package ai.intima.Servicos.Buscar.Protocolo.TipoDocumento;

import ai.intima.Servicos.Buscar.ServicoBusca;
import ai.intima.Servicos.Endpoint;

public class TipoDocumentoEprocBusca implements ServicoBusca {
    private final String idProtocolo;
    private final String idEvento;

    public TipoDocumentoEprocBusca(String idProtocolo, String idEvento) {
        this.idProtocolo = idProtocolo;
        this.idEvento = idEvento;
    }

    public TipoDocumentoEprocBusca(String idProtocolo) { this(idProtocolo, null); }

    @Override
    public String getEndpoint() {
        if (idEvento == null || idEvento.isEmpty())
            return Endpoint.EPROC + Endpoint.PROTOCOLO + "/" + idProtocolo + Endpoint.TIPOS_DOCUMENTO;
        return Endpoint.EPROC + Endpoint.PROTOCOLO + "/" + idProtocolo + Endpoint.TIPOS_DOCUMENTO + "/" + idEvento;
    }
}
