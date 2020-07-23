package com.cart.greenveg.admin.mapper;

import com.cart.greenveg.admin.dto.ProductDTO;
import com.cart.greenveg.admin.dto.ProductMetaDTO;
import com.cart.greenveg.admin.dto.SubCategoryDTO;
import com.cart.greenveg.admin.vo.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class EntityTOVoObjectMapper {

    @Autowired

    private ModelMapper modelMapper;

    public Category mapCategory(com.cart.greenveg.admin.dto.CategoryDTO categoryDTO) {

        Set<SubCategoryDTO> subCategoryDTOS =  categoryDTO.getSubCategories();
        Set<SubCategory> subCategories = new HashSet<>();
        for(SubCategoryDTO subCategoryDTO : subCategoryDTOS){
            SubCategory subCategory = mapSubCategory(subCategoryDTO);
            subCategories.add(subCategory);
        }

        //remove all subcategories from DTO
        categoryDTO.getSubCategories().removeAll(subCategoryDTOS);

        Category category = modelMapper.map(categoryDTO, Category.class);
        category.setSubCategories(subCategories);

        return category;

    }

    public SubCategory mapSubCategory(com.cart.greenveg.admin.dto.SubCategoryDTO subCategoryDTO) {

        SubCategory subCategory = modelMapper.map(subCategoryDTO, SubCategory.class);
        return subCategory;

    }

    public ProductCategory mapProductCategory(com.cart.greenveg.admin.dto.ProductCategoryDTO productCategoryDTO) {

        ProductCategory productCategory = modelMapper.map(productCategoryDTO, ProductCategory.class);
        return productCategory;

    }


    public Product mapProduct(ProductDTO addedProduct) {
        Product product = modelMapper.map(addedProduct, Product.class);
        ProductMetaInfo productMetaInfo = modelMapper.map(addedProduct.getProductMeta(),ProductMetaInfo.class);
        product.setProductMetaInfo(productMetaInfo);
        return product;
    }

    public List<Product> mapProducts(List<ProductDTO> productDTOList) {
        List<Product> productList = new ArrayList<>();
        for(ProductDTO productDTO : productDTOList){
            ProductCategory productCategory = mapProductCategory(productDTO.getProductCategory());
            productDTO.setProductCategory(null);
            Product product = mapProduct(productDTO);
            product.setProductCategory(productCategory);
            productList.add(product);
        }
        return productList;
    }
}
