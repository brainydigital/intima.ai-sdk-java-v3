package ai.intima.Utilidades;

public final class StatusAcao {
    private StatusAcao() {}

    public static final int PENDENTE = 0;
    public static final int FINALIZADO = 1;
    public static final int PROCESSANDO = 2;
    public static final int ERRO = 3;
    public static final int NA_FILA = 4;
    public static final int PREPARANDO_DOCUMENTOS = 5;
    public static final int ESPERANDO_ACAO_USUARIO = 6;
    public static final int TENTANDO_NOVAMENTE = 7;
    public static final int ESPERANDO = 8;
    public static final int ESPERANDO_ARQUIVOS = 9;
    public static final int ESPERANDO_2FA = 10;
    public static final int USUARIO_SELECIONADO_NAO_ENCONTRADO = 11;
    public static final int AGUARDANDO_DADOS_FISCAIS = 12;
    public static final int AGUARDANDO_CONFIRMACAO = 13;
    public static final int CANCELADA = 14;
}
