package com.akata.offerservice.services;

import com.akata.offerservice.dto.CategoryRequestDTO;
import com.akata.offerservice.dto.CategoryResponseDTO;
import com.akata.offerservice.entities.Category;
import com.akata.offerservice.mapper.CategoryMapper;
import com.akata.offerservice.repository.CategoryRepository;
import com.akata.offerservice.services.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public CategoryResponseDTO save(CategoryRequestDTO categoryRequestDTO) {
        Category category = this.categoryMapper.categoryRequestDTOCategory(categoryRequestDTO);
        return this.categoryMapper.categoryToCategoryResponseDTO(this.categoryRepository.save(category));
    }

    @Override
    public CategoryResponseDTO getCategory(Long id) {
        return this.categoryMapper.categoryToCategoryResponseDTO(this.categoryRepository.findById(id).get());
    }

    @Override
    public CategoryResponseDTO update(Long id, CategoryRequestDTO categoryRequestDTO) {
        Category category = this.categoryMapper.categoryRequestDTOCategory(categoryRequestDTO);
        category.setId(id);
        return this.categoryMapper.categoryToCategoryResponseDTO(this.categoryRepository.save(category));
    }

    @Override
    public boolean delete(Long id) {
        try {
            this.categoryRepository.deleteById(id);
            return true;
        }catch (DataAccessException e){
            return false;
        }
    }

    @Override
    public List<CategoryResponseDTO> getAllCategories() {
        return this.categoryRepository.findAll().stream()
                .map(category -> this.categoryMapper.categoryToCategoryResponseDTO(category)).collect(Collectors.toList());
    }
}
