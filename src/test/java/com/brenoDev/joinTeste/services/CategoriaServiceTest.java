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

import com.brenoDev.joinTeste.dtos.CategoriaDTO;
import com.brenoDev.joinTeste.entities.Categoria;
import com.brenoDev.joinTeste.repositories.CategoriaRepository;

class CategoriaServiceTest {

    @InjectMocks
    private CategoriaService categoriaService;

    @Mock
    private CategoriaRepository categoriaRepository;

    private Categoria categoria;
    private CategoriaDTO categoriaDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        categoria = new Categoria(1L, "Categoria Teste", "Descrição Teste");
        categoriaDTO = new CategoriaDTO(categoria);
    }

    @Test
    void testCreate() {
        when(categoriaRepository.save(any(Categoria.class))).thenReturn(categoria);

        Categoria result = categoriaService.create(categoria);

        assertNotNull(result);
        assertEquals(categoria.getId(), result.getId());
        verify(categoriaRepository, times(1)).save(any(Categoria.class));
    }

    @Test
    void testFindAll() {
        when(categoriaRepository.findAll()).thenReturn(List.of(categoria));

        List<Categoria> result = categoriaService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(categoriaRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(categoriaRepository.findById(anyLong())).thenReturn(Optional.of(categoria));

        Categoria result = categoriaService.findById(1L);

        assertNotNull(result);
        assertEquals(categoria.getId(), result.getId());
        verify(categoriaRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdate() {
        when(categoriaRepository.findById(anyLong())).thenReturn(Optional.of(categoria));
        when(categoriaRepository.save(any(Categoria.class))).thenReturn(categoria);

        Categoria result = categoriaService.update(1L, categoriaDTO);

        assertNotNull(result);
        assertEquals("Categoria Teste", result.getNome());
        verify(categoriaRepository, times(1)).save(any(Categoria.class));
    }

    @Test
    void testDelete() {
        when(categoriaRepository.findById(anyLong())).thenReturn(Optional.of(categoria));
        doNothing().when(categoriaRepository).delete(any(Categoria.class));

        categoriaService.delete(1L);

        verify(categoriaRepository, times(1)).delete(any(Categoria.class));
    }
}

