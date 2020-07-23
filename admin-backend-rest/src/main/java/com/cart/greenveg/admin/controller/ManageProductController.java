package com.cart.greenveg.admin.controller;


import com.cart.greenveg.admin.common.AdminBackEndException;
import com.cart.greenveg.admin.dto.ProductDTO;
import com.cart.greenveg.admin.service.ManageProductService;
import com.cart.greenveg.admin.vo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/manageproduct")
public class ManageProductController {

    @Autowired
    private ManageProductService manageProductService;

    @GetMapping("/allproducts")
    ResponseEntity<List<Product>> fetchAllProducts() throws AdminBackEndException {
        List<Product> products = manageProductService.retrieveAllProducts();

        return ResponseEntity.ok(products);
    }

    @PostMapping("/addproduct/productCategory/{pcid}")
    ResponseEntity<Product> addProducts(@RequestBody Product product,@PathVariable Long pcid) throws AdminBackEndException {
        Product addProduct = manageProductService.addProduct(pcid,product);
        return ResponseEntity.ok(addProduct);
    }

    @PutMapping(path = "/updateproduct/productCategory/{pcid}")
    ResponseEntity<Product> updateProducts(@RequestBody Product product,@PathVariable Long pcid) throws AdminBackEndException {
        Product updateProduct = manageProductService.updateProduct(pcid,product);
        return ResponseEntity.ok(updateProduct);
    }


}
