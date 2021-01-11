package com.company.service;

import com.company.model.Consumer;
import com.company.persistence.ConsumerDAO;
import com.google.inject.Inject;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import java.util.Collection;

public class ConsumerService extends BaseService<Consumer> implements Service<Consumer> {
    private final ConsumerDAO consumerDAO;

    @Inject
    public ConsumerService(ConsumerDAO consumerDAO) {
        this.consumerDAO = consumerDAO;
    }

    @Override
    public Collection<Consumer> getAll() {
        return consumerDAO.getAllConsumers();
    }

    @Override
    public Consumer get(Integer id) {
        Consumer consumer = consumerDAO.getConsumer(id);
        return requireResult(consumer);
    }

    @Override
    public Consumer create(Consumer consumer) {
        return errorIfEmpty(get(consumerDAO.createConsumer(consumer)));
    }

    @Override
    public Response delete(Integer id) {
        this.throwNotFoundExceptionWhenDeletingNonExistentObject(id);

        return Response.ok().build();
    }

    @Override
    public Consumer update(Consumer consumer) {
        consumerDAO.updateConsumer(consumer);
        return consumer;
    }

    @Override
    public void throwNotFoundExceptionWhenDeletingNonExistentObject(Integer id) {
        if (!consumerDAO.deleteConsumer(id)) {
            throw new NotFoundException("Klant niet gevonden.");
        }
    }
}
