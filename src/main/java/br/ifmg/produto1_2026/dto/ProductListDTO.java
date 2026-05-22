package br.ifmg.produto1_2026.dto;

import br.ifmg.produto1_2026.entities.Product;
import br.ifmg.produto1_2026.projections.ProductProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;

public class ProductListDTO extends RepresentationModel<ProductListDTO> {

    @Schema(description = "identificador")
    private Long id;
    @Schema(description = "nome do produto")
    @Size(min = 2, max = 100, message = "O nome do produto deve ter entre 2 e 100 caracteres")
    private String name;
    @Schema(description = "preço do produto")
    @Positive(message = "O preço do produto deve ser positivo")
    private Double price;
    @Schema(description = "url da imagem do produto")
    private String imgUrl;

    public ProductListDTO(ProductProjection projection) {
        this.id = projection.getId();
        this.name = projection.getName();
        this.price = projection.getPrice();
        this.imgUrl = projection.getImgUrl();
    }

    public ProductListDTO(Long id, String name, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public ProductListDTO(){}

    public ProductListDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.imgUrl = product.getImgUrl();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }


    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
