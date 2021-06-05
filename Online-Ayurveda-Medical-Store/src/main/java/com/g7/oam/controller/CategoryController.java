package com.g7.oam.controller;

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

import com.g7.oam.entities.Category;
import com.g7.oam.exception.CategoryNotFoundException;
import com.g7.oam.service.ICategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	ICategoryService categoryService;

	@PostMapping("/add")
	public ResponseEntity<Category> addCategory(@RequestBody @Valid Category category) {

		Category savedCategory = this.categoryService.addCategory(category);
		return new ResponseEntity<>(savedCategory, HttpStatus.OK);

	}

	@PutMapping("/update")
	public ResponseEntity<Category> updateCategory(@RequestBody @Valid Category category)
			throws CategoryNotFoundException {

		Category updatedCategory = this.categoryService.updateCategory(category);
		return new ResponseEntity<>(updatedCategory, HttpStatus.OK);

	}

	@GetMapping("/view")
	public ResponseEntity<Category> viewCategory(@RequestBody @Valid Category category)
			throws CategoryNotFoundException {

		Category retrievedCategory = this.categoryService.viewCategory(category);
		return new ResponseEntity<>(retrievedCategory, HttpStatus.OK);

	}

	@DeleteMapping("delete/{categoryId}")
	public ResponseEntity<Category> deleteCategory(@PathVariable("categoryId") int categoryId)
			throws CategoryNotFoundException {

		Category deletedCategory = this.categoryService.deleteCategory(categoryId);
		return new ResponseEntity<>(deletedCategory, HttpStatus.OK);

	}

	@GetMapping("/showAll")
	public ResponseEntity<List<Category>> showAllCategory() {

		List<Category> categoryList = this.categoryService.showAllCategories();
		return new ResponseEntity<>(categoryList, HttpStatus.OK);
	}

}
