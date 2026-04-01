package br.ifmg.produto1_2026.service;

import br.ifmg.produto1_2026.dto.CategoryDTO;
import br.ifmg.produto1_2026.entities.Category;
import br.ifmg.produto1_2026.repositories.CategoryRepository;
import br.ifmg.produto1_2026.resources.exception.databaseException;
import br.ifmg.produto1_2026.service.exception.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public CategoryDTO update(Long id, CategoryDTO dto) {

        if(!categoryRepository.existsById(id)){
            throw new ResourceNotFound("Categoria não encontrada, para update");
        }

        Category entity = categoryRepository.getReferenceById(id);

        entity.setName(dto.getName());
        entity = categoryRepository.save(entity);
        return new CategoryDTO(entity);
    }

    @Transactional(readOnly = true)
    public Page<CategoryDTO> findAll(Pageable pageable){
        //Lista com os dados do bd
        Page<Category> categories = categoryRepository.findAll(pageable);

        //Lista de dados convertidos em DTO
//        List<CategoryDTO> categoryDTO = new ArrayList<CategoryDTO>();

//        for (Category category : categories) {
//            categoryDTO.add(new CategoryDTO(category));
//        }

        return categories.map(CategoryDTO::new);
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
        category.setName(dto.getName());

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
