package ai.intima.Servicos.Cadastrar.Autenticador;

import java.util.HashMap;
import java.util.Map;
import ai.intima.Servicos.Cadastrar.ServicoCadastro;
import ai.intima.Servicos.Endpoint;
import ai.intima.DadosTipo.Oab;

public class AutenticadorRegularCadastro implements ServicoCadastro {
    private final String idTribunal;
    private final Oab oab;
    private final String usuario;
    private final String senha;

    public AutenticadorRegularCadastro(String idTribunal, Oab oab, String usuario, String senha) {
        this.idTribunal = idTribunal;
        this.oab = oab;
        this.usuario = usuario;
        this.senha = senha;
    }

    @Override
    public String getEndpoint() { return Endpoint.AUTENTICADOR + Endpoint.REGULAR + Endpoint.CADASTRAR; }

    @Override
    public Map<String, Object> getRequest() {
        Map<String, Object> formParams = new HashMap<>();
        formParams.put("tribunal_id", idTribunal);
        formParams.put("usuario", usuario);
        formParams.put("senha", senha);
        formParams.put("oab_numero", oab.getNumero());
        formParams.put("oab_letra", oab.getLetra());
        formParams.put("oab_uf", oab.getUf());
        Map<String, Object> request = new HashMap<>();
        request.put("form_params", formParams);
        return request;
    }
}
