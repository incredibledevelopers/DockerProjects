package com.springbootdev.examples.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springbootdev.examples.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long>
{

}
