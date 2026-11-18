package ai.intima;

import java.util.function.Supplier;

import ai.intima.API.API;
import ai.intima.Servicos.Servicos;

public class Intimai {
    private final API api;
    public final Servicos servicos;

    public Intimai(String apiToken) {
        this(apiToken, null);
    }

    public Intimai(String apiToken, Object opcoes) {
        this.api = new API(apiToken, opcoes);
        this.servicos = new Servicos(this.api);
    }

    public void definirCodigo2fa(String codigo) {
        api.definirCodigo2fa(codigo);
    }

    public void definirCodigo2fa(Supplier<String> codigo) {
        api.definirCodigo2fa(codigo);
    }
}
