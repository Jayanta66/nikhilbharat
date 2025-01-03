package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.model.ImageGallery;



@Repository
public interface ImageGalleryRepository extends JpaRepository<ImageGallery, Long>{

}

