package com.beesket.beesketclone.repository;

import com.beesket.beesketclone.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
