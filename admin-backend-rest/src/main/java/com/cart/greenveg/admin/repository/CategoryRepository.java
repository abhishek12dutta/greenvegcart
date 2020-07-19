package com.cart.greenveg.admin.repository;

import com.cart.greenveg.admin.dto.CategoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryDTO, Long> {

}
