package br.com.kraken.estoque.java.model;

import jakarta.persistence.*;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table (name = "Estoque")
public class EstoqueModel {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private UUID estoqueid;
    @Column(name = "nome_estoque", nullable = false, unique = true)
    private String nomeEstoque;
    @Column (name = "quantidade_posicao")
    private int quantidadePosicao;
    @ElementCollection
    @Column (name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    public EstoqueModel(String nomeEstoque, int quantidadePosicao, LocalDateTime dataAtualizacao) {
        this.nomeEstoque = nomeEstoque;
        this.quantidadePosicao = quantidadePosicao;
        this.dataAtualizacao = dataAtualizacao;
    }

    public EstoqueModel(){}

    public UUID getEstoqueid() {
        return estoqueid;
    }

    public void setEstoqueid(UUID estoqueid) {
        this.estoqueid = estoqueid;
    }

    public String getNomeEstoque() {
        return nomeEstoque;
    }

    public void setNomeEstoque(String nomeEstoque) {
        this.nomeEstoque = nomeEstoque;
    }

    public int getQuantidadePosicao() {
        return quantidadePosicao;
    }

    public void setQuantidadePosicao(int quantidadePosicao) {
        this.quantidadePosicao = quantidadePosicao;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
}
