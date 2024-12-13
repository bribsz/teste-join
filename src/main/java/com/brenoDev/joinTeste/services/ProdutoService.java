package com.brenoDev.joinTeste.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brenoDev.joinTeste.entities.Categoria;
import com.brenoDev.joinTeste.entities.Produto;
import com.brenoDev.joinTeste.repositories.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;
    
    @Autowired
    private CategoriaService categoriaService;

    public Produto findById(Long id) {
		Produto obj = produtoRepository.findById(id).get();
		return obj;
	}

	public List<Produto> findAll(Long id_cat) {
		categoriaService.findById(id_cat);
		return produtoRepository.findAllByCategoria(id_cat);
	}

	public Produto update(Long id, Produto obj) {
		Produto newObj = findById(id);
		updateData(newObj, obj);
		return produtoRepository.save(newObj);
	}

	private void updateData(Produto newObj, Produto obj) {
		newObj.setNome(obj.getNome());
		newObj.setPreco(obj.getPreco());
	}

	public Produto create(Long id_cat, Produto obj) {
		obj.setId(null);
		Categoria cat = categoriaService.findById(id_cat);
		obj.setCategoria(cat);
		return produtoRepository.save(obj);
	}

	public void delete(Long id) {
		Produto obj = findById(id);
		produtoRepository.delete(obj);
	}

}
