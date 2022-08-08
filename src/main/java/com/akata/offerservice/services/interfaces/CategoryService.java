package com.akata.offerservice.services.interfaces;

import com.akata.offerservice.dto.CategoryRequestDTO;
import com.akata.offerservice.dto.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {

    CategoryResponseDTO save(CategoryRequestDTO categoryRequestDTO);

    CategoryResponseDTO getCategory(Long id);

    CategoryResponseDTO update(Long id, CategoryRequestDTO categoryRequestDTO);

    boolean delete(Long id);

    List<CategoryResponseDTO> getAllCategories();
}
