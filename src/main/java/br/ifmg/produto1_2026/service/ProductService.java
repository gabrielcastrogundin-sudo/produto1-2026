package br.ifmg.produto1_2026.service;

import br.ifmg.produto1_2026.dto.CategoryDTO;
import br.ifmg.produto1_2026.dto.ProductDTO;
import br.ifmg.produto1_2026.entities.Category;
import br.ifmg.produto1_2026.entities.Product;
import br.ifmg.produto1_2026.repositories.CategoryRepository;
import br.ifmg.produto1_2026.repositories.ProductRepository;
import br.ifmg.produto1_2026.resources.exception.databaseException;
import br.ifmg.produto1_2026.service.exception.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {

        Page<Product> products = productRepository.findAll(pageable);

        return products.map(ProductDTO::new);

    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {

        Product p = productRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFound("Produto não encotrado "));

        return new ProductDTO(p);
    }

    @Transactional
    public ProductDTO insert(ProductDTO productDTO) {

        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImgUrl(productDTO.getImgUrl());


        for(CategoryDTO dto : productDTO.getCategories()){
            Category category = categoryRepository.getReferenceById(dto.getId());
            product.getCategories().add(category);
        }

        productRepository.save(product);
        return new ProductDTO(product);
    }

    @Transactional
    public void delete(Long id) {

        if(!productRepository.existsById(id)) {
            throw new ResourceNotFound("Registro não encontrado" );
        }
        try {
            productRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new databaseException(e.getMessage());
        }
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {

        if(!productRepository.existsById(id)) {
            throw new ResourceNotFound("Registro não encontrado" );
        }

        Product entity = productRepository.getReferenceById(id);

        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());

        productRepository.save(entity);
        return new ProductDTO(entity);
    }
}
