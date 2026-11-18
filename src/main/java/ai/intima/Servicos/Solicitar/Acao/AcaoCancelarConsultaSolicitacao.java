package ai.intima.Servicos.Solicitar.Acao;

import java.util.HashMap;
import java.util.Map;
import ai.intima.Servicos.Solicitar.ServicoSolicitacao;
import ai.intima.Servicos.Endpoint;

public class AcaoCancelarConsultaSolicitacao implements ServicoSolicitacao {
    private final String id;

    public AcaoCancelarConsultaSolicitacao(String id) { this.id = id; }

    @Override
    public String getEndpoint() { return Endpoint.ACAO + "/" + id + Endpoint.CANCELAR_CONSULTA; }

    @Override
    public Map<String, Object> getRequest() { return new HashMap<>(); }
}
