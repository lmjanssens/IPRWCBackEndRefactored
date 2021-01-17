package com.company.service;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;

public class BaseService<T> {
    public T requireResult(T model) {
        if (model == null) {
            throw new NotFoundException();
        }

        return model;
    }

    <R extends T> R errorIfEmpty(R model) {
        if (model == null) {
            throw new InternalServerErrorException();
        }

        return model;
    }
}
