package com.cart.greenveg.admin.service;

import com.cart.greenveg.admin.dto.ProductDTO;
import com.cart.greenveg.admin.dto.ProductCategoryDTO;
import com.cart.greenveg.admin.repository.ManageProductRepository;
import com.cart.greenveg.admin.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManageProductService {
    @Autowired
    private ManageProductRepository manageProductRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    public List<ProductDTO> retrieveAllProducts() {
        return manageProductRepository.findAll(Sort.by(Sort.Direction.ASC, "productCategory.id"));
    }

    public ProductDTO addProduct(ProductDTO productDTO) {

        Optional<ProductCategoryDTO> productCategoryOptional = productCategoryRepository.findById(productDTO.getProductCategoryDTO().getId());
        if (productCategoryOptional.isPresent()) {
            productDTO.setProductCategoryDTO(productCategoryOptional.get());
        }
        return manageProductRepository.save(productDTO);
    }

    public ProductDTO updateProduct(ProductDTO productDTO) {
        Optional<ProductCategoryDTO> productCategoryOptional = productCategoryRepository.findById(productDTO.getProductCategoryDTO().getId());
        if (productCategoryOptional.isPresent()) {
            productDTO.setProductCategoryDTO(productCategoryOptional.get());
        }
        return manageProductRepository.save(productDTO);

    }
}
