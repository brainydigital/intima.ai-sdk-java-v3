package ai.intima.Servicos.Buscar.Protocolo.TipoDocumentoMensagemGeral;

import ai.intima.Servicos.Buscar.ServicoBusca;
import ai.intima.Servicos.Endpoint;

public class TipoDocumentoMensagemGeralPjeBusca implements ServicoBusca {
    private final String idAutenticador;

    public TipoDocumentoMensagemGeralPjeBusca(String idAutenticador) { this.idAutenticador = idAutenticador; }

    @Override
    public String getEndpoint() {
        return Endpoint.PJE + Endpoint.PROTOCOLO + "/" + idAutenticador + Endpoint.TIPOS_DOCUMENTO_MENSAGEM_GERAL;
    }
}
