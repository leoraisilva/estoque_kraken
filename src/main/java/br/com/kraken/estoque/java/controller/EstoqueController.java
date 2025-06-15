package br.com.kraken.estoque.java.controller;

import br.com.kraken.estoque.java.model.AcessoModel;
import br.com.kraken.estoque.java.model.EstoqueModel;
import br.com.kraken.estoque.java.modelDTO.AcessoDTO;
import br.com.kraken.estoque.java.modelDTO.EstoqueDTO;
import br.com.kraken.estoque.java.service.AcessoService;
import br.com.kraken.estoque.java.service.EstoqueService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/estoque")
public class EstoqueController {
    public final EstoqueService estoqueService;
    public final AcessoService acessoService;

    public EstoqueController (EstoqueService estoqueService, AcessoService acessoService) {
        this.estoqueService = estoqueService;
        this.acessoService = acessoService;
    }

    @PostMapping("/auth/registry")
    public ResponseEntity<Object> clienteEstoque (@RequestBody @Valid AcessoDTO acessoDTO) {
        var acessoModel = new AcessoModel();
        BeanUtils.copyProperties(acessoDTO, acessoModel);
        acessoModel.setDataCadastro(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        return ResponseEntity.status(HttpStatus.CREATED).body(acessoService.getRepository().save(acessoModel));
    }

    @GetMapping
    public ResponseEntity<Object> listarEstoque () {
        return ResponseEntity.status(HttpStatus.OK).body(estoqueService.getEstoqueRepository().findAll());
    }

    @GetMapping ("/{id}")
    public ResponseEntity<Object> encontrarEstoque (@PathVariable (value = "id") UUID id) {
        Optional<EstoqueModel> estoqueModelOptional = estoqueService.getEstoqueRepository().findById(id);
        return estoqueModelOptional.<ResponseEntity<Object>>map(
                estoqueModel -> ResponseEntity.status(HttpStatus.OK).body(estoqueModel)).
                orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found Estoque")
        );
    }

    @DeleteMapping ("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'SALES')")
    public ResponseEntity<Object> deletarEstoque (@PathVariable (value = "id") UUID id ){
        Optional<EstoqueModel> estoqueModelOptional = estoqueService.getEstoqueRepository().findById(id);
        if (!estoqueModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found Estoque");
        estoqueService.getEstoqueRepository().delete(estoqueModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Delete Success!!!");
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'SALES')")
    public ResponseEntity<Object> cadastrarEstoque (@RequestBody @Valid EstoqueDTO estoqueDTO) {
        EstoqueModel estoqueModel = new EstoqueModel ();
        BeanUtils.copyProperties(estoqueDTO, estoqueModel);
        estoqueModel.setDataAtualizacao(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        return ResponseEntity.status(HttpStatus.CREATED).body(estoqueService.getEstoqueRepository().save(estoqueModel));
    }

    @PutMapping ("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'SALES')")
    public ResponseEntity<Object> alterarEstoque (@PathVariable (value = "id") UUID id, @RequestBody @Valid EstoqueDTO estoqueDTO) {
        Optional<EstoqueModel> estoqueModelOptional = estoqueService.getEstoqueRepository().findById(id);
        if(!estoqueModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found Estoque");
        estoqueModelOptional.get().setNomeEstoque(estoqueDTO.nomeEstoque());
        estoqueModelOptional.get().setValorMaximo(estoqueDTO.valorMaximo());
        estoqueModelOptional.get().setValorMinimo(estoqueDTO.valorMinimo());
        estoqueModelOptional.get().setQuantidadeProduto(estoqueDTO.quantidadeProduto());
        estoqueModelOptional.get().setDescricao(estoqueDTO.descricao());
        estoqueModelOptional.get().setDataAtualizacao(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        return ResponseEntity.status(HttpStatus.CREATED).body(estoqueService.getEstoqueRepository().save(estoqueModelOptional.get()));
    }
}
