package ai.intima.Servicos.Atualizar.Autenticador;

import java.util.HashMap;
import java.util.Map;
import ai.intima.Servicos.Atualizar.ServicoAtualizacao;
import ai.intima.Servicos.Endpoint;

public class AutenticadorAlterarParaCertificadoAtualizacao implements ServicoAtualizacao {
    private final String id;
    private final String idCertificado;

    public AutenticadorAlterarParaCertificadoAtualizacao(String id, String idCertificado) {
        this.id = id;
        this.idCertificado = idCertificado;
    }

    @Override
    public String getEndpoint() { return Endpoint.AUTENTICADOR + Endpoint.CERTIFICADO + "/" + id + Endpoint.ALTERAR_PARA_CERTIFICADO; }

    @Override
    public Map<String, Object> getRequest() {
        Map<String, Object> formParams = new HashMap<>();
        formParams.put("certificado_id", idCertificado);
        Map<String, Object> request = new HashMap<>();
        request.put("form_params", formParams);
        return request;
    }
}
