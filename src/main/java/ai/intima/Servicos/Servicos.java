package ai.intima.Servicos;

import java.util.HashMap;
import java.util.Map;

import ai.intima.API.API;
import ai.intima.Exceptions.APIRequestException;
import ai.intima.Servicos.Atualizar.ServicoAtualizacao;
import ai.intima.Servicos.Buscar.ServicoBusca;
import ai.intima.Servicos.Cadastrar.ServicoCadastro;
import ai.intima.Servicos.Excluir.ServicoExclusao;
import ai.intima.Servicos.Resultado.ServicoResultado;
import ai.intima.Servicos.Solicitar.ServicoSolicitacao;
import ai.intima.Servicos.SolicitarAtualizacao.ServicoSolicitaAtualizacao;
import ai.intima.Servicos.SolicitarCadastro.ServicoSolicitacaoCadastro;
import ai.intima.Servicos.SolicitarExclusao.ServicoSolicitacaoExclusao;
import ai.intima.Servicos.TentarNovamente.ServicoTentaNovamente;

public class Servicos {
    private final API api;

    public Servicos(API api) {
        this.api = api;
    }

    public Map<String, Object> solicitar(ServicoSolicitacao servico) throws APIRequestException {
        return api.post(servico.getEndpoint(), servico.getRequest());
    }

    public Map<String, Object> buscar(ServicoBusca servico) throws APIRequestException {
        return buscar(servico, 1, new HashMap<>());
    }

    public Map<String, Object> buscar(ServicoBusca servico, int pagina, Map<String, String> filtros) throws APIRequestException {
        return api.get(servico.getEndpoint(), pagina, filtros);
    }

    public Map<String, Object> cadastrar(ServicoCadastro servico) throws APIRequestException {
        return api.post(servico.getEndpoint(), servico.getRequest());
    }

    public Map<String, Object> atualizar(ServicoAtualizacao servico) throws APIRequestException {
        return api.put(servico.getEndpoint(), servico.getRequest());
    }

    public Map<String, Object> excluir(ServicoExclusao servico) throws APIRequestException {
        return api.delete(servico.getEndpoint());
    }

    public Map<String, Object> solicitarCadastro(ServicoSolicitacaoCadastro servico) throws APIRequestException {
        return api.post(servico.getEndpoint(), servico.getRequest());
    }

    public Map<String, Object> solicitarAtualizacao(ServicoSolicitaAtualizacao servico) throws APIRequestException {
        return api.post(servico.getEndpoint(), servico.getRequest());
    }

    public Map<String, Object> solicitarExclusao(ServicoSolicitacaoExclusao servico) throws APIRequestException {
        return api.delete(servico.getEndpoint());
    }

    public Map<String, Object> resultado(ServicoResultado servico) throws APIRequestException {
        return api.get(servico.getEndpoint());
    }

    public Map<String, Object> tentarNovamente(ServicoTentaNovamente servico) throws APIRequestException {
        return api.get(servico.getEndpoint());
    }
}
