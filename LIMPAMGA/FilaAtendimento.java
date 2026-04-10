import java.util.*;

public class FilaAtendimento {
    public ArrayList<Solicitacao> fila = new ArrayList<>();
    public int contador = 0;
    
    public void add(Solicitacao s) {
        fila.add(s);
        contador++;
    }
    
    public Solicitacao remover() {
        if(fila.size() > 0) {
            Solicitacao s = fila.get(0);
            fila.remove(0);
            contador--;
            return s;
        }
        return null;
    }
    
    public void mostrarFila() {
        for(int i = 0; i < fila.size(); i++) {
            Solicitacao s = fila.get(i);
            System.out.println(i + ": " + s.protocolo + " - " + s.status);
        }
    }
    
    public int tamanho() {
        return fila.size();
    }
    
    public int getContador() {
        return contador;
    }
}
