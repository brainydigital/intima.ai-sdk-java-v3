package ai.intima.Servicos.Atualizar;

import java.util.Map;

public interface ServicoAtualizacao {
    String getEndpoint();
    Map<String, Object> getRequest();
}
