package ai.intima.Servicos.Cadastrar.Notificacao;

import java.util.HashMap;
import java.util.Map;
import ai.intima.Servicos.Cadastrar.ServicoCadastro;
import ai.intima.Servicos.Endpoint;

public class NotificacaoEmailCadastro implements ServicoCadastro {
    private final String email;

    public NotificacaoEmailCadastro(String email) { this.email = email; }

    @Override
    public String getEndpoint() { return Endpoint.NOTIFICACAO + Endpoint.EMAIL + Endpoint.CADASTRAR; }

    @Override
    public Map<String, Object> getRequest() {
        Map<String, Object> formParams = new HashMap<>();
        formParams.put("email", email);
        Map<String, Object> request = new HashMap<>();
        request.put("form_params", formParams);
        return request;
    }
}
