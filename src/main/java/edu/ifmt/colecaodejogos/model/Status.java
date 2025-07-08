package edu.ifmt.colecaodejogos.model;

/**
 * Enum que representa o status de um jogo na coleção.
 */
public enum Status {
    PARA_JOGAR("Para Jogar"),
    JOGANDO("Jogando"),
    COMPLETADO("Concluído");

    private String description; // Descrição amigável para exibição

    /**
     * Construtor do enum.
     * @param description a descrição do status.
     */
    Status(String description) {
        this.description = description;
    }

    /**
     * Retorna a descrição amigável do status.
     * @return a descrição do status.
     */
    public String getDescricao() {
        return description;
    }
}
