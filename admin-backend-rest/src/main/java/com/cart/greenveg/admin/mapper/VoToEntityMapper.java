package com.cart.greenveg.admin.mapper;

import com.cart.greenveg.admin.dto.CategoryDTO;
import com.cart.greenveg.admin.dto.ProductCategoryDTO;
import com.cart.greenveg.admin.dto.SubCategoryDTO;
import com.cart.greenveg.admin.vo.Category;
import com.cart.greenveg.admin.vo.ProductCategory;
import com.cart.greenveg.admin.vo.SubCategory;
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
}
