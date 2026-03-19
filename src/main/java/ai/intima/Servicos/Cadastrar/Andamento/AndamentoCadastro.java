package ai.intima.Servicos.Cadastrar.Andamento;

import java.util.HashMap;
import java.util.Map;
import ai.intima.Servicos.Cadastrar.ServicoCadastro;
import ai.intima.Servicos.Endpoint;

public class AndamentoCadastro implements ServicoCadastro {
    private final String idAutenticador;
    private final String numeroDoProcesso;

    public AndamentoCadastro(String idAutenticador, String numeroDoProcesso) {
        this.idAutenticador = idAutenticador;
        this.numeroDoProcesso = numeroDoProcesso;
    }

    @Override
    public String getEndpoint() { return Endpoint.ANDAMENTO + Endpoint.CADASTRAR; }

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
