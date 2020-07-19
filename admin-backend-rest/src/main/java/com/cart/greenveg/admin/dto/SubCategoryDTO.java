package com.cart.greenveg.admin.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "SUB_CATEGORY")
public class SubCategoryDTO extends AuditModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SUB_CATEGORY_ID")
    private Long id;

    @NotNull
    @Column(length = 50)
    private String name;

    @ManyToOne
    @JsonIgnore
    private CategoryDTO category;

    @OneToMany(mappedBy = "subCategory", cascade = CascadeType.ALL)
    private Set<ProductCategoryDTO> productCategories = new HashSet<>();

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

    public CategoryDTO getCategoryDTO() {
        return category;
    }

    public void setCategoryDTO(CategoryDTO categoryDTO) {
        this.category = categoryDTO;
    }

    public SubCategoryDTO() {
    }

    public SubCategoryDTO(@NotNull String name) {
        this.name = name;
    }

    public Set<ProductCategoryDTO> getProductCategories() {
        return productCategories;
    }

    public void setProductCategories(Set<ProductCategoryDTO> productCategories) {
        this.productCategories = productCategories;
    }

    public void addProductCategory(ProductCategoryDTO productCategoryDTO){
        this.productCategories.add(productCategoryDTO);
    }

}
