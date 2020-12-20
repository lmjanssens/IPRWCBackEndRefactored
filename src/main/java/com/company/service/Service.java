package com.company.service;

import javax.ws.rs.core.Response;
import java.util.Collection;

public interface Service<T> {
    Collection<T> getAll();

    T get(Integer id);

    T create(T model);

    Response delete(Integer id);

    T update(T model);
}
