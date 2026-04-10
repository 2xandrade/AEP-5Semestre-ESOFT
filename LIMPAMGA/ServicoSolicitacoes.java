import java.util.*;

public class ServicoSolicitacoes {
    public ArrayList<Solicitacao> lista = new ArrayList<>();
    private int num = 1000;
    
    public String gerarProt() {
        num++;
        return "LIMPA-" + num + "-" + System.currentTimeMillis() % 1000;
    }
    
    public void cadastrar(Solicitacao solicitacao) {
        lista.add(solicitacao);
    }

    //
    public Optional<Solicitacao> buscarProtocolo(String protocolo) {
        for (Solicitacao solicitacao : lista) {
            if (solicitacao.getProtocolo().equals(protocolo)) {
                return Optional.of(solicitacao);
            }
        }
        return Optional.empty();
    }
    
    public List<Solicitacao> listarPorStatus(StatusEnum status) {
        List<Solicitacao> resultado = new ArrayList<>();
        for (Solicitacao solicitacoes : lista){
            if ((solicitacoes.getStatus().equals(status))){
                resultado.add(solicitacoes);
            }
        }
        return resultado;
    }
    
    public ArrayList<Solicitacao> listarPorBairro(String bairro) {
        ArrayList<Solicitacao> resultado = new ArrayList<>();
        for(int i = 0; i < lista.size(); i++) {
            Solicitacao solicitacao = lista.get(i);
            if(solicitacao.bairro.equalsIgnoreCase(bairro)) {
                resultado.add(solicitacao);
            }
        }
        return resultado;
    }
    
    public boolean atualizarStatus(String protocolo, StatusEnum novoStatus) {
        Optional<Solicitacao> resultado = buscarProtocolo(protocolo);

        if (resultado.isPresent()){
            Solicitacao solicitacao = resultado.get();
            StatusEnum statusAntigo = solicitacao.getStatus();
            solicitacao.setStatus(novoStatus);


            HistoricoStatus historicoStatus = new HistoricoStatus();
            historicoStatus.data = new Date();
            historicoStatus.status = novoStatus.getDescricao();
            historicoStatus.observacao = "Status alterado de '" + statusAntigo.getDescricao() +
                    "' para '" + novoStatus.getDescricao() + "'";
            solicitacao.historicoStatuses.add(historicoStatus);
            return true;
        }
        return false;
    }
    
    public void addComentario(String protocolo, String comentario) {
        Optional<Solicitacao> resultado = buscarProtocolo(protocolo);
        if (resultado.isPresent()) {
            Solicitacao solicitacao = resultado.get();
            solicitacao.comentarios.add(comentario);
        }
    }
    
    public int total() {
        return lista.size();
    }
    
    public int totalPorCategoria(String cat) {
        int total = 0;
        for(int i = 0; i < lista.size(); i++) {
            if(lista.get(i).categoria.equals(cat)) {
                total++;
            }
        }
        return total;
    }
    
    public void imprimirTodos() {
        for(int i = 0; i < lista.size(); i++) {
            Solicitacao s = lista.get(i);
            System.out.println("---");
            System.out.println("Protocolo: " + s.protocolo);
            System.out.println("Status: " + s.status);
            System.out.println("Usuario: " + s.usuario.nome);
        }
    }
}
