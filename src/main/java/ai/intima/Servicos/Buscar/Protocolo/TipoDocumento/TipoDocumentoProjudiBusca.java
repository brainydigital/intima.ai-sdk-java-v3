package ai.intima.Servicos.Buscar.Protocolo.TipoDocumento;

import ai.intima.Servicos.Buscar.ServicoBusca;
import ai.intima.Servicos.Endpoint;

public class TipoDocumentoProjudiBusca implements ServicoBusca {
    private final String idAutenticador;

    public TipoDocumentoProjudiBusca(String idAutenticador) { this.idAutenticador = idAutenticador; }

    @Override
    public String getEndpoint() {
        return Endpoint.PROJUDI + Endpoint.PROTOCOLO + "/" + idAutenticador + Endpoint.TIPOS_DOCUMENTO;
    }
}
