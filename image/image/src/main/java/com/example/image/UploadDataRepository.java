package com.example.image;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadDataRepository extends JpaRepository<UploadData, Long> {
}
