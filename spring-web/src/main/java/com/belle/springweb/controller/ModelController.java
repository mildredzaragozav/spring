package com.belle.springweb.controller;

import com.belle.springweb.dto.ModelDTO;
import com.belle.springweb.model.Model;
import com.belle.springweb.service.ModelService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/models")
@AllArgsConstructor
public class ModelController {
    private final ModelService modelService;

    @GetMapping
    public ResponseEntity<List<Model>> getModels() {
        return ResponseEntity.ok(modelService.getAll());
    }

    @GetMapping("/total")
    public ResponseEntity<Long> getTotal() {
        return ResponseEntity.ok(modelService.getTotal());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Model>> search(@RequestParam String name, @RequestParam(required = false) String email) {
        return ResponseEntity.ok(modelService.getModelByName(name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Model> getModelById(@PathVariable Long id) {
        Optional<Model> model = modelService.getModelById(id);
        if (model.isPresent()) {
            return ResponseEntity.ok(model.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Model> save(@RequestBody ModelDTO model) {
        return ResponseEntity.ok(modelService.save(model));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteModel(@PathVariable Long id) {
        return ResponseEntity.ok(modelService.deleteModelById(id));
    }
}
