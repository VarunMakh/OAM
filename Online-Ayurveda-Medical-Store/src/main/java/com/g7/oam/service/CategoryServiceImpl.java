package com.g7.oam.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g7.oam.entities.Category;
import com.g7.oam.exception.CategoryNotFoundException;
import com.g7.oam.repository.ICategoryRepository;

@Service
public class CategoryServiceImpl implements ICategoryService {

	Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

	@Autowired
	ICategoryRepository repository;

	public CategoryServiceImpl(ICategoryRepository repository) {
		this.repository = repository;
	}

	@Override
	@Transactional
	public Category addCategory(Category category) {
		try {
			repository.save(category);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return category;
	}

	@Override
	@Transactional
	public Category updateCategory(Category category) throws CategoryNotFoundException {
		Optional<Category> optional = repository.findById(category.getCategoryId());
		if (optional.isPresent()) {
			repository.save(category);
			return optional.get();
		} else {
			throw new CategoryNotFoundException("Category not found for updation!");
		}
	}

	@Override
	public Category viewCategory(Category category) throws CategoryNotFoundException {
		Optional<Category> optional = repository.findById(category.getCategoryId());
		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new CategoryNotFoundException("Category not found!");
		}
	}

	@Override
	@Transactional
	public Category deleteCategory(int categoryId) throws CategoryNotFoundException {
		Optional<Category> optional = repository.findById(categoryId);
		if (optional.isPresent()) {
			repository.deleteById(categoryId);
			return optional.get();
		} else {
			throw new CategoryNotFoundException("Category not found for deletion!");
		}
	}

	@Override
	public List<Category> showAllCategories() {
		List<Category> categoryList = null;
		try {
			categoryList = repository.findAll();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return categoryList;
	}

}
