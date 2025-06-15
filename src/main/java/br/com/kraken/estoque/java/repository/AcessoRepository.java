package br.com.kraken.estoque.java.repository;

import br.com.kraken.estoque.java.model.AcessoModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface AcessoRepository extends CrudRepository<AcessoModel, String> {
    UserDetails findByUsuario(String usuario);
}
