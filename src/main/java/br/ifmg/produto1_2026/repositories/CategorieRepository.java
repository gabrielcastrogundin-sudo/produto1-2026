package br.ifmg.produto1_2026.repositories;

import br.ifmg.produto1_2026.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieRepository extends JpaRepository<Category, Long> {
}
