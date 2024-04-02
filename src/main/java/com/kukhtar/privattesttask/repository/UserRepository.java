package com.kukhtar.privattesttask.repository;

import com.kukhtar.privattesttask.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByEmail(String email);
}
