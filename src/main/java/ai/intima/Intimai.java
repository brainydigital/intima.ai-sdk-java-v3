package ai.intima;

import ai.intima.API.API;
import ai.intima.Servicos.Servicos;

public class Intimai {
    private final API api;
    public final Servicos servicos;

    public Intimai(String apiToken) {
        this(apiToken, null);
    }

    public Intimai(String apiToken, Object opcoes) {
        this.api = new API(apiToken, opcoes != null ? opcoes : new Object());
        this.servicos = new Servicos(this.api);
    }
}