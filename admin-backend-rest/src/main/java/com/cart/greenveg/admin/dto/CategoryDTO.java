package com.cart.greenveg.admin.dto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CATEGORY")
public class CategoryDTO extends AuditModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ID")
    private Long id;
    @NotNull
    @Column(length = 50)
    private String name;
    @Column(length = 100)
    private String slug;
    private int category_order;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    //@JsonIgnoreProperties("subCategories")
    private Set<SubCategoryDTO> subCategories = new HashSet<SubCategoryDTO>();


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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public int getCategory_order() {
        return category_order;
    }

    public void setCategory_order(int category_order) {
        this.category_order = category_order;
    }

    public Set<SubCategoryDTO> getSubCategories() {
        return subCategories;
    }

    public void addSubCategory(SubCategoryDTO subCategoryDTO) {
        this.subCategories.add(subCategoryDTO);
    }

    public void removeSubCategory(SubCategoryDTO subCategoryDTO) {
        this.subCategories.remove(subCategoryDTO);
    }

    public CategoryDTO() {
    }

    public CategoryDTO(@NotNull String name, String slug, int category_order) {
        this.name = name;
        this.slug = slug;
        this.category_order = category_order;
    }


}
