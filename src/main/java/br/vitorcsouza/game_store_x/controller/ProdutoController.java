package br.vitorcsouza.game_store_x.controller;


import br.vitorcsouza.game_store_x.model.Produto;
import br.vitorcsouza.game_store_x.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @PostMapping
    public ResponseEntity<Produto> create(@RequestBody @Valid Produto produto, UriComponentsBuilder uri) {
        repository.save(produto);
        URI address = uri.path("produtos/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(address).body(produto);
    }

    @GetMapping
    public ResponseEntity<List<Produto>> findAll() {
        List<Produto> produtos = repository.findAll();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> GetById(@PathVariable Long id) {
        Produto produto = repository.findById(id).orElseThrow(NoSuchElementException::new);

        return ResponseEntity.ok(produto);
    }

    @GetMapping("/pesquisar")
    public ResponseEntity<List<Produto>> GetByNome(@RequestParam String nome) {
        List<Produto> produtos = repository.findAllByNomeContainingIgnoreCase(nome);

        return ResponseEntity.ok(produtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> put(@PathVariable Long id, @RequestBody @Valid Produto produto) {
        Produto reference = repository.getReferenceById(id);
        reference.update(produto);
        return ResponseEntity.ok(repository.save(reference));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean exists = repository.existsById(id);

        if (!exists) {
            throw new NoSuchElementException();
        }

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
