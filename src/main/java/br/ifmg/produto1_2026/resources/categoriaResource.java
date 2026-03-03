package br.ifmg.produto1_2026.resources;

import br.ifmg.produto1_2026.entities.Categoria;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class categoriaResource {
    @GetMapping
    public ResponseEntity<List<Categoria>> categoria() {
        Categoria categoria = new Categoria(1L, "notebook");
        Categoria categoria1 = new Categoria(2L, "celular");
        Categoria categoria2 = new Categoria(3L, "livro");
        Categoria categoria3 = new Categoria(4L, "fones");
        ArrayList<Categoria> categorias = new ArrayList<>();
        categorias.add(categoria);
        categorias.add(categoria1);
        categorias.add(categoria2);
        categorias.add(categoria3);
        return ResponseEntity.ok().body(categorias);
    };
}
