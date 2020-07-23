package com.cart.greenveg.admin.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "PRODUCT")
public class ProductDTO extends AuditModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long id;
    private String name;
    private String summary;
    @Enumerated(EnumType.STRING)
    private ProductUnit unit;
    private String price;
    private String discount;
    private String quantity;
    @Column(columnDefinition="tinyint(1) default 1")
    private boolean active;
    @Column(name = "published_date", columnDefinition = "DATE")
    private LocalDate publishedAt;
    @Column(name = "sale_start_date", columnDefinition = "DATE")
    private LocalDate startsAt;
    @Column(name = "sale_end_date", columnDefinition = "DATE")
    private LocalDate endsAt;

    @JsonIgnore
    @ManyToOne
    @JoinTable(name="PRODUCT_PRODUCTCATEGORY_MAPPING",
            joinColumns={@JoinColumn(name="PRODUCT_ID")},
            inverseJoinColumns={@JoinColumn(name="PRODUCT_CATEGORY_ID")}
    )
    private ProductCategoryDTO productCategory;


    @OneToOne(mappedBy = "product",cascade = CascadeType.ALL)
    @JoinTable(name="PRODUCT_META_MAPPING",
            joinColumns=@JoinColumn(name="PRODUCT_ID",unique=true),
            inverseJoinColumns=@JoinColumn(name="PRODUCT_META_ID",unique=true))
    private ProductMetaDTO productMeta;

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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public ProductUnit getUnit() {
        return unit;
    }

    public void setUnit(ProductUnit unit) {
        this.unit = unit;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDate getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(LocalDate publishedAt) {
        this.publishedAt = publishedAt;
    }

    public LocalDate getStartsAt() {
        return startsAt;
    }

    public void setStartsAt(LocalDate startsAt) {
        this.startsAt = startsAt;
    }

    public LocalDate getEndsAt() {
        return endsAt;
    }

    public void setEndsAt(LocalDate endsAt) {
        this.endsAt = endsAt;
    }

    public ProductMetaDTO getProductMeta() {
        return productMeta;
    }

    public void setProductMeta(ProductMetaDTO productMeta) {
        this.productMeta = productMeta;
    }

    public ProductCategoryDTO getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategoryDTO productCategory) {
        this.productCategory = productCategory;
    }
}
