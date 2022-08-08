package com.akata.offerservice.mapper;

import com.akata.offerservice.dto.CategoryRequestDTO;
import com.akata.offerservice.dto.CategoryResponseDTO;
import com.akata.offerservice.entities.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryResponseDTO categoryToCategoryResponseDTO(Category category);
    Category categoryRequestDTOCategory (CategoryRequestDTO categoryRequestDTO);

    Category categoryResponseDTOCategory(CategoryResponseDTO categoryResponseDTO);
}
