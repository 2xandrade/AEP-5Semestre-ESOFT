import java.util.*;

public class Solicitacao {
    public String protocolo;
    public String desc;
    public String end;
    public String bairro;
    public String categoria;
    public StatusEnum status;
    public Usuario usuario;
    public ArrayList<HistoricoStatus> historicoStatuses;
    public ArrayList<String> comentarios;

    public void imprimirDadosSolicitacao() {
        System.out.println("Protocolo: " + protocolo);
        System.out.println("Descricao: " + desc);
        System.out.println("Endereco: " + end);
        System.out.println("Bairro: " + bairro);
        System.out.println("Categoria: " + categoria);
        System.out.println("Status: " + status.getDescricao());  // se status for Enum
        System.out.println("Usuario: " + usuario.nome);
        System.out.println("--- Comentarios ---");
        System.out.println("Comentarios: " + comentarios.size());
        System.out.println("--- Historico ---");
        for(int i = 0; i < historicoStatuses.size(); i++) {
            System.out.println("  " + historicoStatuses.get(i).getInfo());
        }
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public ArrayList<HistoricoStatus> getHistoricoStatuses(){
        return historicoStatuses;
    }
}
