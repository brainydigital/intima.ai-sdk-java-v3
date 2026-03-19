package ai.intima.Servicos.Buscar.Protocolo.TipoDocumento;

import ai.intima.Servicos.Buscar.ServicoBusca;
import ai.intima.Servicos.Endpoint;

public class TipoDocumentoEsajBusca implements ServicoBusca {
    private final String idProtocolo;
    private final String idClasse;

    public TipoDocumentoEsajBusca(String idProtocolo, String idClasse) {
        this.idProtocolo = idProtocolo;
        this.idClasse = idClasse;
    }

    public TipoDocumentoEsajBusca(String idProtocolo) { this(idProtocolo, null); }

    @Override
    public String getEndpoint() {
        if (idClasse == null || idClasse.isEmpty())
            return Endpoint.ESAJ + Endpoint.PROTOCOLO + "/" + idProtocolo + Endpoint.TIPOS_DOCUMENTO;
        return Endpoint.ESAJ + Endpoint.PROTOCOLO + "/" + idProtocolo + Endpoint.TIPOS_DOCUMENTO + "/" + idClasse;
    }
}
