package ai.intima.Servicos.Solicitar.Protocolo.TJRJ;

import java.util.HashMap;
import java.util.Map;
import ai.intima.Servicos.Solicitar.ServicoSolicitacao;
import ai.intima.Servicos.Endpoint;

public class ProtocoloTjrjPrimeiroPassoSolicitacao implements ServicoSolicitacao {
    private final String idAutenticador;
    private final String numeroDoProcesso;

    public ProtocoloTjrjPrimeiroPassoSolicitacao(String idAutenticador, String numeroDoProcesso) {
        this.idAutenticador = idAutenticador;
        this.numeroDoProcesso = numeroDoProcesso;
    }

    @Override
    public String getEndpoint() { return Endpoint.TJRJ + Endpoint.PROTOCOLO + Endpoint.PRIMEIRA_ETAPA; }

    @Override
    public Map<String, Object> getRequest() {
        Map<String, Object> formParams = new HashMap<>();
        formParams.put("autenticacao_id", idAutenticador);
        formParams.put("numero_processo", numeroDoProcesso);
        Map<String, Object> request = new HashMap<>();
        request.put("form_params", formParams);
        return request;
    }
}
