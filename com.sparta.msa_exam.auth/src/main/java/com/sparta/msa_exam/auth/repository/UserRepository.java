package com.sparta.msa_exam.auth.repository;

import com.sparta.msa_exam.auth.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUserId(String user_id);
}
