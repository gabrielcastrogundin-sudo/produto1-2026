package br.ifmg.produto1_2026.resources;

import br.ifmg.produto1_2026.entities.Categorie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class categorieResource {

    @GetMapping
    public ResponseEntity<List<Categorie>> categorie(){

        Categorie categorie1 = new Categorie(1L, "Notebook");
        Categorie categorie2 = new Categorie(2L, "Perifericos");
        Categorie categorie3 = new Categorie(3L, "Peso de papel");
        Categorie categorie4 = new Categorie(4L, "Ferramentas");

        List<Categorie> categories = new ArrayList<Categorie>();

        categories.add(categorie1);
        categories.add(categorie2);
        categories.add(categorie3);
        categories.add(categorie4);

        return ResponseEntity.ok().body(categories);
    };
}
