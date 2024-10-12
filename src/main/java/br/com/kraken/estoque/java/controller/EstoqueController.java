package br.com.kraken.estoque.java.controller;

import br.com.kraken.estoque.java.model.EstoqueModel;
import br.com.kraken.estoque.java.modelDTO.EstoqueDTO;
import br.com.kraken.estoque.java.service.EstoqueService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/estoque")
public class EstoqueController {
    public final EstoqueService estoqueService;

    public EstoqueController (EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
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
    public ResponseEntity<Object> deletarEstoque (@PathVariable (value = "id") UUID id ){
        Optional<EstoqueModel> estoqueModelOptional = estoqueService.getEstoqueRepository().findById(id);
        if (!estoqueModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found Estoque");
        estoqueService.getEstoqueRepository().delete(estoqueModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Delete Success!!!");
    }

    @PostMapping
    public ResponseEntity<Object> cadastrarEstoque (@RequestBody @Valid EstoqueDTO estoqueDTO) {
        EstoqueModel estoqueModel = new EstoqueModel ();
        if(estoqueService.containerPosicao(estoqueDTO.posicao()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict, Already registry posicao");
        BeanUtils.copyProperties(estoqueDTO, estoqueModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(estoqueService.getEstoqueRepository().save(estoqueModel));
    }

    @PutMapping ("/{id}")
    public ResponseEntity<Object> alterarEstoque (@PathVariable (value = "id") UUID id, @RequestBody @Valid EstoqueDTO estoqueDTO) {
        Optional<EstoqueModel> estoqueModelOptional = estoqueService.getEstoqueRepository().findById(id);
        if(estoqueService.containerPosicao(estoqueDTO.posicao()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict, Already registry posicao");
        estoqueModelOptional.get().setNomeEstoque(estoqueDTO.nomeEstoque());
        estoqueModelOptional.get().setQuantidadePosicao(estoqueDTO.quantidadePosicao());
        estoqueModelOptional.get().setPosicao(estoqueDTO.posicao());
        return ResponseEntity.status(HttpStatus.CREATED).body(estoqueService.getEstoqueRepository().save(estoqueModelOptional.get()));
    }
}
