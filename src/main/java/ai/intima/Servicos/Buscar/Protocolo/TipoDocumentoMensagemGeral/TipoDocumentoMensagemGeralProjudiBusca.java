package ai.intima.Servicos.Buscar.Protocolo.TipoDocumentoMensagemGeral;

import ai.intima.Servicos.Buscar.ServicoBusca;
import ai.intima.Servicos.Endpoint;

public class TipoDocumentoMensagemGeralProjudiBusca implements ServicoBusca {
    private final String idAutenticador;

    public TipoDocumentoMensagemGeralProjudiBusca(String idAutenticador) { this.idAutenticador = idAutenticador; }

    @Override
    public String getEndpoint() {
        return Endpoint.PROJUDI + Endpoint.PROTOCOLO + "/" + idAutenticador + Endpoint.TIPOS_DOCUMENTO_MENSAGEM_GERAL;
    }
}
