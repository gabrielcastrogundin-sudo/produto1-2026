package br.ifmg.produto1_2026.service;

import br.ifmg.produto1_2026.entities.Category;
import br.ifmg.produto1_2026.repositories.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieService {

    @Autowired
    private CategorieRepository categorieRepository;

    public List<Category> findAll(){

        List  categorieList = categorieRepository.findAll();

        return categorieList;
    }

}
