package com.agenda.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.agenda.domain.model.Model;

public class DefaultController<T extends Model, S extends DefaultService<?, T>> {

  @Getter
  @Setter
  @Autowired
  private S service;

  @PutMapping
  @ResponseBody
  public ResponseEntity<T> save(@RequestBody T entity) {
    return ResponseEntity.ok(service.save(entity));
  }

  @PostMapping
  @ResponseBody
  public ResponseEntity<T> update(@RequestBody T entity) {
    return ResponseEntity.ok(service.update(entity));
  }

  @GetMapping
  @ResponseBody
  public ResponseEntity<Page<T>> findAll(Pageable pageable) {
    return ResponseEntity.ok(service.findAll(pageable));
  }

  @DeleteMapping
  public void delete(@RequestBody T entity) {
    service.delete(entity);
  }


}
