package ai.intima.Servicos.Atualizar.Intimacao;

import java.util.HashMap;
import java.util.Map;
import ai.intima.Servicos.Atualizar.ServicoAtualizacao;
import ai.intima.Servicos.Endpoint;

public class IntimacaoMarcarComoRevisadaAtualizacao implements ServicoAtualizacao {
    private final String id;

    public IntimacaoMarcarComoRevisadaAtualizacao(String id) { this.id = id; }

    @Override
    public String getEndpoint() { return Endpoint.INTIMACAO + "/" + id + Endpoint.MARCAR_COMO_REVISADA; }

    @Override
    public Map<String, Object> getRequest() { return new HashMap<>(); }
}
