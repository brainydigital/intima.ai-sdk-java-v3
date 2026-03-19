package ai.intima.Servicos.Excluir.Escuta;

import ai.intima.Servicos.Excluir.ServicoExclusao;
import ai.intima.Servicos.Endpoint;

public class EscutaExclusao implements ServicoExclusao {
    private final String id;

    public EscutaExclusao(String id) { this.id = id; }

    @Override
    public String getEndpoint() { return Endpoint.ESCUTA + "/" + id + Endpoint.EXCLUIR; }
}
