package br.ifmg.produto1_2026.resources;

import br.ifmg.produto1_2026.entities.Categoria;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorias")
public class categoriaResource {
    @GetMapping
    public ResponseEntity<Categoria> categoria() {
        Categoria categoria = new Categoria(1L, "notebook");
        return ResponseEntity.ok().body(categoria);
    };

}
