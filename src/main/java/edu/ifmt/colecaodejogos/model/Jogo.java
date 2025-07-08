package edu.ifmt.colecaodejogos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Objects;

// Importações para validação de ano, se necessário (jakarta.validation.constraints.Min, Max)
// Importações para formatadores, se necessário (org.springframework.format.annotation.DateTimeFormat, NumberFormat)

@Entity // Marca esta classe como uma entidade JPA
public class Jogo {

    @Id // Marca o campo 'id' como a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Estratégia de geração de ID pelo banco de dados
    private Long id;

    @NotBlank(message = "O título do jogo é obrigatório.") // Validação: não pode ser nulo, vazio ou conter apenas espaços em branco
    @Size(max = 100, message = "O título não pode exceder 100 caracteres.") // Validação: tamanho máximo de 100 caracteres
    private String titulo;

    @NotBlank(message = "O gênero é obrigatório.")
    @Size(max = 50, message = "O gênero não pode exceder 50 caracteres.")
    private String genero;

    @NotNull(message = "O ano de lançamento é obrigatório.")
    @Min(value = 1980, message = "O ano de lançamento deve ser a partir de 1980.") // Ano mínimo
    @Max(value = 2025, message = "O ano de lançamento não pode ser no futuro.") // Ano máximo (ajuste conforme o ano atual)
    private Integer anoLancamento;

    @NotNull(message = "A plataforma é obrigatória.")
    @Enumerated(EnumType.STRING) // Armazena o enum como String no banco de dados
    private Plataforma plataforma;

    @NotNull(message = "O status é obrigatório.")
    @Enumerated(EnumType.STRING) // Armazena o enum como String no banco de dados
    private Status status;

    // Construtor padrão (necessário para JPA)
    public Jogo() {
    }

    // --- Getters e Setters para todos os campos ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(Integer anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public Plataforma getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(Plataforma plataforma) {
        this.plataforma = plataforma;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Método auxiliar para verificar se o status do jogo é "Para Jogar".
     * Segue a convenção de JavaBeans para métodos booleanos (prefixo 'is').
     * Usado para estilização condicional no Thymeleaf.
     * @return true se o status for PARA_JOGAR, false caso contrário.
     */
    public boolean isParaJogar() {
        return Status.PARA_JOGAR.equals(this.status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Jogo jogo = (Jogo) o;

        /* --> IntelliJ deu boa dica para usar equals ao invés da expressão:
         'id != null ? id.equals(jogo.id) : jogo.id == null'  <-- */

        return Objects.equals(id, jogo.id);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
