import java.util.*;

public class HistoricoStatus {
    public Date data;
    public String status;
    public String observacao;
    
    public String getInfo() {
        return data.toString() + " - " + status + ": " + observacao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }
}
