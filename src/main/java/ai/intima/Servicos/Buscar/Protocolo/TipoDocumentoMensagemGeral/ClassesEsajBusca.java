package ai.intima.Servicos.Buscar.Protocolo.TipoDocumentoMensagemGeral;

import ai.intima.Servicos.Buscar.ServicoBusca;
import ai.intima.Servicos.Endpoint;

public class ClassesEsajBusca implements ServicoBusca {
    private final String idProtocolo;

    public ClassesEsajBusca(String idProtocolo) { this.idProtocolo = idProtocolo; }

    @Override
    public String getEndpoint() {
        return Endpoint.ESAJ + Endpoint.PROTOCOLO + "/" + idProtocolo + Endpoint.CLASSES;
    }
}
