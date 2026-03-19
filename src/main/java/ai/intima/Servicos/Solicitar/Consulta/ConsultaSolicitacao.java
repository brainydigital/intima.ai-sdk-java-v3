package ai.intima.Servicos.Solicitar.Consulta;

import java.util.HashMap;
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
    private final List<Oab> oab;
    private final String numeroDocumentoParte;
    private final String token;

    public ConsultaSolicitacao(String idAutenticador, String numeroDoProcesso, String nomeParte,
            String nomeRepresentante, List<Oab> oab, String numeroDocumentoParte, String token) {
        this.idAutenticador = idAutenticador;
        this.numeroDoProcesso = numeroDoProcesso != null ? numeroDoProcesso : "";
        this.nomeParte = nomeParte != null ? nomeParte : "";
        this.nomeRepresentante = nomeRepresentante != null ? nomeRepresentante : "";
        this.oab = oab != null ? oab : List.of();
        this.numeroDocumentoParte = numeroDocumentoParte != null ? numeroDocumentoParte : "";
        this.token = token != null ? token : "";
    }

    public ConsultaSolicitacao(String idAutenticador) {
        this(idAutenticador, "", "", "", List.of(), "", "");
    }

    @Override
    public String getEndpoint() { return Endpoint.CONSULTA + Endpoint.SOLICITAR; }

    @Override
    public Map<String, Object> getRequest() {
        Map<String, Object> formParams = new HashMap<>();
        formParams.put("autenticacao_id", idAutenticador);
        formParams.put("numero_processo", numeroDoProcesso);
        formParams.put("nome_parte", nomeParte);
        formParams.put("nome_representante", nomeRepresentante);
        formParams.put("oab", oab);
        formParams.put("numero_documento_parte", numeroDocumentoParte);
        formParams.put("token", token);
        Map<String, Object> request = new HashMap<>();
        request.put("form_params", formParams);
        return request;
    }
}
