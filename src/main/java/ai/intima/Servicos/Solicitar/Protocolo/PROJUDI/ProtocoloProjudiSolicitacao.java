package ai.intima.Servicos.Solicitar.Protocolo.PROJUDI;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import ai.intima.Servicos.Solicitar.ServicoSolicitacao;
import ai.intima.Servicos.Endpoint;
import ai.intima.Servicos.Helpers;
import ai.intima.Modelos.Documento;

public class ProtocoloProjudiSolicitacao implements ServicoSolicitacao {
    private final String idAutenticador;
    private final String numeroDoProcesso;
    private final String tipoDocumentoMensagemGeral;
    private final List<Documento> documentos;

    public ProtocoloProjudiSolicitacao(String idAutenticador, String numeroDoProcesso,
            String tipoDocumentoMensagemGeral, List<Documento> documentos) {
        this.idAutenticador = idAutenticador;
        this.numeroDoProcesso = numeroDoProcesso;
        this.tipoDocumentoMensagemGeral = tipoDocumentoMensagemGeral;
        this.documentos = documentos != null ? documentos : List.of();
    }

    public ProtocoloProjudiSolicitacao(String idAutenticador, String numeroDoProcesso, String tipoDocumentoMensagemGeral) {
        this(idAutenticador, numeroDoProcesso, tipoDocumentoMensagemGeral, List.of());
    }

    @Override
    public String getEndpoint() { return Endpoint.PROJUDI + Endpoint.PROTOCOLO + Endpoint.SOLICITAR; }

    @Override
    public Map<String, Object> getRequest() {
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("numero_processo", numeroDoProcesso);
        params.put("autenticacao_id", idAutenticador);
        params.put("tipo_documento_mensagem_geral", tipoDocumentoMensagemGeral);
        Helpers.definirDocumentos(documentos, params);
        Map<String, Object> request = new HashMap<>();
        request.put("multipart", Helpers.definirMultiPart(params));
        return request;
    }
}
