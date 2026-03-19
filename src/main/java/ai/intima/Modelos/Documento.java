package ai.intima.Modelos;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

public class Documento {
    private final String caminho;
    private final String tipo;
    private final String descricao;
    private final int ordem;

    public Documento(String caminho, int ordem, String tipo, String descricao) {
        this.caminho = caminho;
        this.ordem = ordem;
        this.tipo = tipo != null ? tipo : "";
        this.descricao = descricao != null ? descricao : "";
    }

    public Documento(String caminho, int ordem) {
        this(caminho, ordem, "", "");
    }

    public String getCaminho() { return caminho; }
    public String getTipo() { return tipo; }
    public String getDescricao() { return descricao; }
    public int getOrdem() { return ordem; }

    public Path getArquivo() {
        Path path = Paths.get(caminho);
        if (!path.toFile().exists()) {
            throw new RuntimeException("O caminho do arquivo informado é inválido!");
        }
        return path;
    }

    public Map<String, Object> emArray() {
        int index = ordem - 1;
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("documentos[" + index + "][arquivo]", getArquivo());
        map.put("documentos[" + index + "][tipo_documento]", tipo);
        map.put("documentos[" + index + "][descricao_documento]", descricao);
        map.put("documentos[" + index + "][ordem]", String.valueOf(ordem));
        return map;
    }
}
