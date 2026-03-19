package ai.intima.Servicos.Solicitar.ProtocoloHabilitacao.PJE;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import ai.intima.Servicos.Solicitar.ServicoSolicitacao;
import ai.intima.Servicos.Endpoint;
import ai.intima.Servicos.Helpers;
import ai.intima.Modelos.Documento;
import ai.intima.Modelos.ParteVinculada;

public class ProtocoloHabilitacaoPjeSegundoPassoSolicitacao implements ServicoSolicitacao {
    private final String id;
    private final int tipoSolicitacao;
    private final int tipoDeclaracao;
    private final int polo;
    private final List<ParteVinculada> partesVinculadas;
    private final String tipoDocumentoMensagemGeral;
    private final List<Documento> documentos;

    public ProtocoloHabilitacaoPjeSegundoPassoSolicitacao(String id, int tipoSolicitacao, int tipoDeclaracao,
            int polo, List<ParteVinculada> partesVinculadas, String tipoDocumentoMensagemGeral, List<Documento> documentos) {
        this.id = id;
        this.tipoSolicitacao = tipoSolicitacao;
        this.tipoDeclaracao = tipoDeclaracao;
        this.polo = polo;
        this.partesVinculadas = partesVinculadas;
        this.tipoDocumentoMensagemGeral = tipoDocumentoMensagemGeral;
        this.documentos = documentos != null ? documentos : List.of();
    }

    public ProtocoloHabilitacaoPjeSegundoPassoSolicitacao(String id, int tipoSolicitacao, int tipoDeclaracao,
            int polo, List<ParteVinculada> partesVinculadas, String tipoDocumentoMensagemGeral) {
        this(id, tipoSolicitacao, tipoDeclaracao, polo, partesVinculadas, tipoDocumentoMensagemGeral, List.of());
    }

    @Override
    public String getEndpoint() { return Endpoint.PJE + Endpoint.PROTOCOLO_HABILITACAO + "/" + id + Endpoint.SEGUNDA_ETAPA; }

    @Override
    public Map<String, Object> getRequest() {
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("tipo_solicitacao", String.valueOf(tipoSolicitacao));
        params.put("tipo_declaracao", String.valueOf(tipoDeclaracao));
        params.put("polo", String.valueOf(polo));
        params.put("tipo_documento_mensagem_geral", tipoDocumentoMensagemGeral);
        Helpers.definirPartes(partesVinculadas, params);
        Helpers.definirDocumentos(documentos, params);
        Map<String, Object> request = new HashMap<>();
        request.put("multipart", Helpers.definirMultiPart(params));
        return request;
    }
}
