package ai.intima.Servicos.Buscar.Protocolo.TipoDocumento;

import ai.intima.Servicos.Buscar.ServicoBusca;
import ai.intima.Servicos.Endpoint;

public class TipoDocumentoPjeBusca implements ServicoBusca {
    private final String idAutenticador;

    public TipoDocumentoPjeBusca(String idAutenticador) { this.idAutenticador = idAutenticador; }

    @Override
    public String getEndpoint() {
        return Endpoint.PJE + Endpoint.PROTOCOLO + "/" + idAutenticador + Endpoint.TIPOS_DOCUMENTO;
    }
}
