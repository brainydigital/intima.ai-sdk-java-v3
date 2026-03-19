package ai.intima.Servicos.Atualizar.Autenticador;

import java.util.HashMap;
import java.util.Map;
import ai.intima.Servicos.Atualizar.ServicoAtualizacao;
import ai.intima.Servicos.Endpoint;

public class AutenticadorAtivarCapturaIntimacaoAtualizacao implements ServicoAtualizacao {
    private final String id;
    private final Object abas;
    private final Object diasDeCaptura;
    private final Object horariosDeCaptura;
    private final Object capturarProcuradorias;

    public AutenticadorAtivarCapturaIntimacaoAtualizacao(String id, Object abas, Object diasDeCaptura,
            Object horariosDeCaptura, Object capturarProcuradorias) {
        this.id = id;
        this.abas = abas;
        this.diasDeCaptura = diasDeCaptura;
        this.horariosDeCaptura = horariosDeCaptura;
        this.capturarProcuradorias = capturarProcuradorias;
    }

    public AutenticadorAtivarCapturaIntimacaoAtualizacao(String id, Object abas, Object diasDeCaptura, Object horariosDeCaptura) {
        this(id, abas, diasDeCaptura, horariosDeCaptura, false);
    }

    @Override
    public String getEndpoint() { return Endpoint.AUTENTICADOR + "/" + id + "/ativar-captura-intimacao"; }

    @Override
    public Map<String, Object> getRequest() {
        Map<String, Object> request = new HashMap<>();
        request.put("abas", abas);
        request.put("dias_de_captura", diasDeCaptura);
        request.put("horarios_de_captura", horariosDeCaptura);
        request.put("capturar_procuradorias", capturarProcuradorias);
        return request;
    }
}
