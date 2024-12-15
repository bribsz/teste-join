package com.brenoDev.joinTeste.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.brenoDev.joinTeste.entities.Categoria;
import com.brenoDev.joinTeste.entities.Produto;
import com.brenoDev.joinTeste.repositories.ProdutoRepository;

class ProdutoServiceTest {

    @InjectMocks
    private ProdutoService produtoService;

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private CategoriaService categoriaService;

    private Produto produto;
    private Categoria categoria;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        categoria = new Categoria(1L, "Categoria Teste", "Descrição Teste");
        produto = new Produto(1L, "Produto Teste", 100.0, categoria);
    }

    @Test
    void testFindById() {
        when(produtoRepository.findById(anyLong())).thenReturn(Optional.of(produto));

        Produto result = produtoService.findById(1L);

        assertNotNull(result);
        assertEquals(produto.getId(), result.getId());
        verify(produtoRepository, times(1)).findById(1L);
    }

    @Test
    void testFindAll() {
        when(categoriaService.findById(anyLong())).thenReturn(categoria);
        when(produtoRepository.findAllByCategoria(anyLong())).thenReturn(List.of(produto));

        List<Produto> result = produtoService.findAll(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(produto.getNome(), result.get(0).getNome());
        verify(categoriaService, times(1)).findById(1L);
        verify(produtoRepository, times(1)).findAllByCategoria(1L);
    }

    @Test
    void testCreate() {
        when(categoriaService.findById(anyLong())).thenReturn(categoria);
        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);

        Produto result = produtoService.create(1L, produto);

        assertNotNull(result);
        assertEquals(produto.getNome(), result.getNome());
        assertEquals(categoria, result.getCategoria());
        verify(categoriaService, times(1)).findById(1L);
        verify(produtoRepository, times(1)).save(any(Produto.class));
    }

    @Test
    void testUpdate() {
        Produto updatedProduto = new Produto(1L, "Produto Atualizado", 150.0, categoria);
        when(produtoRepository.findById(anyLong())).thenReturn(Optional.of(produto));
        when(produtoRepository.save(any(Produto.class))).thenReturn(updatedProduto);

        Produto result = produtoService.update(1L, updatedProduto);

        assertNotNull(result);
        assertEquals("Produto Atualizado", result.getNome());
        assertEquals(150.0, result.getPreco());
        verify(produtoRepository, times(1)).save(any(Produto.class));
    }

    @Test
    void testDelete() {
        when(produtoRepository.findById(anyLong())).thenReturn(Optional.of(produto));
        doNothing().when(produtoRepository).delete(any(Produto.class));

        produtoService.delete(1L);

        verify(produtoRepository, times(1)).findById(1L);
        verify(produtoRepository, times(1)).delete(produto);
    }
}
