package br.ifmg.produto1_2026.service;

import br.ifmg.produto1_2026.dto.CategoryDTO;
import br.ifmg.produto1_2026.dto.ProductDTO;
import br.ifmg.produto1_2026.entities.Category;
import br.ifmg.produto1_2026.entities.Product;
import br.ifmg.produto1_2026.repositories.CategoryRepository;
import br.ifmg.produto1_2026.repositories.ProductRepository;
import br.ifmg.produto1_2026.resources.ProductResource;
import br.ifmg.produto1_2026.resources.exception.databaseException;
import br.ifmg.produto1_2026.service.exception.ResourceNotFound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {

        logger.info("Buscando todos os produtos");
        logger.error("Buscando todos os produtos");
        logger.warn("Buscando todos os produtos");
        logger.debug("Buscando todos os produtos");

        Page<Product> products = productRepository.findAll(pageable);

        return products.map(
    p -> new ProductDTO(p)
            .add(linkTo(methodOn(ProductResource.class).findAll(null)).withSelfRel())
            .add(linkTo(methodOn(ProductResource.class).findById(p.getId())).withRel("Obter produto por ID"))
            .add()

        );

    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {

        Product p = productRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFound("Produto não encotrado "));

        ProductDTO dto = new ProductDTO(p);

        return dto
                .add(linkTo(methodOn(ProductResource.class).findById(id)).withSelfRel())
                .add(linkTo(methodOn(ProductResource.class).findAll(null)).withRel("Todos os produtos"))
                .add(linkTo(methodOn(ProductResource.class).update(p.getId(), dto)).withRel("Atualizar produto"))
                .add(linkTo(methodOn(ProductResource.class).delete(p.getId())).withRel("Deletar produto"));
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

        product = productRepository.save(product);
        return new ProductDTO(product)
                .add(linkTo(methodOn(ProductResource.class).insert(productDTO)).withSelfRel())
                .add(linkTo(methodOn(ProductResource.class).findById(product.getId())).withSelfRel())
                .add(linkTo(methodOn(ProductResource.class).findAll(null)).withRel("Todos os produtos"))
                .add(linkTo(methodOn(ProductResource.class).update(product.getId(), productDTO)).withRel("Atualizar produto"))
                .add(linkTo(methodOn(ProductResource.class).delete(product.getId())).withRel("Deletar produto"));
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

        entity = productRepository.save(entity);
        return new ProductDTO(entity)
                .add(linkTo(methodOn(ProductResource.class).findById(entity.getId())).withSelfRel())
                .add(linkTo(methodOn(ProductResource.class).findAll(null)).withRel("Todos os produtos"))
                .add(linkTo(methodOn(ProductResource.class).update(entity.getId(), dto)).withRel("Atualizar produto"))
                .add(linkTo(methodOn(ProductResource.class).delete(entity.getId())).withRel("Deletar produto"));
    }
}
