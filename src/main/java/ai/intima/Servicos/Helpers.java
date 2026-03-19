package ai.intima.Servicos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ai.intima.Modelos.Documento;
import ai.intima.Modelos.ParteVinculada;
import ai.intima.Modelos.Peticao;

public final class Helpers {
    private Helpers() {}

    public static void definirPartes(List<ParteVinculada> partes, Map<String, Object> params) {
        for (int i = 0; i < partes.size(); i++) {
            ParteVinculada parte = partes.get(i);
            params.put("partes_vinculadas[" + i + "][nome]", parte.getNome());
            if (parte.getParticipacao() != null) {
                params.put("partes_vinculadas[" + i + "][participacao]", parte.getParticipacao());
            }
        }
    }

    public static void definirPeticao(Peticao peticao, Map<String, Object> params) {
        if (peticao != null) {
            params.putAll(peticao.emArray());
        }
    }

    public static void definirDocumentos(List<Documento> documentos, Map<String, Object> params) {
        if (documentos != null) {
            for (Documento doc : documentos) {
                params.putAll(doc.emArray());
            }
        }
    }

    public static List<Map<String, Object>> definirMultiPart(Map<String, Object> params) {
        List<Map<String, Object>> parts = new ArrayList<>();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            Map<String, Object> part = new HashMap<>();
            part.put("name", entry.getKey());
            part.put("contents", entry.getValue());
            parts.add(part);
        }
        return parts;
    }
}
