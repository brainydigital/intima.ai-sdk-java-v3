package ai.intima.Servicos.TentarNovamente.Acao;

import ai.intima.Servicos.TentarNovamente.ServicoTentaNovamente;
import ai.intima.Servicos.Endpoint;

public class AcaoTentaNovamente implements ServicoTentaNovamente {
    private final String id;

    public AcaoTentaNovamente(String id) { this.id = id; }

    @Override
    public String getEndpoint() { return Endpoint.ACAO + "/" + id + Endpoint.TENTAR_NOVAMENTE; }
}
