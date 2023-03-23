package com.mohit.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mohit.blog.entities.Category;

/**
 * @author Mohit Sindhpure
 * @useClass {CategoryRepo.interface}
 * @apiNote Blogging API
 * @version springframework.boot [2.7.7]
 * @implNote use JpaRepository[Pagination + Sorting]
 * @see PackageName
 * 
 */
public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
