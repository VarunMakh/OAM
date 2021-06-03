package com.g7.oam.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.g7.oam.dto.CategoryDTO;
import com.g7.oam.entities.Category;
import com.g7.oam.exception.CategoryNotFoundException;
import com.g7.oam.service.ICategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	ICategoryService categoryService;

	@PostMapping("/add")
	public ResponseEntity<CategoryDTO> addCategory(@RequestBody @Valid Category category) {

		Category savedCategory = this.categoryService.addCategory(category);
		CategoryDTO obj = new CategoryDTO();
		obj.setCategoryId(savedCategory.getCategoryId());
		obj.setCategoryName(savedCategory.getCategoryName());
		return new ResponseEntity<>(obj, HttpStatus.OK);

	}

	@PutMapping("/update")
	public ResponseEntity<CategoryDTO> updateCategory(@RequestBody @Valid Category category)
			throws CategoryNotFoundException {

		Category updatedCategory = this.categoryService.updateCategory(category);
		CategoryDTO obj = new CategoryDTO();
		obj.setCategoryId(updatedCategory.getCategoryId());
		obj.setCategoryName(updatedCategory.getCategoryName());
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

	@GetMapping("/view")
	public ResponseEntity<CategoryDTO> viewCategory(@RequestBody @Valid Category category)
			throws CategoryNotFoundException {

		Category retrievedCategory = this.categoryService.viewCategory(category);
		CategoryDTO obj = new CategoryDTO();
		obj.setCategoryId(retrievedCategory.getCategoryId());
		obj.setCategoryName(retrievedCategory.getCategoryName());
		return new ResponseEntity<>(obj, HttpStatus.OK);

	}

	@DeleteMapping("delete/{categoryId}")
	public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable("categoryId") int categoryId)
			throws CategoryNotFoundException {

		Category deletedCategory = this.categoryService.deleteCategory(categoryId);
		CategoryDTO obj = new CategoryDTO();
		obj.setCategoryId(deletedCategory.getCategoryId());
		obj.setCategoryName(deletedCategory.getCategoryName());
		return new ResponseEntity<>(obj, HttpStatus.OK);

	}

	@GetMapping("/showAll")
	public ResponseEntity<List<CategoryDTO>> showAllCategory() {
		List<Category> categoryList = this.categoryService.showAllCategories();
		List<CategoryDTO> categoryDtoList = new ArrayList<>();
		for (Category category : categoryList) {
			CategoryDTO categoryDto = new CategoryDTO();
			categoryDto.setCategoryId(category.getCategoryId());
			categoryDto.setCategoryName(category.getCategoryName());
			categoryDtoList.add(categoryDto);
		}
		return new ResponseEntity<>(categoryDtoList, HttpStatus.OK);
	}

}
