package com.cart.greenveg.admin.dto;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT_META_INFO")
public class ProductMetaDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_META_ID")
    private Long id;
    private String tags;
    private String image;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinTable(name="PRODUCT_META_MAPPING",joinColumns=@JoinColumn(name="PRODUCT_META_ID",unique=true),
            inverseJoinColumns=@JoinColumn(name="PRODUCT_ID",unique=true))
    private ProductDTO product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }
}
