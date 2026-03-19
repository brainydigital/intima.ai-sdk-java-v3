package ai.intima.Servicos.Atualizar.Autenticador;

import java.util.HashMap;
import java.util.Map;
import ai.intima.Servicos.Atualizar.ServicoAtualizacao;
import ai.intima.Servicos.Endpoint;
import ai.intima.DadosTipo.Oab;

public class AutenticadorOabAtualizacao implements ServicoAtualizacao {
    private final String id;
    private final Oab oab;

    public AutenticadorOabAtualizacao(String id, Oab oab) {
        this.id = id;
        this.oab = oab;
    }

    @Override
    public String getEndpoint() { return Endpoint.AUTENTICADOR + "/" + id + Endpoint.ATUALIZAR + "-oab"; }

    @Override
    public Map<String, Object> getRequest() {
        Map<String, Object> request = new HashMap<>();
        request.put("oab_numero", oab.getNumero());
        request.put("oab_letra", oab.getLetra());
        request.put("oab_uf", oab.getUf());
        return request;
    }
}
