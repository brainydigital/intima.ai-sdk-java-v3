package ai.intima.Servicos.Solicitar.Autenticador;

import java.util.HashMap;
import java.util.Map;
import ai.intima.Servicos.Solicitar.ServicoSolicitacao;
import ai.intima.Servicos.Endpoint;

public class AutenticadorCapturarIntimacoesSolicitacao implements ServicoSolicitacao {
    private final String id;

    public AutenticadorCapturarIntimacoesSolicitacao(String id) { this.id = id; }

    @Override
    public String getEndpoint() { return Endpoint.AUTENTICADOR + "/" + id + Endpoint.CAPTURAR_INTIMACOES; }

    @Override
    public Map<String, Object> getRequest() { return new HashMap<>(); }
}
