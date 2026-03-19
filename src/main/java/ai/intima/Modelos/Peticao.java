package ai.intima.Modelos;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

public class Peticao {
    private final String caminho;
    private final String tipo;
    private final String descricao;

    public Peticao(String caminho, String tipo, String descricao) {
        this.caminho = caminho;
        this.tipo = tipo != null ? tipo : "";
        this.descricao = descricao != null ? descricao : "";
    }

    public Peticao(String caminho) {
        this(caminho, "", "");
    }

    public String getCaminho() { return caminho; }
    public String getTipo() { return tipo; }
    public String getDescricao() { return descricao; }

    public Path getArquivo() {
        Path path = Paths.get(caminho);
        if (!path.toFile().exists()) {
            throw new RuntimeException("O caminho do arquivo informado é inválido!");
        }
        return path;
    }

    public Map<String, Object> emArray() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("peticao[arquivo]", getArquivo());
        map.put("peticao[tipo_documento]", tipo);
        map.put("peticao[descricao_documento]", descricao);
        return map;
    }
}
