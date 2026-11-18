package ai.intima.Servicos.Atualizar.Autenticador;

import java.util.LinkedHashMap;
import java.util.Map;
import ai.intima.Servicos.Atualizar.ServicoAtualizacao;
import ai.intima.Servicos.Endpoint;

public class AutenticadorPerfilAtualizacao implements ServicoAtualizacao {
    private final String id;
    private final String usuarioSigla;
    private final String usuarioDescricao;

    public AutenticadorPerfilAtualizacao(String id, String usuarioSigla, String usuarioDescricao) {
        this.id = id;
        this.usuarioSigla = usuarioSigla;
        this.usuarioDescricao = usuarioDescricao;
    }

    @Override
    public String getEndpoint() { return Endpoint.AUTENTICADOR + "/" + id + Endpoint.ATUALIZAR_PERFIL; }

    @Override
    public Map<String, Object> getRequest() {
        Map<String, Object> formParams = new LinkedHashMap<>();
        formParams.put("usuario_sigla", usuarioSigla);
        formParams.put("usuario_descricao", usuarioDescricao);

        Map<String, Object> request = new LinkedHashMap<>();
        request.put("form_params", formParams);
        return request;
    }
}
