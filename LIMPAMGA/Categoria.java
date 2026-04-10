public class Categoria {
    public int cod;
    public String nome;
    public String descricao;
    
    public void exibir() {
        System.out.println(cod + " - " + nome);
        System.out.println("Desc: " + descricao);
    }
    
    public String getNomeUpper() {
        return nome.toUpperCase();
    }
    
    public String getNomeLower() {
        return nome.toLowerCase();
    }
    
    public String getNomeTrim() {
        return nome.trim();
    }
}
