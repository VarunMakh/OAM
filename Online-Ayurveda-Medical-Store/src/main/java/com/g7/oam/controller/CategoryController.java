package com.g7.oam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public Category addCategory(@RequestBody Category category) {
		this.categoryService.addCategory(category);
		return category;
	}

	@PutMapping("/update")
	public Category updateCategory(@RequestBody Category category) throws CategoryNotFoundException {
		return this.categoryService.updateCategory(category);
	}

	@GetMapping("/view")
	public Category viewCategory(@RequestBody Category category) throws CategoryNotFoundException {
		return this.categoryService.viewCategory(category);
	}

	@DeleteMapping("delete/{categoryId}")
	public Category deleteCategory(@PathVariable("categoryId") int categoryId) throws CategoryNotFoundException {
		return this.categoryService.deleteCategory(categoryId);
	}

	@GetMapping("/showAll")
	public List<Category> showAllCategories() {
		return this.categoryService.showAllCategories();
	}

}
