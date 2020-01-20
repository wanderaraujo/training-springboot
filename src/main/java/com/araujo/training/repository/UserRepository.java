package com.araujo.training.repository;

import com.araujo.training.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    User findByUserName(String userName);
}
