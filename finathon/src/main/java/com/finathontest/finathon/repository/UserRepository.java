package com.finathontest.finathon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finathontest.finathon.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String userName);
}
