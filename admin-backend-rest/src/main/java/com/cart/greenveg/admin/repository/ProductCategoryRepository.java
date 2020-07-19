package com.cart.greenveg.admin.repository;

import com.cart.greenveg.admin.dto.ProductCategoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategoryDTO, Long> {
}
