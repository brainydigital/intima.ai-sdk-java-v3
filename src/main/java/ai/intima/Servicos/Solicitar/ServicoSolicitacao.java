package ai.intima.Servicos.Solicitar;

import java.util.Map;

public interface ServicoSolicitacao {
    String getEndpoint();
    Map<String, Object> getRequest();
}
