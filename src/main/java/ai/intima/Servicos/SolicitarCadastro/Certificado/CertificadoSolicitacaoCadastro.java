package ai.intima.Servicos.SolicitarCadastro.Certificado;

import java.util.HashMap;
import java.util.Map;
import ai.intima.Servicos.SolicitarCadastro.ServicoSolicitacaoCadastro;
import ai.intima.Servicos.Endpoint;
import ai.intima.Modelos.Certificado;

public class CertificadoSolicitacaoCadastro implements ServicoSolicitacaoCadastro {
    private final Certificado certificado;

    public CertificadoSolicitacaoCadastro(Certificado certificado) { this.certificado = certificado; }

    @Override
    public String getEndpoint() { return Endpoint.CERTIFICADO + Endpoint.SOLICITAR_CADASTRO; }

    @Override
    public Map<String, Object> getRequest() {
        Map<String, Object> request = new HashMap<>();
        request.put("multipart", certificado.emArray());
        return request;
    }
}
