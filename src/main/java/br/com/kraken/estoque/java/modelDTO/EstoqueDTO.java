package br.com.kraken.estoque.java.modelDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record EstoqueDTO(String nomeEstoque, int quantidadePosicao, LocalDateTime dataAtualizacao) {
}
