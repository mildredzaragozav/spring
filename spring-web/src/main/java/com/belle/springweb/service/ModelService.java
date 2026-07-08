package com.belle.springweb.service;

import com.belle.springweb.dto.ModelDTO;
import com.belle.springweb.model.Model;
import com.belle.springweb.repository.ModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ModelService {
    private final ModelRepository modelRepository;

    public List<Model> getAll() {
        return modelRepository.findAll();
    }

    public Page<Model> getAll(Pageable pageable) {
        return modelRepository.findAll(pageable);
    }

    public Long getTotal() {
        return modelRepository.count();
    }

    public Optional<Model> getModelById(Long id) {
        return modelRepository.findById(id);
    }

    public List<Model> getModelByName(String name) {
        return modelRepository.findByNameContainingIgnoreCase(name);
    }

    public Model save(ModelDTO modelDTO) {
        Model model = Model.builder().name(modelDTO.name()).email(modelDTO.email()).build();
        return modelRepository.save(model);
    }

    public boolean deleteModelById(Long id) {
        modelRepository.deleteById(id);
        return true;
    }
}
