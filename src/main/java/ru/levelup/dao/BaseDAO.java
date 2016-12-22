package ru.levelup.dao;

import ru.levelup.enttities.BaseEntity;

public interface BaseDAO<E extends BaseEntity, ID> {

    void add(E note);

    void delete(E note);

    E get(ID id);

    void update(E note);

}
