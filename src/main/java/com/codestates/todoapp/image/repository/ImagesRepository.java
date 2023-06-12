package com.codestates.todoapp.image.repository;

import com.codestates.todoapp.image.entity.Images;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagesRepository extends JpaRepository<Images,Long> {
}
