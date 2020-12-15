package com.agenda.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.agenda.domain.model.Model;

import lombok.Getter;

public class DefaultService<R extends JpaRepository<E, Long>, E extends Model> {

  @Autowired
  @Getter
  private R repository;

  public E save(E entity) {
    if (entity.getId() != null) {
      return update(entity);
    }
    entityValidation(entity);
    beforeInsert(entity);
    E insertedEntity = repository.save(entity);
    afterInsert(insertedEntity);
    return insertedEntity;
  }

  public E update(E entity) {
    if (entity.getId() == null) {
      return save(entity);
    }
    entityValidation(entity);
    beforeUpdate(entity);
    E updatedEntity = repository.save(entity);
    afterUpdate(updatedEntity);
    return updatedEntity;
  }

  public E get(Long id) {
    return repository.getOne(id);
  }

  public Page<E> findAll(Pageable page) {
    return repository.findAll(page);
  }

  public void delete(E entity) {
    if (entity.getId() == null) {
      throw new RuntimeException("entity cannot be deleted without ID reference");
    }
    beforeDelete(entity);
    repository.deleteById(entity.getId());
  }


  public void beforeInsert(E entity) {
  }

  public void beforeUpdate(E entity) {
  }

  public void beforeDelete(E entity) {
  }

  private void afterUpdate(E entity) {
  }

  private void afterInsert(E insertedEntity) {
  }

  private void entityValidation(E entity) {
  }

}