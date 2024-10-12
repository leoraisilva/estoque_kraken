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

    public boolean containerPosicao(List<String> list) {
        List<EstoqueModel> listAll = StreamSupport.stream(estoqueRepository.findAll().spliterator(), false).toList();
        List<String> posicaoAll = listAll
                .stream()
                .flatMap(posicao -> posicao.getPosicao().stream())
                .toList();
        for (String value : list){
            for (int i = 0; i < posicaoAll.size(); i++) {
                if (value.contains(posicaoAll.get(i)))
                    return true;
            }
        }
        return false;
    }

}
