package br.ifmg.produto1_2026.resources;

import br.ifmg.produto1_2026.entities.Category;
import br.ifmg.produto1_2026.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class categorieResource {

    @Autowired
    private CategorieService categorieService;

    @GetMapping
    public ResponseEntity<List<Category>> categorie(){


        List<Category> categories = categorieService.findAll();


        return ResponseEntity.ok().body(categories);
    };
}
