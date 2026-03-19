package ai.intima.Servicos.Atualizar.Autenticador;

import java.util.HashMap;
import java.util.Map;
import ai.intima.Servicos.Atualizar.ServicoAtualizacao;
import ai.intima.Servicos.Endpoint;

public class AutenticadorAlterarParaRegularAtualizacao implements ServicoAtualizacao {
    private final String id;
    private final String usuario;
    private final String senha;

    public AutenticadorAlterarParaRegularAtualizacao(String id, String usuario, String senha) {
        this.id = id;
        this.usuario = usuario;
        this.senha = senha;
    }

    @Override
    public String getEndpoint() { return Endpoint.AUTENTICADOR + Endpoint.REGULAR + "/" + id + Endpoint.ALTERAR_PARA_REGULAR; }

    @Override
    public Map<String, Object> getRequest() {
        Map<String, Object> formParams = new HashMap<>();
        formParams.put("usuario", usuario);
        formParams.put("senha", senha);
        Map<String, Object> request = new HashMap<>();
        request.put("form_params", formParams);
        return request;
    }
}
