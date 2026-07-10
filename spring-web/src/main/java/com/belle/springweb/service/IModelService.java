package com.belle.springweb.service;

import com.belle.springweb.dto.ModelDTO;
import com.belle.springweb.model.Model;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IModelService {

    List<Model> getAll();

    Page<Model> getAll(Pageable pageable);

    Long getTotal();

    Optional<Model> getModelById(Long id);

    List<Model> getModelByName(String name);

    Model save(ModelDTO modelDTO);

    boolean deleteModelById(Long id);
}
