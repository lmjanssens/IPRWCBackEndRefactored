package com.company.service;

import java.util.Collection;

public interface Service<T> {
    Collection<T> getAll();

    T get(int id);

    T insert(T model);

    void delete(int id);

    void update(T model);
}
