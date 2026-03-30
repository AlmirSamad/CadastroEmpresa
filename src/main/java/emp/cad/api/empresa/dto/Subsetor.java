package emp.cad.api.empresa.dto;

public enum Subsetor {
    ELETROELETRONICO("Eletroeletrônico"),
    RELOJOEIRO("Relojoeiro"),
    DUASRODAS("Duas Rodas"),
    TERMOPLASTICO("Termoplástico"),
    BEBIDAS("Bebidas"),
    METALURGICO("Metalúrgico"),
    MECANICO("Mecânico"),
    MADEIREIRO("Madeireiro"),
    PAPELEPAPELAO("Papel e Papelão"),
    COUROSESIMILARES("Couros e Similares"),
    QUIMICO("Químico"),
    MATERIALDELIMPEZAEVELAS("Material de Limpeza e Velas"),
    VESTUARIOSECALCADOS("Vestuário e Calçados"),
    PRODUTOSALIMENTICIOS("Produtos Alimentícios"),
    EDITORIALEGRAFICO("Editorial e Gráfico"),
    TEXTIL("Têxtil"),
    MINERALNAOMETALICO("Mineral Não Metálico"),
    MOBILIARIO("Mobiliário"),
    BENEFICIAMENTODEBORRACHA("Beneficiamento de Borracha"),
    OTICO("Ótico"),
    BRINQUEDOS("Brinquedos"),
    ISQUEIROSECANETAS("Isqueiros e Canetas"),
    NAVAL("Naval"),
    DIVERSOS("Diversos");

    private final String descricao;

    Subsetor(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
