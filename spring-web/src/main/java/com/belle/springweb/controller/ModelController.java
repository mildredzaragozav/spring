package com.belle.springweb.controller;

import com.belle.springweb.dto.ModelDTO;
import com.belle.springweb.model.Model;
import com.belle.springweb.service.IModelService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/models")
@AllArgsConstructor
public class ModelController {
    private final IModelService modelService;

    @GetMapping
    public ResponseEntity<List<Model>> getModels() {
        return ResponseEntity.ok(modelService.getAll());
    }

    @GetMapping("/paginated")
    public ResponseEntity<PagedModel<EntityModel<Model>>> getModelsPaginated(
            @RequestParam(defaultValue = "0") int page, // page number (0-based)
            @RequestParam(defaultValue = "3") int size, // page size
            PagedResourcesAssembler<Model> assembler) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Model> models = modelService.getAll(pageable);

        return ResponseEntity.ok(assembler.toModel(models));
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
