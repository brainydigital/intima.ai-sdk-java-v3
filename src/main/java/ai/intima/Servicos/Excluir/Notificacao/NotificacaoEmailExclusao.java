package ai.intima.Servicos.Excluir.Notificacao;

import ai.intima.Servicos.Excluir.ServicoExclusao;
import ai.intima.Servicos.Endpoint;

public class NotificacaoEmailExclusao implements ServicoExclusao {
    private final String id;

    public NotificacaoEmailExclusao(String id) { this.id = id; }

    @Override
    public String getEndpoint() { return Endpoint.NOTIFICACAO + Endpoint.EMAIL + "/" + id + Endpoint.EXCLUIR; }
}
