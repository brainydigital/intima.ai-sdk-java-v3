package ai.intima.DadosTipo;

public class Oab {
    private final String numero;
    private final String letra;
    private final String uf;

    public Oab(String numero, String uf) {
        this(numero, null, uf);
    }

    public Oab(String numero, String letra, String uf) {
        this.numero = numero;
        this.letra = letra;
        this.uf = uf;
    }

    public String getNumero() { return numero; }
    public String getLetra() { return letra != null ? letra : ""; }
    public String getUf() { return uf; }
}
