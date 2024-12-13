package com.brenoDev.joinTeste.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.brenoDev.joinTeste.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	@Query("SELECT obj FROM Produto obj WHERE obj.categoria.id = :id_cat ORDER BY nome")
	List<Produto> findAllByCategoria(@Param(value = "id_cat")Long id_cat);
}
