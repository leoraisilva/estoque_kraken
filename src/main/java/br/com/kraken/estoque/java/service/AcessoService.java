package br.com.kraken.estoque.java.service;

import br.com.kraken.estoque.java.repository.AcessoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AcessoService  implements UserDetailsService {
    private final AcessoRepository repository;

    public AcessoService(AcessoRepository acessoRepository) {
        this.repository = acessoRepository;
    }

    public AcessoRepository getRepository() {
        return repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsuario(username);
    }
}
