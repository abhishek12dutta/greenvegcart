package com.cart.greenveg.admin.repository;

import com.cart.greenveg.admin.dto.ProductMetaDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductMetaInfoRepository extends JpaRepository<ProductMetaDTO, Long> {

}
