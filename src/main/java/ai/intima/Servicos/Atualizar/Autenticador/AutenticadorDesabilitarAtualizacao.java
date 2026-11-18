package ai.intima.Servicos.Atualizar.Autenticador;

import java.util.HashMap;
import java.util.Map;
import ai.intima.Servicos.Atualizar.ServicoAtualizacao;
import ai.intima.Servicos.Endpoint;

public class AutenticadorDesabilitarAtualizacao implements ServicoAtualizacao {
    private final String id;

    public AutenticadorDesabilitarAtualizacao(String id) { this.id = id; }

    @Override
    public String getEndpoint() { return Endpoint.AUTENTICADOR + "/" + id + Endpoint.DESABILITAR; }

    @Override
    public Map<String, Object> getRequest() { return new HashMap<>(); }
}
