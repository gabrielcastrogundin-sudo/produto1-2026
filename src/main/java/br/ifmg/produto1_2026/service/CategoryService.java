package br.ifmg.produto1_2026.service;

import br.ifmg.produto1_2026.dto.CategoryDTO;
import br.ifmg.produto1_2026.entities.Category;
import br.ifmg.produto1_2026.repositories.CategoryRepository;
import br.ifmg.produto1_2026.resources.exception.databaseException;
import br.ifmg.produto1_2026.service.exception.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll(){
        //Lista com os dados do bd
        List<Category> categories = categoryRepository.findAll();

        //Lista de dados convertidos em DTO
        List<CategoryDTO> categoryDTO = new ArrayList<CategoryDTO>();

//        for (Category category : categories) {
//            categoryDTO.add(new CategoryDTO(category));
//        }

        return categories.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) {

        Optional<Category> opt = categoryRepository.findById(id);
        Category category = opt.orElseThrow(() -> new ResourceNotFound("Categoria não encontrado"));

        return  new CategoryDTO(category);
    }

    @Transactional
    public CategoryDTO insert(CategoryDTO dto) {
        Category category = new Category();
        category.setNome(dto.getName());

        Category newCategory = categoryRepository.save(category);
        return new CategoryDTO(newCategory);
    }
    @Transactional
    public void delete(Long id) {
        if(!categoryRepository.existsById(id)){
            throw new ResourceNotFound("Registro não encontrado, para exclusão");
        }
        try {
            categoryRepository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw  new databaseException(e.getMessage());
        }

    }

}
