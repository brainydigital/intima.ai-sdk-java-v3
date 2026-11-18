package ai.intima.Servicos;

public final class Endpoint {
    private Endpoint() {}

    public static final String BUSCAR = "/buscar";
    public static final String CADASTRAR = "/cadastrar";
    public static final String EXCLUIR = "/excluir";
    public static final String ATUALIZAR = "/atualizar";
    public static final String SOLICITAR = "/solicitar";
    public static final String PRIMEIRA_ETAPA = "/primeira-etapa";
    public static final String SEGUNDA_ETAPA = "/segunda-etapa";
    public static final String MARCAR_COMO_REVISADA = "/marcar-como-revisada";
    public static final String SOLICITAR_ATUALIZACAO = "/solicitar-atualizacao";
    public static final String SOLICITAR_CADASTRO = "/solicitar-cadastro";
    public static final String SOLICITAR_EXCLUSAO = "/solicitar-exclusao";

    public static final String ANDAMENTO = "/andamento-processual";
    public static final String CONSULTA = "/consulta-processual";
    public static final String CONSULTA_PRE_ANALISE = "/pre-consulta-processual";
    public static final String COPIA = "/copia-processual";
    public static final String ESCUTA = "/escuta-processual";
    public static final String INFORMACAO = "/informacao-processual";
    public static final String INTIMACAO = "/intimacao";
    public static final String PROTOCOLO = "/protocolo-processual";
    public static final String PROTOCOLO_HABILITACAO = "/protocolo-habilitacao";

    public static final String ESAJ = "/esaj";
    public static final String EPROC = "/eproc";
    public static final String TJRJ = "/tjrj";
    public static final String PJE = "/pje";
    public static final String PROJUDI = "/projudi";

    public static final String AUTENTICADOR = "/autenticador";
    public static final String REGULAR = "/regular";
    public static final String CERTIFICADO = "/certificado";

    public static final String NOTIFICACAO = "/notificacao";
    public static final String EMAIL = "/email";
    public static final String WEBHOOK = "/webhook";

    public static final String CREDITO = "/credito";
    public static final String COTACAO_ACOES = "/cotacao-acoes";
    public static final String HISTORICO = "/historico";
    public static final String SALDO = "/saldo";
    public static final String TRANSACAO = "/transacao";
    public static final String TRIBUNAL = "/tribunal";
    public static final String USUARIO = "/usuario";
    public static final String ACAO = "/acao";
    public static final String TENTAR_NOVAMENTE = "/tentar-novamente";
    public static final String RESULTADO = "/resultado";
    public static final String ALTERAR_PARA_REGULAR = "/alterar-para-regular";
    public static final String ALTERAR_PARA_CERTIFICADO = "/alterar-para-certificado";
    public static final String TIPOS_DOCUMENTO = "/tipos-documento";
    public static final String CLASSES = "/classes";
    public static final String EVENTOS = "/eventos";
    public static final String TIPOS_DOCUMENTO_MENSAGEM_GERAL = "/tipos-documento-mensagem-geral";
    public static final String CONFIRMAR_CONSULTA = "/confirmar-consulta";
    public static final String CANCELAR_CONSULTA = "/cancelar-consulta";
    public static final String ATUALIZAR_PERFIL = "/atualizar-perfil";
    public static final String CAPTURAR_INTIMACOES = "/capturar-intimacoes";
    public static final String HABILITAR = "/habilitar";
    public static final String DESABILITAR = "/desabilitar";
    public static final String DOIS_FATORES = "/2fa/armazenar";
    public static final String CONSUMO_MENSAL = "/consumo-mensal";
    public static final String CAPTURA_AGENDADA = "/captura-agendada";
    public static final String CODIGO_AJUDA = "/codigo-ajuda";
}
