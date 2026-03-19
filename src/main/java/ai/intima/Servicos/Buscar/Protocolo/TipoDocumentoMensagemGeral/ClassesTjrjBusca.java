package ai.intima.Servicos.Buscar.Protocolo.TipoDocumentoMensagemGeral;

import ai.intima.Servicos.Buscar.ServicoBusca;
import ai.intima.Servicos.Endpoint;

public class ClassesTjrjBusca implements ServicoBusca {
    private final String idProtocolo;

    public ClassesTjrjBusca(String idProtocolo) { this.idProtocolo = idProtocolo; }

    @Override
    public String getEndpoint() {
        return Endpoint.TJRJ + Endpoint.PROTOCOLO + "/" + idProtocolo + Endpoint.CLASSES;
    }
}
