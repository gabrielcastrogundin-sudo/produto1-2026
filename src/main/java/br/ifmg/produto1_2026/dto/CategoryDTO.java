package br.ifmg.produto1_2026.dto;

import br.ifmg.produto1_2026.entities.Category;

public class CategoryDTO {
    private Long id;
    private String name;

    public CategoryDTO() {
    }

    public CategoryDTO(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public CategoryDTO(Category category) {
        this.name = category.getNome();
        this.id = category.getId();
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

    @Override
    public String toString() {
        return "CategoryDTO{" + "id=" + id + ", name=" + name + '}';
    }
}
