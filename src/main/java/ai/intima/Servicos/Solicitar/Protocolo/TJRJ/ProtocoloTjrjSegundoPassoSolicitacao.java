package ai.intima.Servicos.Solicitar.Protocolo.TJRJ;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import ai.intima.Servicos.Solicitar.ServicoSolicitacao;
import ai.intima.Servicos.Endpoint;
import ai.intima.Servicos.Helpers;
import ai.intima.Modelos.Documento;
import ai.intima.Modelos.ParteVinculada;
import ai.intima.Modelos.Peticao;

public class ProtocoloTjrjSegundoPassoSolicitacao implements ServicoSolicitacao {
    private final String id;
    private final String idClasse;
    private final List<ParteVinculada> partesVinculadas;
    private final Peticao peticao;
    private final List<Documento> documentos;
    private final String idCategoria;

    public ProtocoloTjrjSegundoPassoSolicitacao(String id, String idClasse,
            List<ParteVinculada> partesVinculadas, Peticao peticao, List<Documento> documentos, String idCategoria) {
        this.id = id;
        this.idClasse = idClasse;
        this.partesVinculadas = partesVinculadas;
        this.peticao = peticao;
        this.documentos = documentos != null ? documentos : List.of();
        this.idCategoria = idCategoria;
    }

    public ProtocoloTjrjSegundoPassoSolicitacao(String id, String idClasse,
            List<ParteVinculada> partesVinculadas, Peticao peticao, List<Documento> documentos) {
        this(id, idClasse, partesVinculadas, peticao, documentos, null);
    }

    public ProtocoloTjrjSegundoPassoSolicitacao(String id, String idClasse,
            List<ParteVinculada> partesVinculadas, Peticao peticao) {
        this(id, idClasse, partesVinculadas, peticao, List.of(), null);
    }

    @Override
    public String getEndpoint() { return Endpoint.TJRJ + Endpoint.PROTOCOLO + "/" + id + Endpoint.SEGUNDA_ETAPA; }

    @Override
    public Map<String, Object> getRequest() {
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("classe_id", idClasse);
        params.put("categoria_id", idCategoria);
        Helpers.definirPartes(partesVinculadas, params);
        Helpers.definirPeticao(peticao, params);
        Helpers.definirDocumentos(documentos, params);
        Map<String, Object> request = new HashMap<>();
        request.put("multipart", Helpers.definirMultiPart(params));
        return request;
    }
}
