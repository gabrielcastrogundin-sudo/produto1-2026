package br.ifmg.produto1_2026.resources;

import br.ifmg.produto1_2026.dto.CategoryDTO;
import br.ifmg.produto1_2026.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class categoryResource {

    @Autowired
    private CategorieService categorieService;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> categories(){


        List<CategoryDTO> categories = categorieService.findAll();


        return ResponseEntity.ok().body(categories);
    };

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> category( @PathVariable Long id){

        CategoryDTO dto = categorieService.findById(id);
        
        return ResponseEntity.ok().body(dto);
    }
}
