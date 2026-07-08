package com.belle.springweb.repository;

import com.belle.springweb.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
    List<Model> findByNameContainingIgnoreCase(String name);
}
