package ai.intima.Servicos.Cadastrar.Autenticador;

import java.util.HashMap;
import java.util.Map;
import ai.intima.Servicos.Cadastrar.ServicoCadastro;
import ai.intima.Servicos.Endpoint;
import ai.intima.DadosTipo.Oab;

public class AutenticadorCertificadoCadastro implements ServicoCadastro {
    private final String idTribunal;
    private final Oab oab;
    private final String idCertificado;

    public AutenticadorCertificadoCadastro(String idTribunal, Oab oab, String idCertificado) {
        this.idTribunal = idTribunal;
        this.oab = oab;
        this.idCertificado = idCertificado;
    }

    @Override
    public String getEndpoint() { return Endpoint.AUTENTICADOR + Endpoint.CERTIFICADO + Endpoint.CADASTRAR; }

    @Override
    public Map<String, Object> getRequest() {
        Map<String, Object> formParams = new HashMap<>();
        formParams.put("tribunal_id", idTribunal);
        formParams.put("certificado_id", idCertificado);
        formParams.put("oab_numero", oab.getNumero());
        formParams.put("oab_letra", oab.getLetra());
        formParams.put("oab_uf", oab.getUf());
        Map<String, Object> request = new HashMap<>();
        request.put("form_params", formParams);
        return request;
    }
}
