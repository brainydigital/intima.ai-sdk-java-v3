package ai.intima.Servicos.Cadastrar.Autenticador;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import ai.intima.Servicos.Cadastrar.ServicoCadastro;
import ai.intima.Servicos.Endpoint;
import ai.intima.DadosTipo.Oab;

public class AutenticadorRegularCadastro implements ServicoCadastro {
    private final Object idTribunal;
    private final Oab oab;
    private final String usuario;
    private final String senha;
    private final String idCertificado;
    private final String usuarioSigla;
    private final String usuarioDescricao;
    private final String secret;

    public AutenticadorRegularCadastro(String idTribunal, Oab oab, String usuario, String senha,
            String idCertificado, String usuarioSigla, String usuarioDescricao, String secret) {
        this((Object) idTribunal, oab, usuario, senha, idCertificado, usuarioSigla, usuarioDescricao, secret);
    }

    public AutenticadorRegularCadastro(List<String> idsTribunais, Oab oab, String usuario, String senha,
            String idCertificado, String usuarioSigla, String usuarioDescricao, String secret) {
        this((Object) idsTribunais, oab, usuario, senha, idCertificado, usuarioSigla, usuarioDescricao, secret);
    }

    private AutenticadorRegularCadastro(Object idTribunal, Oab oab, String usuario, String senha,
            String idCertificado, String usuarioSigla, String usuarioDescricao, String secret) {
        this.idTribunal = idTribunal;
        this.oab = oab;
        this.usuario = usuario;
        this.senha = senha;
        this.idCertificado = idCertificado;
        this.usuarioSigla = usuarioSigla;
        this.usuarioDescricao = usuarioDescricao;
        this.secret = secret;
    }

    public AutenticadorRegularCadastro(String idTribunal, Oab oab, String usuario, String senha) {
        this(idTribunal, oab, usuario, senha, null, null, null, null);
    }

    @Override
    public String getEndpoint() { return Endpoint.AUTENTICADOR + Endpoint.REGULAR + Endpoint.CADASTRAR; }

    @Override
    public Map<String, Object> getRequest() {
        Map<String, Object> formParams = new LinkedHashMap<>();
        formParams.put("tribunal_id", idTribunal);
        formParams.put("usuario", usuario);
        formParams.put("senha", senha);
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
