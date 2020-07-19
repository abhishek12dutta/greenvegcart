package com.cart.greenveg.admin.controller;

import com.cart.greenveg.admin.common.AdminBackEndErrorCodes;
import com.cart.greenveg.admin.common.AdminBackEndException;
import com.cart.greenveg.admin.service.CategoryService;
import com.cart.greenveg.admin.vo.Category;
import com.cart.greenveg.admin.vo.SubCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/all")
    ResponseEntity<List<Category>> getAllCategory() throws AdminBackEndException {
        List<Category> allCategories = categoryService.fetchCategoryList();
        return ResponseEntity.ok(allCategories);
    }

    //    @GetMapping("/{id}}")
//    ResponseEntity<ProductCategory> getCategoryDetailsById(@PathVariable int id) throws AdminBackEndException {
//        ProductCategory category = productCategoryService.fetchCategoryById(id);
//        return ResponseEntity.ok(category);
//    }
//
    @PostMapping("/add")
    ResponseEntity<?> addCategory(@RequestBody Category Category) throws AdminBackEndException {

        if (Category.getId().intValue() > 0 || Category.getId().intValue() < 0) {
            throw new AdminBackEndException(AdminBackEndErrorCodes.BAD_REQUEST, "Category Id should be 0");
        }
        Category addedCategory = categoryService.saveCategory(Category);
        return ResponseEntity.ok(addedCategory);
    }

    @PutMapping("/update")
    ResponseEntity<?> updateCategory(@RequestBody Category category) throws AdminBackEndException {
        if (category.getId().intValue() <= 0) {
            throw new AdminBackEndException(AdminBackEndErrorCodes.BAD_REQUEST, "Category Id can't be 0 or < 0");
        }
        Category updatedCategory = categoryService.updateCategory(category);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/{id}/delete")
    ResponseEntity<?> addCategory(@PathVariable Long id) throws AdminBackEndException {

        if (id.intValue() <= 0) {
            throw new AdminBackEndException(AdminBackEndErrorCodes.BAD_REQUEST, "Category Id can't be 0 or < 0");
        }
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/subCategory/add")
    ResponseEntity<?> addSubCategory(@PathVariable Long id, @RequestBody SubCategory subCategory) throws AdminBackEndException {

        if (id.intValue() <= 0) {
            throw new AdminBackEndException(AdminBackEndErrorCodes.BAD_REQUEST, "Category Id can't be 0 or < 0");
        }
        if (subCategory.getId().intValue() > 0 || subCategory.getId().intValue() < 0) {
            throw new AdminBackEndException(AdminBackEndErrorCodes.BAD_REQUEST, "SUB Category Id should be zero");
        }
        SubCategory addedsubCategory = categoryService.saveSubCategory(id, subCategory);
        return ResponseEntity.ok(addedsubCategory);
    }

    @PutMapping("/{id}/subCategory/update")
    ResponseEntity<?> updateSubCategory(@PathVariable Long id, @RequestBody SubCategory subCategory) throws AdminBackEndException {

        if (id <= 0) {
            throw new AdminBackEndException(AdminBackEndErrorCodes.BAD_REQUEST, "Category Id can't be 0 or < 0");
        }
        if (subCategory.getId().intValue() <= 0) {
            throw new AdminBackEndException(AdminBackEndErrorCodes.BAD_REQUEST, "SUB Category Id can't be 0 or < 0");
        }
        SubCategory updatedSubCategory = categoryService.updateSubCategory(id, subCategory);
        return ResponseEntity.ok(updatedSubCategory);
    }


    @DeleteMapping("/{id}/subCategory/{subCatid}")
    ResponseEntity<?> updateSubCategory(@PathVariable Long id, @PathVariable Long subCatid) throws AdminBackEndException {

        if (id <= 0 || subCatid <= 0) {
            throw new AdminBackEndException(AdminBackEndErrorCodes.BAD_REQUEST, "Category Id or Sub Category Id can't be 0 or < 0");
        }
        categoryService.deleteSubCategory(id, subCatid);
        return ResponseEntity.ok().build();
    }


}
