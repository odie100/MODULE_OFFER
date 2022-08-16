package com.akata.offerservice.controllers;

import com.akata.offerservice.dto.CategoryRequestDTO;
import com.akata.offerservice.dto.CategoryResponseDTO;
import com.akata.offerservice.services.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping(path = "/{id}")
    public CategoryResponseDTO get(@PathVariable("id") Long id){
        return this.categoryService.getCategory(id);
    }

    @GetMapping(path = "/all")
    public List<CategoryResponseDTO> getAll(){
        return this.categoryService.getAllCategories();
    }

    @PostMapping(path = "/insert")
    public CategoryResponseDTO insert(@RequestBody CategoryRequestDTO categoryRequestDTO){
        return this.categoryService.save(categoryRequestDTO);
    }

    @PutMapping(path = "/{id}")
    public CategoryResponseDTO update(@PathVariable("id") Long id, @RequestBody CategoryRequestDTO categoryRequestDTO){
        return this.categoryService.update(id,categoryRequestDTO);
    }

    @DeleteMapping(path = "/{id}")
    public boolean delete(@PathVariable("id") Long id){
        return this.categoryService.delete(id);
    }
}
