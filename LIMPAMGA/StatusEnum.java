public enum StatusEnum {
    ABERTO(0, "Aberto"),
    EM_ANALISE(1, "Em analise"),
    EM_ANDAMENTO(2, "Em andamento"),
    CONCLUIDO(3, "Concluido"),
    CANCELADO(4, "Cancelado");

    private int codigo;
    private String descricao;

    StatusEnum(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static StatusEnum fromCodigo(int codigo) {
        for (StatusEnum status : values()) {
            if (status.codigo == codigo) {
                return status;
            }
        }
        return null;
    }
}
