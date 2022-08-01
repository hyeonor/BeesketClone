package com.beesket.beesketclone.repository;

import com.beesket.beesketclone.model.Image;
import com.beesket.beesketclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Image, Long> {
    Optional<User> findByEmail(String email);
}
