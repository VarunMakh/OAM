package com.g7.oam.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g7.oam.entities.Category;
import com.g7.oam.exception.CategoryNotFoundException;
import com.g7.oam.repository.ICategoryRepository;

@Service
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	ICategoryRepository repository;

	@Override
	@Transactional
	public Category addCategory(Category category) {
		try {
			repository.save(category);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return category;
	}

	@Override
	@Transactional
	public Category updateCategory(Category category) throws CategoryNotFoundException {
		Optional<Category> optional = null;
		try {
			optional = repository.findById(category.getCategoryId());
			repository.save(category);
		} catch (Exception e) {
			e.printStackTrace();
			if (optional.get() == null) {
				throw new CategoryNotFoundException("Category not found for updation!");
			}
		}
		return optional.get();
	}

	@Override
	public Category viewCategory(Category category) throws CategoryNotFoundException {
		Optional<Category> optional = null;
		try {
			optional = repository.findById(category.getCategoryId());
			repository.findById(category.getCategoryId());
		} catch (Exception e) {
			e.printStackTrace();
			if (optional.get() == null) {
				throw new CategoryNotFoundException("Category not found!");
			}
		}
		return optional.get();
	}

	@Override
	@Transactional
	public Category deleteCategory(int categoryId) throws CategoryNotFoundException {
		Optional<Category> optional = null;
		try {
			optional = repository.findById(categoryId);
			repository.deleteById(categoryId);
		} catch (Exception e) {
			e.printStackTrace();
			if (optional.get() == null) {
				throw new CategoryNotFoundException("Category not found for deletion!");
			}
		}
		return optional.get();
	}

	@Override
	public List<Category> showAllCategories() {
		List<Category> categoryList = null;
		try {
			categoryList = repository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categoryList;
	}

}
