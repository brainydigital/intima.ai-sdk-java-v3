package ai.intima.Servicos.Excluir.Andamento;

import ai.intima.Servicos.Excluir.ServicoExclusao;
import ai.intima.Servicos.Endpoint;

public class AndamentoExclusao implements ServicoExclusao {
    private final String id;

    public AndamentoExclusao(String id) { this.id = id; }

    @Override
    public String getEndpoint() { return Endpoint.ANDAMENTO + "/" + id + Endpoint.EXCLUIR; }
}
