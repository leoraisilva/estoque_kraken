package br.com.kraken.estoque.java.repository;

import br.com.kraken.estoque.java.model.EstoqueModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface EstoqueRepository extends CrudRepository<EstoqueModel, UUID> {

}
