package com.sipoo8.bankapp.repository;

import com.sipoo8.bankapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
