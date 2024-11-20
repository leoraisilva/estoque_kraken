package br.com.kraken.estoque.java.service;

import br.com.kraken.estoque.java.model.EstoqueModel;
import br.com.kraken.estoque.java.repository.EstoqueRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class EstoqueService {
    private final EstoqueRepository estoqueRepository;
    public EstoqueService(EstoqueRepository estoqueRepository) {
        this.estoqueRepository = estoqueRepository;
    }

    public EstoqueRepository getEstoqueRepository() {
        return estoqueRepository;
    }

}
