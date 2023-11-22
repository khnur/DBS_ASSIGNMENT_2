package com.example.demo.service;

import java.util.List;
import java.util.Optional;

public interface AbstractService<T> {
    List<T> getAll();
    Optional<T> getById(Long id);
    T create(T t);
    Optional<T> updateById(Long id, T t);
    Optional<T> deleteById(Long id);
}
