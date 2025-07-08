package edu.ifmt.colecaodejogos.model;

public enum Plataforma {
    PC("PC"),
    PLAYSTATION("PlayStation"),
    XBOX("Xbox"),
    NINTENDO_SWITCH("Nintendo Switch");

    private String descricao; // Descrição amigável para exibição

    /** Construtor
     * Construtor do enum.
     * @param descricao a descrição da plataforma.
     */
    Plataforma(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Retorna a descrição amigável da plataforma.
     * @return a descrição da plataforma.
     */
    public String getDescricao() {
        return descricao;
    }
}
