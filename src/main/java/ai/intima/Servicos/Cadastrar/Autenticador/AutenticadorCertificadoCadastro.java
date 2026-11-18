package ai.intima.Servicos.Cadastrar.Autenticador;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import ai.intima.Servicos.Cadastrar.ServicoCadastro;
import ai.intima.Servicos.Endpoint;
import ai.intima.DadosTipo.Oab;

public class AutenticadorCertificadoCadastro implements ServicoCadastro {
    private final Object idTribunal;
    private final Oab oab;
    private final String idCertificado;
    private final String usuarioSigla;
    private final String usuarioDescricao;
    private final String secret;

    public AutenticadorCertificadoCadastro(String idTribunal, Oab oab, String idCertificado,
            String usuarioSigla, String usuarioDescricao, String secret) {
        this((Object) idTribunal, oab, idCertificado, usuarioSigla, usuarioDescricao, secret);
    }

    public AutenticadorCertificadoCadastro(List<String> idsTribunais, Oab oab, String idCertificado,
            String usuarioSigla, String usuarioDescricao, String secret) {
        this((Object) idsTribunais, oab, idCertificado, usuarioSigla, usuarioDescricao, secret);
    }

    private AutenticadorCertificadoCadastro(Object idTribunal, Oab oab, String idCertificado,
            String usuarioSigla, String usuarioDescricao, String secret) {
        this.idTribunal = idTribunal;
        this.oab = oab;
        this.idCertificado = idCertificado;
        this.usuarioSigla = usuarioSigla;
        this.usuarioDescricao = usuarioDescricao;
        this.secret = secret;
    }

    public AutenticadorCertificadoCadastro(String idTribunal, Oab oab, String idCertificado) {
        this(idTribunal, oab, idCertificado, null, null, null);
    }

    @Override
    public String getEndpoint() { return Endpoint.AUTENTICADOR + Endpoint.CERTIFICADO + Endpoint.CADASTRAR; }

    @Override
    public Map<String, Object> getRequest() {
        Map<String, Object> formParams = new LinkedHashMap<>();
        formParams.put("tribunal_id", idTribunal);
        formParams.put("certificado_id", idCertificado);
        formParams.put("oab_numero", oab.getNumero());
        formParams.put("oab_letra", oab.getLetra());
        formParams.put("oab_uf", oab.getUf());
        formParams.put("usuario_sigla", usuarioSigla);
        formParams.put("usuario_descricao", usuarioDescricao);
        formParams.put("secret", secret);

        Map<String, Object> request = new LinkedHashMap<>();
        request.put("form_params", formParams);
        return request;
    }
}
