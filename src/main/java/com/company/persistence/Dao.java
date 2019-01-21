package com.company.persistence;

import org.intellij.lang.annotations.Language;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {
    @Language("PostgreSQL")
    String getInsertQuery();

    @Language("PostgreSQL")
    String getDeleteQuery();

    @Language("PostgreSQL")
    String getUpdateQuery();

    @Language("PostgreSQL")
    String getFindByIDQuery();

    @Language("PostgreSQL")
    String getAllQuery();

    T insert(T model);

    T findByID(int id) throws SQLException;

    T createEntity(ResultSet resultset) throws SQLException;

    List<T> getAll();

    void update(T model);

    void delete(int id);
}
