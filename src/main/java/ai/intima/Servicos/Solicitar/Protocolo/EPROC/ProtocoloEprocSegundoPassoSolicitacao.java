package ai.intima.Servicos.Solicitar.Protocolo.EPROC;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import ai.intima.Servicos.Solicitar.ServicoSolicitacao;
import ai.intima.Servicos.Endpoint;
import ai.intima.Servicos.Helpers;
import ai.intima.Modelos.Documento;
import ai.intima.Modelos.ParteVinculada;

public class ProtocoloEprocSegundoPassoSolicitacao implements ServicoSolicitacao {
    private final String id;
    private final String tipoDocumentoMensagemGeral;
    private final List<ParteVinculada> partesVinculadas;
    private final List<Documento> documentos;

    public ProtocoloEprocSegundoPassoSolicitacao(String id, String tipoDocumentoMensagemGeral,
            List<ParteVinculada> partesVinculadas, List<Documento> documentos) {
        this.id = id;
        this.tipoDocumentoMensagemGeral = tipoDocumentoMensagemGeral;
        this.partesVinculadas = partesVinculadas;
        this.documentos = documentos != null ? documentos : List.of();
    }

    public ProtocoloEprocSegundoPassoSolicitacao(String id, String tipoDocumentoMensagemGeral,
            List<ParteVinculada> partesVinculadas) {
        this(id, tipoDocumentoMensagemGeral, partesVinculadas, List.of());
    }

    @Override
    public String getEndpoint() { return Endpoint.EPROC + Endpoint.PROTOCOLO + "/" + id + Endpoint.SEGUNDA_ETAPA; }

    @Override
    public Map<String, Object> getRequest() {
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("tipo_documento_mensagem_geral", tipoDocumentoMensagemGeral);
        Helpers.definirPartes(partesVinculadas, params);
        Helpers.definirDocumentos(documentos, params);
        Map<String, Object> request = new HashMap<>();
        request.put("multipart", Helpers.definirMultiPart(params));
        return request;
    }
}
