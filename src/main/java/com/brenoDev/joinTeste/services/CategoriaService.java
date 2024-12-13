package com.brenoDev.joinTeste.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brenoDev.joinTeste.dtos.CategoriaDTO;
import com.brenoDev.joinTeste.entities.Categoria;
import com.brenoDev.joinTeste.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    public Categoria create(Categoria obj) {
		obj.setId(null);
		return categoriaRepository.save(obj);
	}

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }
    
    public Categoria findById(Long id) {
		Categoria obj = categoriaRepository.findById(id).get();
		return obj;
				
	}
    
    public Categoria update(Long id, CategoriaDTO objDTO) {
		Categoria obj = findById(id);
		obj.setNome(objDTO.getNome());
		obj.setDescricao(objDTO.getDescricao());
		return categoriaRepository.save(obj);
	}
    
    public void delete(Long id) {
        Categoria objeto = categoriaRepository.findById(id).get();
        categoriaRepository.delete(objeto);
	}
}