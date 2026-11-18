package ai.intima;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class DocsTest {

    private static final Pattern BLOCO = Pattern.compile("```java\\n(.*?)```", Pattern.DOTALL);

    @TempDir
    Path saida;

    private record Exemplo(String origem, String fonte) {}

    private static JavaFileObject arquivo(String classe, String fonte) {
        return new SimpleJavaFileObject(URI.create("string:///exemplos/" + classe + ".java"), JavaFileObject.Kind.SOURCE) {
            @Override
            public CharSequence getCharContent(boolean ignorarErros) {
                return fonte;
            }
        };
    }

    private static List<Exemplo> exemplos() throws IOException {
        List<Exemplo> exemplos = new ArrayList<>();
        List<Path> paginas;

        try (Stream<Path> arquivos = Files.walk(Path.of("docs"))) {
            paginas = arquivos.filter(p -> p.toString().endsWith(".md")).sorted().toList();
        }

        for (Path pagina : paginas) {
            String texto = Files.readString(pagina);
            Matcher bloco = BLOCO.matcher(texto);

            while (bloco.find()) {
                StringBuilder imports = new StringBuilder();
                StringBuilder corpo = new StringBuilder();

                for (String linha : bloco.group(1).split("\n")) {
                    (linha.startsWith("import ") ? imports : corpo).append(linha).append('\n');
                }

                String classe = "Exemplo" + exemplos.size();
                String fonte = "package exemplos;\n" + imports + "class " + classe
                        + " { static void exemplo() throws Exception {\n" + corpo + "} }\n";
                int linha = texto.substring(0, bloco.start()).split("\n", -1).length;
                exemplos.add(new Exemplo(pagina + ":" + linha, fonte));
            }
        }

        return exemplos;
    }

    @Test
    void exemplosDosDocsCompilamContraASdk() throws IOException {
        List<Exemplo> exemplos = exemplos();
        JavaCompiler compilador = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnosticos = new DiagnosticCollector<>();
        List<JavaFileObject> fontes = new ArrayList<>();

        for (int i = 0; i < exemplos.size(); i++) {
            fontes.add(arquivo("Exemplo" + i, exemplos.get(i).fonte()));
        }

        boolean compilou = compilador.getTask(null, null, diagnosticos,
                List.of("-classpath", System.getProperty("java.class.path"), "-d", saida.toString(), "-proc:none"),
                null, fontes).call();

        List<String> erros = new ArrayList<>();

        for (Diagnostic<? extends JavaFileObject> diagnostico : diagnosticos.getDiagnostics()) {
            if (diagnostico.getKind() == Diagnostic.Kind.ERROR) {
                String nome = diagnostico.getSource().getName().replaceAll(".*Exemplo(\\d+)\\.java", "$1");
                Exemplo exemplo = exemplos.get(Integer.parseInt(nome));
                String linha = exemplo.fonte().split("\n")[(int) diagnostico.getLineNumber() - 1].trim();
                erros.add(exemplo.origem() + " [" + linha + "]: " + diagnostico.getMessage(null).split("\n")[0]);
            }
        }

        assertTrue(exemplos.size() > 50);
        assertTrue(compilou && erros.isEmpty(), String.join("\n", erros));
    }
}
