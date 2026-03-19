package ai.intima.Servicos.Solicitar.Escuta;

import java.util.HashMap;
import java.util.Map;
import ai.intima.Servicos.Solicitar.ServicoSolicitacao;
import ai.intima.Servicos.Endpoint;

public class EscutaSolicitacao implements ServicoSolicitacao {
    private final String id;

    public EscutaSolicitacao(String id) { this.id = id; }

    @Override
    public String getEndpoint() { return Endpoint.ESCUTA + "/" + id + Endpoint.SOLICITAR; }

    @Override
    public Map<String, Object> getRequest() { return new HashMap<>(); }
}
