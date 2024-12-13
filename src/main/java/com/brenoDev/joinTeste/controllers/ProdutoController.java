package com.brenoDev.joinTeste.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.brenoDev.joinTeste.dtos.ProdutoDTO;
import com.brenoDev.joinTeste.entities.Produto;
import com.brenoDev.joinTeste.services.ProdutoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping(value = "/{id}")
	public ResponseEntity<Produto> findById(@PathVariable Long id) {
		Produto obj = produtoService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	//endpoint -> localhost:8080/livros?categoria=1
	@GetMapping()
	public ResponseEntity<List<ProdutoDTO>> findAll(@RequestParam(value = "categoria", defaultValue = "0") Long id_cat) {
		List<Produto> list = produtoService.findAll(id_cat);
		List<ProdutoDTO> listDTO = list.stream().map(obj -> new ProdutoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	// RequestParam é pq precisa do "id" de categoria que é passado na URL para ser criado o livro
	@PostMapping
	public ResponseEntity<Produto> create(@RequestParam(value = "categoria", defaultValue = "0") Long id_cat, @Valid @RequestBody Produto obj) {
		Produto newObj = produtoService.create(id_cat, obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/livros/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Produto> update(@PathVariable Long id, @Valid @RequestBody Produto obj) {
		Produto newObj = produtoService.update(id, obj);
		return ResponseEntity.ok().body(newObj);
	}
	
	@PatchMapping(value = "/{id}")
	public ResponseEntity<Produto> updatePatch(@PathVariable Long id, @Valid @RequestBody Produto obj) {
		Produto newObj = produtoService.update(id, obj);
		return ResponseEntity.ok().body(newObj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		produtoService.delete(id);
		return ResponseEntity.noContent().build();
	}
}