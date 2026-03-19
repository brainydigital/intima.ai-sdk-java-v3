package ai.intima.Servicos.SolicitarAtualizacao.Certificado;

import java.util.HashMap;
import java.util.Map;
import ai.intima.Servicos.SolicitarAtualizacao.ServicoSolicitaAtualizacao;
import ai.intima.Servicos.Endpoint;
import ai.intima.Modelos.Certificado;

public class CertificadoSolicitaAtualizacao implements ServicoSolicitaAtualizacao {
    private final String id;
    private final Certificado certificado;

    public CertificadoSolicitaAtualizacao(String id, Certificado certificado) {
        this.id = id;
        this.certificado = certificado;
    }

    @Override
    public String getEndpoint() { return Endpoint.CERTIFICADO + "/" + id + Endpoint.SOLICITAR_ATUALIZACAO; }

    @Override
    public Map<String, Object> getRequest() {
        Map<String, Object> request = new HashMap<>();
        request.put("multipart", certificado.emArray());
        return request;
    }
}
