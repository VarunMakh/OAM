package com.g7.oam.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Validated
@RestController
@RequestMapping("/categories")
@Api(value = "OAM - Category")
public class CategoryController {

	@Autowired
	ICategoryService categoryService;
	org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(MedicineController.class);


	@PostMapping("/add")
	@ApiOperation(value = "Add Category using Post Mapping", response = Category.class)

	public ResponseEntity<Category> addCategory(@RequestBody @Valid Category category) {

		logger.info("Add Category Called in Category Controller");
		Category savedCategory = this.categoryService.addCategory(category);
		return new ResponseEntity<>(savedCategory, HttpStatus.OK);

	}

	@PutMapping("/update")
	@ApiOperation(value = "Update Category using Put Mapping", response = Category.class)
	public ResponseEntity<Category> updateCategory(@RequestBody @Valid Category category)
			throws CategoryNotFoundException {

		logger.info("update Category Called in Category Controller");
		Category updatedCategory = this.categoryService.updateCategory(category);
		return new ResponseEntity<>(updatedCategory, HttpStatus.OK);

	}

	@GetMapping("/view")
	@ApiOperation(value = "View Category using Put Mapping", response = Category.class)
	public ResponseEntity<Category> viewCategory(@RequestBody @Valid Category category)
			throws CategoryNotFoundException {

		logger.info("View Category Called in Category Controller");
		Category retrievedCategory = this.categoryService.viewCategory(category);
		return new ResponseEntity<>(retrievedCategory, HttpStatus.OK);

	}

	@DeleteMapping("delete/{categoryId}")
	@ApiOperation(value = "Delete Category using Put Mapping", response = Category.class)
	public ResponseEntity<Category> deleteCategory(@PathVariable("categoryId") int categoryId)
			throws CategoryNotFoundException {

		logger.info("Delete Category Called in Category Controller");
		Category deletedCategory = this.categoryService.deleteCategory(categoryId);
		return new ResponseEntity<>(deletedCategory, HttpStatus.OK);

	}

	@GetMapping("/showAll")
	@ApiOperation(value = "show All category using Put Mapping", response = Category.class)
	public ResponseEntity<List<Category>> showAllCategory() {

		logger.info("Add Category Called in Category Controller");
		List<Category> categoryList = this.categoryService.showAllCategories();
		return new ResponseEntity<>(categoryList, HttpStatus.OK);
	}

}
