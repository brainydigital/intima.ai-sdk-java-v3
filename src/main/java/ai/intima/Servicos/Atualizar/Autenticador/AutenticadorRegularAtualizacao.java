package ai.intima.Servicos.Atualizar.Autenticador;

import java.util.HashMap;
import java.util.Map;
import ai.intima.Servicos.Atualizar.ServicoAtualizacao;
import ai.intima.Servicos.Endpoint;

public class AutenticadorRegularAtualizacao implements ServicoAtualizacao {
    private final String id;
    private final String usuario;
    private final String senha;

    public AutenticadorRegularAtualizacao(String id, String usuario, String senha) {
        this.id = id;
        this.usuario = usuario;
        this.senha = senha;
    }

    public AutenticadorRegularAtualizacao(String id) { this(id, null, null); }

    @Override
    public String getEndpoint() { return Endpoint.AUTENTICADOR + Endpoint.REGULAR + "/" + id + Endpoint.ATUALIZAR; }

    @Override
    public Map<String, Object> getRequest() {
        Map<String, Object> request = new HashMap<>();
        request.put("usuario", usuario);
        request.put("senha", senha);
        return request;
    }
}
