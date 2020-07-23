package com.cart.greenveg.admin.service;

import com.cart.greenveg.admin.common.AdminBackEndErrorCodes;
import com.cart.greenveg.admin.common.AdminBackEndException;
import com.cart.greenveg.admin.dto.ProductCategoryDTO;
import com.cart.greenveg.admin.dto.ProductDTO;
import com.cart.greenveg.admin.dto.ProductMetaDTO;
import com.cart.greenveg.admin.mapper.EntityTOVoObjectMapper;
import com.cart.greenveg.admin.mapper.VoToEntityMapper;
import com.cart.greenveg.admin.repository.ManageProductRepository;
import com.cart.greenveg.admin.repository.ProductCategoryRepository;
import com.cart.greenveg.admin.repository.ProductMetaInfoRepository;
import com.cart.greenveg.admin.vo.Product;
import com.cart.greenveg.admin.vo.ProductMetaInfo;
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

    @Autowired

    private ProductMetaInfoRepository productMetaInfoRepository;


    @Autowired
    EntityTOVoObjectMapper entityTOVoObjectMapper;

    @Autowired
    VoToEntityMapper voToEntityMapper;


    public List<Product> retrieveAllProducts() {
        List<ProductDTO> productDTOList = manageProductRepository.findAll(Sort.by(Sort.Direction.ASC, "productCategory.id"));
        return entityTOVoObjectMapper.mapProducts(productDTOList);

    }

    public Product addProduct(Long pcID, Product product) throws AdminBackEndException{

        Optional<ProductCategoryDTO> productCategoryOptional = productCategoryRepository.findById(pcID);
        if (productCategoryOptional.isPresent()) {

            ProductMetaDTO productMetaDTO = voToEntityMapper.mapProductMetaDTO(product.getProductMetaInfo());

            ProductDTO productDTO = voToEntityMapper.mapProductForAddRequest(product);
            productDTO.setProductCategory(productCategoryOptional.get());
            ProductDTO addedProduct = manageProductRepository.save(productDTO);
            productMetaDTO.setProduct(addedProduct);
            //save product meta info first
             productMetaDTO = productMetaInfoRepository.save(productMetaDTO);
            addedProduct.setProductMeta(productMetaDTO);

            return entityTOVoObjectMapper.mapProduct(addedProduct);
        } else {
            throw new AdminBackEndException(AdminBackEndErrorCodes.INVALID_PRODUCT_CATEGORY_ID, "Product Category Id Not Found");
        }
    }

    public Product updateProduct(Long pcID, Product product) throws AdminBackEndException{
        Optional<ProductCategoryDTO> productCategoryOptional = productCategoryRepository.findById(pcID);
        if (productCategoryOptional.isPresent()) {
            Optional<ProductDTO> productDTOOptional = manageProductRepository.findById(product.getId());
            if (productDTOOptional.isPresent()) {
                ProductDTO productDTO = productDTOOptional.get();
                productDTO.setActive(product.isActive());
                productDTO.setDiscount(product.getDiscount());
                productDTO.setStartsAt(product.getStartsAt());
                productDTO.setEndsAt(product.getEndsAt());
                productDTO.setPublishedAt(product.getPublishedAt());
                productDTO.setName(product.getName());
                productDTO.setPrice(product.getPrice());
                productDTO.setQuantity(product.getQuantity());
                productDTO.setSummary(product.getSummary());
                productDTO.setUnit(product.getUnit());
                ProductDTO updatedDTO = manageProductRepository.save(productDTO);
                return entityTOVoObjectMapper.mapProduct(updatedDTO);
            } else {
                throw new AdminBackEndException(AdminBackEndErrorCodes.INVALID_PRODUCT_CATEGORY_ID, "Product Id Not Found");
            }

        } else {
            throw new AdminBackEndException(AdminBackEndErrorCodes.INVALID_PRODUCT_CATEGORY_ID, "Product Category Id Not Found");
        }


    }
}
