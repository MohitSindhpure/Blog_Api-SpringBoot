package com.mohit.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mohit.blog.entities.Comment;

/**
 * @author Mohit Sindhpure
 * @useClass {CommentRepo.interface}
 * @apiNote Blogging API
 * @version springframework.boot [2.7.7]
 * @implNote use JpaRepository[Pagination + Sorting]
 * @see PackageName,allMethods
 * 
 */
public interface CommentRepo extends JpaRepository<Comment,Integer>{

}
