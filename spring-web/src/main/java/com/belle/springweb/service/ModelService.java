package com.belle.springweb.service;

import com.belle.springweb.dto.ModelDTO;
import com.belle.springweb.model.Model;
import com.belle.springweb.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ModelService implements IModelService{

    private final ModelRepository modelRepository;

    @Override
    public List<Model> getAll() {
        return modelRepository.findAll();
    }

    @Override
    public Page<Model> getAll(Pageable pageable) {
        return modelRepository.findAll(pageable);
    }

    @Override
    public Long getTotal() {
        return modelRepository.count();
    }

    @Override
    public Optional<Model> getModelById(Long id) {
        return modelRepository.findById(id);
    }

    @Override
    public List<Model> getModelByName(String name) {
        return modelRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public Model save(ModelDTO modelDTO) {
        Model model = Model.builder().name(modelDTO.name()).email(modelDTO.email()).build();
        return modelRepository.save(model);
    }
    @Override
    public boolean deleteModelById(Long id) {
        modelRepository.deleteById(id);
        return true;
    }
}
