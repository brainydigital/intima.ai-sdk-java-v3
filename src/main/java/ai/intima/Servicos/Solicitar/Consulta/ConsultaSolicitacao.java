package ai.intima.Servicos.Solicitar.Consulta;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import ai.intima.Servicos.Solicitar.ServicoSolicitacao;
import ai.intima.Servicos.Endpoint;
import ai.intima.DadosTipo.Oab;

public class ConsultaSolicitacao implements ServicoSolicitacao {
    private final String idAutenticador;
    private final String numeroDoProcesso;
    private final String nomeParte;
    private final String nomeRepresentante;
    private final Oab oab;
    private final String numeroDocumentoParte;

    public ConsultaSolicitacao(String idAutenticador, String numeroDoProcesso, String nomeParte,
            String nomeRepresentante, Oab oab, String numeroDocumentoParte) {
        this.idAutenticador = idAutenticador;
        this.numeroDoProcesso = numeroDoProcesso != null ? numeroDoProcesso : "";
        this.nomeParte = nomeParte != null ? nomeParte : "";
        this.nomeRepresentante = nomeRepresentante != null ? nomeRepresentante : "";
        this.oab = oab;
        this.numeroDocumentoParte = numeroDocumentoParte != null ? numeroDocumentoParte : "";
    }

    @Deprecated
    public ConsultaSolicitacao(String idAutenticador, String numeroDoProcesso, String nomeParte,
            String nomeRepresentante, List<Oab> oab, String numeroDocumentoParte, String token) {
        this(idAutenticador, numeroDoProcesso, nomeParte, nomeRepresentante,
                oab != null && !oab.isEmpty() ? oab.get(0) : null, numeroDocumentoParte);
    }

    public ConsultaSolicitacao(String idAutenticador) {
        this(idAutenticador, "", "", "", (Oab) null, "");
    }

    @Override
    public String getEndpoint() { return Endpoint.CONSULTA + Endpoint.SOLICITAR; }

    @Override
    public Map<String, Object> getRequest() {
        Map<String, Object> formParams = new LinkedHashMap<>();
        formParams.put("autenticacao_id", idAutenticador);
        formParams.put("numero_processo", numeroDoProcesso);
        formParams.put("nome_parte", nomeParte);
        formParams.put("nome_representante", nomeRepresentante);
        formParams.put("oab", oab);
        formParams.put("numero_documento_parte", numeroDocumentoParte);

        Map<String, Object> request = new LinkedHashMap<>();
        request.put("form_params", formParams);
        return request;
    }
}
