package br.ifmg.produto1_2026.resources;

import br.ifmg.produto1_2026.dto.CategoryDTO;
import br.ifmg.produto1_2026.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class categoryResource {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> categories(){


        List<CategoryDTO> categories = categoryService.findAll();


        return ResponseEntity.ok().body(categories);
    };

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> category( @PathVariable Long id){

        CategoryDTO dto = categoryService.findById(id);

        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> insert(@RequestBody CategoryDTO dto){
        //inserindo no db e pegando o objeto criado
        CategoryDTO returnDTO = categoryService.insert(dto);

        // link da categoria criada
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(returnDTO.getId()).toUri();

        // enviadno a categoria criada
        return ResponseEntity.created(location).body(returnDTO);
    }
}
