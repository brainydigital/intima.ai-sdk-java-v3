package ai.intima.Servicos.Buscar.Usuario;

import ai.intima.Servicos.Buscar.ServicoBusca;
import ai.intima.Servicos.Endpoint;

public class UsuarioBusca implements ServicoBusca {
    @Override
    public String getEndpoint() { return Endpoint.USUARIO + Endpoint.BUSCAR; }
}
