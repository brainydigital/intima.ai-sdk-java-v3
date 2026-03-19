package ai.intima.Servicos.SolicitarExclusao.Certificado;

import ai.intima.Servicos.SolicitarExclusao.ServicoSolicitacaoExclusao;
import ai.intima.Servicos.Endpoint;

public class CertificadoSolicitacaoExclusao implements ServicoSolicitacaoExclusao {
    private final String id;

    public CertificadoSolicitacaoExclusao(String id) { this.id = id; }

    @Override
    public String getEndpoint() { return Endpoint.CERTIFICADO + "/" + id + Endpoint.SOLICITAR_EXCLUSAO; }
}
