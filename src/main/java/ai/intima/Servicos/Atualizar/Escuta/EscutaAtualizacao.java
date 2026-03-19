package ai.intima.Servicos.Atualizar.Escuta;

import java.util.HashMap;
import java.util.Map;
import ai.intima.Servicos.Atualizar.ServicoAtualizacao;
import ai.intima.Servicos.Endpoint;

public class EscutaAtualizacao implements ServicoAtualizacao {
    private final String id;
    private final Object diasDeCaptura;
    private final Object horariosDeCaptura;

    public EscutaAtualizacao(String id, Object diasDeCaptura, Object horariosDeCaptura) {
        this.id = id;
        this.diasDeCaptura = diasDeCaptura;
        this.horariosDeCaptura = horariosDeCaptura;
    }

    @Override
    public String getEndpoint() { return Endpoint.ESCUTA + "/" + id + Endpoint.ATUALIZAR; }

    @Override
    public Map<String, Object> getRequest() {
        Map<String, Object> request = new HashMap<>();
        request.put("dias_de_captura", diasDeCaptura);
        request.put("horarios_de_captura", horariosDeCaptura);
        return request;
    }
}
