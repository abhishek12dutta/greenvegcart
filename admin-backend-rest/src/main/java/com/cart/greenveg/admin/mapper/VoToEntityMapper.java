package com.cart.greenveg.admin.mapper;

import com.cart.greenveg.admin.dto.*;
import com.cart.greenveg.admin.vo.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VoToEntityMapper {
    @Autowired

    private ModelMapper modelMapper;

    public CategoryDTO mapCategory(Category category) {
        return modelMapper.map(category, CategoryDTO.class);
    }

    public SubCategoryDTO mapSubCategory(SubCategory subCategory) {
        return modelMapper.map(subCategory, SubCategoryDTO.class);
    }

    public ProductCategoryDTO mapProductCategory(ProductCategory productCategory) {
        return modelMapper.map(productCategory, ProductCategoryDTO.class);
    }

    public ProductDTO mapProductForAddRequest(Product product) {
        product.setProductCategory(null);
        product.setProductMetaInfo(null);
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        return productDTO;
    }

    public ProductMetaDTO mapProductMetaDTO(ProductMetaInfo productMetaInfo) {
        return modelMapper.map(productMetaInfo, ProductMetaDTO.class);
    }
}
