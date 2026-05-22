package br.ifmg.produto1_2026.repositories;

import br.ifmg.produto1_2026.entities.Product;
import br.ifmg.produto1_2026.projections.ProductProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(nativeQuery = true,
        value = """
                SELECT DISTINCT
                    p.id,
                    p.name,
                    p.price,
                    p.img_url as imgUrl
                FROM tb_product p
                INNER JOIN tb_product_category pc ON pc.id_product=p.id
                INNER JOIN tb_category c ON c.id = pc.id_category
                WHERE (:categoriesID IS NULL OR pc.id_category is (:categoriesID))
                AND ( LOWER(p.name) LIKE LOWER(CONCAT('%',:name,'%'))
                """,
        countName = """
               SELECT COUNT(*) FROM
               (SELECT DISTINCT
                    p.id,
                    p.name,
                    p.price,
                    p.img_url
                FROM tb_product p
                INNER JOIN tb_product_category pc ON pc.id_product=p.id
                INNER JOIN tb_category c ON c.id = pc.id_category
                WHERE (:categoriesID IS NULL OR pc.id_category is (:categoriesID))
                AND ( LOWER(p.name) LIKE LOWER(CONCAT('%',:name,'%'))) as tb_result
                """
        )
    Page<ProductProjection> searchProducts(List<Long> categoriesId, String name, Pageable pageable);
    }
