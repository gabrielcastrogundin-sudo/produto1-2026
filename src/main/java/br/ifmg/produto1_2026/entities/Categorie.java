package br.ifmg.produto1_2026.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_categorie")
public class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    public Categorie(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Categorie() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Categorie categorie = (Categorie) o;
        return Objects.equals(id, categorie.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Categorie{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
