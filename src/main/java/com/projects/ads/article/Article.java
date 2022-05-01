package com.projects.ads.article;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String reference;
    private String name;
    private String description;
    /**
     * The real article quantity
     */
    private Integer quantity;
    /**
     * Quantity minimal
     * Should be inferior of the quantity
     */
    private Integer quantity_min;

    public Article() {
    }

    public Article(Long id, String reference, String name, String description, Integer quantity, Integer quantity_min) {
        this.id = id;
        this.reference = reference.toUpperCase();
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.quantity_min = quantity_min;
    }

    public Article(String reference, String name, String description, Integer quantity, Integer quantity_min) {
        this.reference = reference.toUpperCase();
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.quantity_min = quantity_min;
    }

    public Article(String name, String description, Integer quantity, Integer quantity_min) {
        this.reference = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.quantity_min = quantity_min;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference.toUpperCase();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity_min() {
        return quantity_min;
    }

    public void setQuantity_min(Integer quantity_min) {
        this.quantity_min = quantity_min;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", reference=" + reference +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", quantity_min=" + quantity_min +
                '}';
    }
}
