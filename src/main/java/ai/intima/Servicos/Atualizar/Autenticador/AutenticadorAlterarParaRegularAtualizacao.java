package ai.intima.Servicos.Atualizar.Autenticador;

import java.util.LinkedHashMap;
import java.util.Map;
import ai.intima.Servicos.Atualizar.ServicoAtualizacao;
import ai.intima.Servicos.Endpoint;

public class AutenticadorAlterarParaRegularAtualizacao implements ServicoAtualizacao {
    private final String id;
    private final String usuario;
    private final String senha;
    private final String idCertificado;

    public AutenticadorAlterarParaRegularAtualizacao(String id, String usuario, String senha, String idCertificado) {
        this.id = id;
        this.usuario = usuario;
        this.senha = senha;
        this.idCertificado = idCertificado;
    }

    public AutenticadorAlterarParaRegularAtualizacao(String id, String usuario, String senha) {
        this(id, usuario, senha, null);
    }

    @Override
    public String getEndpoint() { return Endpoint.AUTENTICADOR + Endpoint.REGULAR + "/" + id + Endpoint.ALTERAR_PARA_REGULAR; }

    @Override
    public Map<String, Object> getRequest() {
        Map<String, Object> formParams = new LinkedHashMap<>();
        formParams.put("usuario", usuario);
        formParams.put("senha", senha);
        formParams.put("certificado_id", idCertificado);

        Map<String, Object> request = new LinkedHashMap<>();
        request.put("form_params", formParams);
        return request;
    }
}
