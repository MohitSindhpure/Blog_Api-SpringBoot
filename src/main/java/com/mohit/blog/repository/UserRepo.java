package com.mohit.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.google.common.base.Optional;
import com.mohit.blog.entities.User;


/**
 * @author Mohit Sindhpure
 * @useClass {UserRepo.interface}
 * @apiNote Blogging API
 * @version springframework.boot [2.7.7]
 * @implNote use JpaRepository[Pagination + Sorting]
 * @see PackageName
 * 
 */
public interface UserRepo extends JpaRepository<User, Integer>{
	User findByEmail(String email);
}
