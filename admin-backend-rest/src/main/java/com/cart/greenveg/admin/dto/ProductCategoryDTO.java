package com.cart.greenveg.admin.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PRODUCT_CATEGORY")
public class ProductCategoryDTO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_CATEGORY_ID")
    private Long id;
    private String name;
    private String description;

//    @ManyToOne
//    @JoinColumn(name = "SUB_CATEGORY_ID")

    @ManyToOne
    @JoinTable(name="SUBCATEGORY_PRODUCTCATEGORY_MAPPING",
            joinColumns={@JoinColumn(name="PRODUCT_CATEGORY_ID")},
            inverseJoinColumns={@JoinColumn(name="SUB_CATEGORY_ID")})
    @JsonIgnore
    private SubCategoryDTO subCategory;



    @OneToMany(fetch=FetchType.LAZY)
    @JoinTable(name="PRODUCT_PRODUCTCATEGORY_MAPPING",
            joinColumns={@JoinColumn(name="PRODUCT_CATEGORY_ID")},
            inverseJoinColumns={@JoinColumn(name="PRODUCT_ID")}
    )
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Set<ProductDTO> productDTOS = new HashSet<>();

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SubCategoryDTO getSubCategoryDTO() {
        return subCategory;
    }

    public void setSubCategoryDTO(SubCategoryDTO subCategoryDTO) {
        this.subCategory = subCategoryDTO;
    }

    public Set<ProductDTO> getProductDTOS() {
        return productDTOS;
    }

    public void setProductDTOS(Set<ProductDTO> productDTOS) {
        this.productDTOS = productDTOS;
    }
}
