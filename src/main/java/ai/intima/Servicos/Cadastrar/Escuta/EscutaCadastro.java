package ai.intima.Servicos.Cadastrar.Escuta;

import java.util.HashMap;
import java.util.Map;
import ai.intima.Servicos.Cadastrar.ServicoCadastro;
import ai.intima.Servicos.Endpoint;

public class EscutaCadastro implements ServicoCadastro {
    private final String idAutenticador;
    private final String numeroDoProcesso;
    private final Object diasDeCaptura;
    private final Object horariosDeCaptura;

    public EscutaCadastro(String idAutenticador, String numeroDoProcesso, Object diasDeCaptura, Object horariosDeCaptura) {
        this.idAutenticador = idAutenticador;
        this.numeroDoProcesso = numeroDoProcesso;
        this.diasDeCaptura = diasDeCaptura;
        this.horariosDeCaptura = horariosDeCaptura;
    }

    @Override
    public String getEndpoint() { return Endpoint.ESCUTA + Endpoint.CADASTRAR; }

    @Override
    public Map<String, Object> getRequest() {
        Map<String, Object> formParams = new HashMap<>();
        formParams.put("autenticacao_id", idAutenticador);
        formParams.put("numero_processo", numeroDoProcesso);
        formParams.put("dias_de_captura", diasDeCaptura);
        formParams.put("horarios_de_captura", horariosDeCaptura);
        Map<String, Object> request = new HashMap<>();
        request.put("form_params", formParams);
        return request;
    }
}
