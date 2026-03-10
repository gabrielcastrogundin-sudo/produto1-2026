package br.ifmg.produto1_2026.service;

import br.ifmg.produto1_2026.dto.CategoryDTO;
import br.ifmg.produto1_2026.entities.Category;
import br.ifmg.produto1_2026.repositories.CategorieRepository;
import br.ifmg.produto1_2026.service.exception.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategorieService {

    @Autowired
    private CategorieRepository categorieRepository;
    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll(){
        //Lista com os dados do bd
        List<Category> categories = categorieRepository.findAll();

        //Lista de dados convertidos em DTO
        List<CategoryDTO> categorieDTO = new ArrayList<CategoryDTO>();

//        for (Category category : categories) {
//            categorieDTO.add(new CategoryDTO(category));
//        }

        return categories.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) {
        Optional<Category> opt = categorieRepository.findById(id);
        Category category = opt.orElseThrow(() -> new ResourceNotFound("Categoria não encontrado"));
        return  new CategoryDTO(category);
    }
}
