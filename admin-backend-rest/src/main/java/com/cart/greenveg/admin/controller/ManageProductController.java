package com.cart.greenveg.admin.controller;


import com.cart.greenveg.admin.common.AdminBackEndException;
import com.cart.greenveg.admin.dto.ProductDTO;
import com.cart.greenveg.admin.service.ManageProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/manageproduct")
public class ManageProductController {

    @Autowired
    private ManageProductService manageProductService;

    @GetMapping("/products")
    ResponseEntity<List<ProductDTO>> fetchAllProducts() throws AdminBackEndException {
        List<ProductDTO> productDTOS = manageProductService.retrieveAllProducts();
        return ResponseEntity.ok(productDTOS);
    }

    @PostMapping("/product/add")
    ResponseEntity<ProductDTO> addProducts(@RequestBody ProductDTO productDTO) throws AdminBackEndException {
        ProductDTO addProductDTO = manageProductService.addProduct(productDTO);
        return ResponseEntity.ok(addProductDTO);
    }

    @PutMapping(path = "/product/update")
    ResponseEntity<ProductDTO> updateProducts(@RequestBody ProductDTO productDTO) throws AdminBackEndException {
        ProductDTO updateProductDTO = manageProductService.updateProduct(productDTO);
        return ResponseEntity.ok(updateProductDTO);
    }


}
