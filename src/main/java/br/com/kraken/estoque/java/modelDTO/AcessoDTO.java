package br.com.kraken.estoque.java.modelDTO;

import br.com.kraken.estoque.java.model.Rules;

public record AcessoDTO(String usuarioId, String usuario, Rules rules) {
}
