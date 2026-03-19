package ai.intima.Modelos;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Certificado {
    private final String caminhoDoCertificado;
    private final String senha;

    public Certificado(String caminhoDoCertificado, String senha) {
        this.caminhoDoCertificado = caminhoDoCertificado;
        this.senha = senha;
    }

    public String getCaminhoDoCertificado() {
        return caminhoDoCertificado;
    }

    public String getSenha() {
        return senha;
    }

    public Path getArquivo() {
        Path path = Paths.get(caminhoDoCertificado);
        if (!path.toFile().exists()) {
            throw new RuntimeException("O caminho do arquivo informado é inválido!");
        }
        return path;
    }

    public List<Map<String, Object>> emArray() {
        List<Map<String, Object>> parts = new ArrayList<>();

        Map<String, Object> arquivo = new HashMap<>();
        arquivo.put("name", "arquivo");
        arquivo.put("contents", getArquivo());
        arquivo.put("filename", Paths.get(caminhoDoCertificado).getFileName().toString());
        parts.add(arquivo);

        Map<String, Object> senhaMap = new HashMap<>();
        senhaMap.put("name", "senha");
        senhaMap.put("contents", senha);
        parts.add(senhaMap);

        Map<String, Object> senhaConfirmation = new HashMap<>();
        senhaConfirmation.put("name", "senha_confirmation");
        senhaConfirmation.put("contents", senha);
        parts.add(senhaConfirmation);

        return parts;
    }
}
