package ai.intima.Servicos.Cadastrar.Autenticador;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import ai.intima.Servicos.Cadastrar.ServicoCadastro;
import ai.intima.Servicos.Endpoint;

public class AutenticadorDoisFatoresCadastro implements ServicoCadastro {
    private final String ids;
    private final String secret;

    public AutenticadorDoisFatoresCadastro(String ids, String secret) {
        this.ids = ids;
        this.secret = secret;
    }

    public AutenticadorDoisFatoresCadastro(List<String> ids, String secret) {
        this(String.join(",", ids), secret);
    }

    @Override
    public String getEndpoint() { return Endpoint.AUTENTICADOR + "/" + ids + Endpoint.DOIS_FATORES; }

    @Override
    public Map<String, Object> getRequest() {
        Map<String, Object> formParams = new LinkedHashMap<>();
        formParams.put("secret", secret);

        Map<String, Object> request = new LinkedHashMap<>();
        request.put("form_params", formParams);
        return request;
    }
}
