package ai.intima.Servicos.Buscar.Protocolo.TipoDocumentoMensagemGeral;

import ai.intima.Servicos.Buscar.ServicoBusca;
import ai.intima.Servicos.Endpoint;

public class EventosEprocBusca implements ServicoBusca {
    private final String idProtocolo;

    public EventosEprocBusca(String idProtocolo) { this.idProtocolo = idProtocolo; }

    @Override
    public String getEndpoint() {
        return Endpoint.EPROC + Endpoint.PROTOCOLO + "/" + idProtocolo + Endpoint.EVENTOS;
    }
}
