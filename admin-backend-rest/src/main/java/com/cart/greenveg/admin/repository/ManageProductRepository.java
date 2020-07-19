package com.cart.greenveg.admin.repository;

import com.cart.greenveg.admin.dto.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManageProductRepository extends JpaRepository<ProductDTO,Long> {


}
