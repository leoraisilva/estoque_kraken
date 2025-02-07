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
    @Column(name = "valor_minimo", nullable = false)
    private int valorMaximo;
    @Column(name = "valor_maximo", nullable = false)
    private int valorMinimo;
    @Column(name = "quantidade_produto", nullable = false )
    private int quantidadeProduto;
    @Column(name = "descricao")
    private String descricao;
    @Column (name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    public EstoqueModel(String nomeEstoque, int valorMaximo, int valorMinimo, int quantidadeProduto, String descricao, LocalDateTime dataAtualizacao) {
        this.nomeEstoque = nomeEstoque;
        this.valorMaximo = valorMaximo;
        this.valorMinimo = valorMinimo;
        this.quantidadeProduto = quantidadeProduto;
        this.descricao = descricao;
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

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public int getValorMaximo() {
        return valorMaximo;
    }

    public void setValorMaximo(int valorMaximo) {
        this.valorMaximo = valorMaximo;
    }

    public int getValorMinimo() {
        return valorMinimo;
    }

    public void setValorMinimo(int valorMinimo) {
        this.valorMinimo = valorMinimo;
    }

    public int getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(int quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
