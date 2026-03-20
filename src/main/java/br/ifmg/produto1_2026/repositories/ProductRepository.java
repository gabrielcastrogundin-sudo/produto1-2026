package br.ifmg.produto1_2026.repositories;

import br.ifmg.produto1_2026.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
