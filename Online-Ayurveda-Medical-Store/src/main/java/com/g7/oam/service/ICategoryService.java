package com.g7.oam.service;

import java.util.List;

import com.g7.oam.entities.Category;
import com.g7.oam.exception.CategoryNotFoundException;

public interface ICategoryService {

	public Category addCategory(Category category);

	public Category updateCategory(Category category) throws CategoryNotFoundException;

	public Category viewCategory(Category category) throws CategoryNotFoundException;

	public Category deleteCategory(int categoryId) throws CategoryNotFoundException;

	public List<Category> showAllCategories();

}
