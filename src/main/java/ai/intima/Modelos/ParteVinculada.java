package ai.intima.Modelos;

public class ParteVinculada {
    private final String nome;
    private final String participacao;

    public ParteVinculada(String nome, String participacao) {
        this.nome = nome;
        this.participacao = participacao;
    }

    public ParteVinculada(String nome) {
        this(nome, null);
    }

    public String getNome() { return nome; }
    public String getParticipacao() { return participacao; }
}
