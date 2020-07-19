package com.cart.greenveg.admin.controller;


import com.cart.greenveg.admin.common.AdminBackEndErrorCodes;
import com.cart.greenveg.admin.common.AdminBackEndException;
import com.cart.greenveg.admin.dto.ProductCategoryDTO;
import com.cart.greenveg.admin.service.CategoryService;
import com.cart.greenveg.admin.vo.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/subcategory")
public class ProductCategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("{id}/productCategory/add")
    ResponseEntity<?> addProductCategory(@PathVariable Long id, @RequestBody ProductCategory productCategory) throws AdminBackEndException {

        if (id <= 0) {
            throw new AdminBackEndException(AdminBackEndErrorCodes.BAD_REQUEST, "Sub Category Id can't be 0 or < 0");
        }
        ProductCategory addedProductCategory = categoryService.addProductCategory(id, productCategory);
        return ResponseEntity.ok(addedProductCategory);
    }

    @PutMapping("{id}/productCategory/update")
    ResponseEntity<?> updateProductCategory(@PathVariable Long id, @RequestBody ProductCategory productCategory) throws AdminBackEndException {

        if (id <= 0) {
            throw new AdminBackEndException(AdminBackEndErrorCodes.BAD_REQUEST, "Sub Category Id can't be 0 or < 0");
        }

        if (productCategory.getId() <= 0) {
            throw new AdminBackEndException(AdminBackEndErrorCodes.BAD_REQUEST, "Product Category Id can't be 0 or < 0");
        }
        ProductCategory updatedProductCategory = categoryService.updateProductCategory(id, productCategory);
        return ResponseEntity.ok(updatedProductCategory);
    }

    @DeleteMapping("{id}/productCategory/delete/{pcid}")
    ResponseEntity<?> updateProductCategory(@PathVariable Long id, @PathVariable Long pcid) throws AdminBackEndException {
        categoryService.deleteProductCategory(id, pcid);
        return ResponseEntity.ok().build();
    }



}
