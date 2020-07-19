package com.cart.greenveg.admin.mapper;

import com.cart.greenveg.admin.dto.SubCategoryDTO;
import com.cart.greenveg.admin.vo.Category;
import com.cart.greenveg.admin.vo.ProductCategory;
import com.cart.greenveg.admin.vo.SubCategory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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


}
