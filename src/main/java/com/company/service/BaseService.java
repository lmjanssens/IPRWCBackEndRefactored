package com.company.service;

import com.company.model.Consumer;

import javax.ws.rs.ForbiddenException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;

public class BaseService<T> {
    public T requireResult(T model) {
        if (model == null) {
            throw new NotFoundException();
        }
        return model;
    }

    public void assertSelf(Consumer consumer1, Consumer consumer2) {
        if (!consumer1.equals(consumer2)) {
            throw new ForbiddenException();
        }
    }

    <R extends T> R errorIfEmpty(R model) {
        if (model == null) {
            throw new InternalServerErrorException();
        }
        return model;
    }
}
