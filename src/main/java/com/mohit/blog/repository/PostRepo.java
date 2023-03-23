package com.mohit.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mohit.blog.entities.Category;
import com.mohit.blog.entities.Post;
import com.mohit.blog.entities.User;


/**
 * @author Mohit Sindhpure
 * @useClass {PostRepo.interface}
 * @apiNote Blogging API
 * @version springframework.boot [2.7.7]
 * @implNote use JpaRepository[Pagination + Sorting]
 * @see PackageName,allMethods
 * 
 */
public interface PostRepo extends JpaRepository<Post,Integer>{

	List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category category);
	
	@Query("select p from Post p where p.title like :key")
	List<Post> searchByTitle(@Param("key")  String title);
	
}
