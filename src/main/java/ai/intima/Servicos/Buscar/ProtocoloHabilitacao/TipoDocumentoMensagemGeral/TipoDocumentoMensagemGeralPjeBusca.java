package ai.intima.Servicos.Buscar.ProtocoloHabilitacao.TipoDocumentoMensagemGeral;

import ai.intima.Servicos.Buscar.ServicoBusca;
import ai.intima.Servicos.Endpoint;

public class TipoDocumentoMensagemGeralPjeBusca implements ServicoBusca {
    private final String idProtocoloHabilitacao;

    public TipoDocumentoMensagemGeralPjeBusca(String idProtocoloHabilitacao) { this.idProtocoloHabilitacao = idProtocoloHabilitacao; }

    @Override
    public String getEndpoint() {
        return Endpoint.PJE + Endpoint.PROTOCOLO_HABILITACAO + "/" + idProtocoloHabilitacao + Endpoint.TIPOS_DOCUMENTO_MENSAGEM_GERAL;
    }
}
