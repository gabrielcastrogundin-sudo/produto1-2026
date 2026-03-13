package br.ifmg.produto1_2026.resources;

import br.ifmg.produto1_2026.dto.CategoriaDTO;
import br.ifmg.produto1_2026.entities.Categoria;
import br.ifmg.produto1_2026.service.CategoriaService;
import br.ifmg.produto1_2026.service.exception.RegistroNaoEncontrado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categoria")
public class categoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> categorias() {

        List<CategoriaDTO> categorias = categoriaService.findAll();

        return ResponseEntity.ok().body(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> categoria(@PathVariable long id) {
        CategoriaDTO dto = null;
        try {
            dto = categoriaService.findById(id);
        }catch(RegistroNaoEncontrado e){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    public ResponseEntity<CategoriaDTO> insert(
            @RequestBody CategoriaDTO dto){
                CategoriaDTO retorno = categoriaService.insert(dto);

                URI location = ServletUriComponentsBuilder
                                    .fromCurrentRequest()
                                    .path("/{id}")
                                    .buildAndExpand(retorno.getId())
                                    .toUri();

            return ResponseEntity.created(location).body(retorno);
        }
}
