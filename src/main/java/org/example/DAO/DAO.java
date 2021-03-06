package org.example.DAO;

import org.example.model.Ticket;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {

    void add(T element);

    void remove(T element);

    void removeById(long id);

    Optional<T> findById(long id);

    List<T> findAll();

    void removeAll();

    void addAll(List<T> elements);

    void update(T element);
}
