package com.cart.greenveg.admin.service;

import com.cart.greenveg.admin.common.AdminBackEndErrorCodes;
import com.cart.greenveg.admin.common.AdminBackEndException;
import com.cart.greenveg.admin.dto.CategoryDTO;
import com.cart.greenveg.admin.dto.ProductCategoryDTO;
import com.cart.greenveg.admin.dto.SubCategoryDTO;
import com.cart.greenveg.admin.mapper.EntityTOVoObjectMapper;
import com.cart.greenveg.admin.mapper.VoToEntityMapper;
import com.cart.greenveg.admin.repository.CategoryRepository;
import com.cart.greenveg.admin.repository.ProductCategoryRepository;
import com.cart.greenveg.admin.repository.SubCategoryRepository;
import com.cart.greenveg.admin.vo.Category;
import com.cart.greenveg.admin.vo.ProductCategory;
import com.cart.greenveg.admin.vo.SubCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    EntityTOVoObjectMapper entityTOVoObjectMapper;

    @Autowired
    VoToEntityMapper voToEntityMapper;

    public List<Category> fetchCategoryList() throws AdminBackEndException {
        List<CategoryDTO> categoryList = categoryRepository.findAll();
        return categoryList.stream().map((category) -> entityTOVoObjectMapper.mapCategory(category)).collect(Collectors.toList());

    }

    public Category saveCategory(Category category) throws AdminBackEndException {
        CategoryDTO categoryDTO = voToEntityMapper.mapCategory(category);
         CategoryDTO savedcategoryDTO = categoryRepository.save(categoryDTO);
        return entityTOVoObjectMapper.mapCategory(savedcategoryDTO);
    }

    public Category updateCategory(Category category) throws AdminBackEndException {
        Optional<CategoryDTO> catOptional = categoryRepository.findById(category.getId());
        if (catOptional.isPresent()) {
            CategoryDTO fetchedCategoryDTO = catOptional.get();
            fetchedCategoryDTO.setCategory_order(category.getCategory_order());
            fetchedCategoryDTO.setName(category.getName());
            fetchedCategoryDTO.setSlug(category.getSlug());
            CategoryDTO categoryDTO =  categoryRepository.save(fetchedCategoryDTO);
            return entityTOVoObjectMapper.mapCategory(categoryDTO);
        } else {
            throw new AdminBackEndException(AdminBackEndErrorCodes.INVALID_CATEGORY_ID, "Category Id Not Found");
        }
    }

    public SubCategory saveSubCategory(Long id, SubCategory subCategory) throws AdminBackEndException {
        Optional<CategoryDTO> categoryOptional = categoryRepository.findById(id);

        if (categoryOptional.isPresent()) {
            CategoryDTO categoryDTO = categoryOptional.get();
            SubCategoryDTO subCategoryDTO = voToEntityMapper.mapSubCategory(subCategory);
            subCategoryDTO.setCategoryDTO(categoryDTO);
            SubCategoryDTO addedSubCategoryDTO =  subCategoryRepository.save(subCategoryDTO);
            return entityTOVoObjectMapper.mapSubCategory(addedSubCategoryDTO);
        } else {
            throw new AdminBackEndException(AdminBackEndErrorCodes.INVALID_CATEGORY_ID, "Category Id Not Found");
        }
    }

    public SubCategory updateSubCategory(Long id, SubCategory subCategory) throws AdminBackEndException {
        Optional<CategoryDTO> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            Optional<SubCategoryDTO> subCategoryOptional = subCategoryRepository.findById(subCategory.getId());
            if (subCategoryOptional.isPresent()) {
                SubCategoryDTO sc = subCategoryOptional.get();
                sc.setName(subCategory.getName());
                SubCategoryDTO updatedSubCategoryDTO = subCategoryRepository.save(sc);
                return entityTOVoObjectMapper.mapSubCategory(updatedSubCategoryDTO);
            } else {
                throw new AdminBackEndException(AdminBackEndErrorCodes.INVALID_SUB_CATEGORY_ID, "Sub Category Id Not Found");
            }

        } else {
            throw new AdminBackEndException(AdminBackEndErrorCodes.INVALID_CATEGORY_ID, "Category Id Not Found");
        }
    }

    public void deleteSubCategory(Long id, Long subCatid) throws AdminBackEndException {
        Optional<CategoryDTO> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            Optional<SubCategoryDTO> subCategoryOptional = subCategoryRepository.findById(subCatid);
            if (subCategoryOptional.isPresent()) {
                SubCategoryDTO sc = subCategoryOptional.get();
                subCategoryRepository.delete(sc);
            } else {
                throw new AdminBackEndException(AdminBackEndErrorCodes.INVALID_SUB_CATEGORY_ID, "Sub Category Id Not Found");
            }

        } else {
            throw new AdminBackEndException(AdminBackEndErrorCodes.INVALID_CATEGORY_ID, "Category Id Not Found");
        }
    }

    public ProductCategory addProductCategory(Long id, ProductCategory productCategory) throws AdminBackEndException {
        Optional<SubCategoryDTO> subCategoryOptional = subCategoryRepository.findById(id);
        if (subCategoryOptional.isPresent()) {
            SubCategoryDTO subCategoryDTO = subCategoryOptional.get();
            ProductCategoryDTO productCategoryDTO = voToEntityMapper.mapProductCategory(productCategory);
            productCategoryDTO.setSubCategoryDTO(subCategoryDTO);
            ProductCategoryDTO addedPC =  productCategoryRepository.save(productCategoryDTO);
            return entityTOVoObjectMapper.mapProductCategory(addedPC);
        } else {
            throw new AdminBackEndException(AdminBackEndErrorCodes.INVALID_SUB_CATEGORY_ID, "Sub Category Id Not Found");
        }
    }

    public ProductCategory updateProductCategory(Long id, ProductCategory productCategory) throws AdminBackEndException {
        Optional<SubCategoryDTO> subCategoryOptional = subCategoryRepository.findById(id);
        if (subCategoryOptional.isPresent()) {
            Optional<ProductCategoryDTO> productCategoryOptional = productCategoryRepository.findById(productCategory.getId());
            if (productCategoryOptional.isPresent()) {
                ProductCategoryDTO existingPC = productCategoryOptional.get();
                existingPC.setDescription(productCategory.getDescription());
                existingPC.setName(productCategory.getName());
                ProductCategoryDTO updatedPC = productCategoryRepository.save(existingPC);
                return entityTOVoObjectMapper.mapProductCategory(updatedPC);
            } else {
                throw new AdminBackEndException(AdminBackEndErrorCodes.INVALID_PRODUCT_CATEGORY_ID, "Product Category Id Not Found");
            }
        } else {
            throw new AdminBackEndException(AdminBackEndErrorCodes.INVALID_SUB_CATEGORY_ID, "Sub Category Id Not Found");
        }

    }

    public void deleteProductCategory(Long id, Long pcid) throws AdminBackEndException {
        Optional<SubCategoryDTO> subCategoryOptional = subCategoryRepository.findById(id);
        if (subCategoryOptional.isPresent()) {
            Optional<ProductCategoryDTO> productCategoryOptional = productCategoryRepository.findById(pcid);
            if (productCategoryOptional.isPresent()) {
                productCategoryRepository.delete(productCategoryOptional.get());
            } else {
                throw new AdminBackEndException(AdminBackEndErrorCodes.INVALID_PRODUCT_CATEGORY_ID, "Product Category Id Not Found");
            }
        } else {
            throw new AdminBackEndException(AdminBackEndErrorCodes.INVALID_SUB_CATEGORY_ID, "Sub Category Id Not Found");
        }
    }

    public void deleteCategory(Long id) throws AdminBackEndException {
        categoryRepository.deleteById(id);
    }
}
