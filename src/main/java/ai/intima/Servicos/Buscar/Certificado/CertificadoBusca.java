package ai.intima.Servicos.Buscar.Certificado;

import ai.intima.Servicos.Buscar.ServicoBusca;
import ai.intima.Servicos.Endpoint;

public class CertificadoBusca implements ServicoBusca {
    private final String id;

    public CertificadoBusca(String id) { this.id = id; }
    public CertificadoBusca() { this.id = null; }

    @Override
    public String getEndpoint() {
        if (id == null || id.isEmpty()) return Endpoint.CERTIFICADO + Endpoint.BUSCAR;
        return Endpoint.CERTIFICADO + "/" + id + Endpoint.BUSCAR;
    }
}
