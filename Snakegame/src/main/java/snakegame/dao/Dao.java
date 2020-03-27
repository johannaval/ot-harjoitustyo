package snakegame.dao;

import java.sql.*;
import java.util.*;

interface Dao<T, K> {

    void create(T object) throws SQLException;

    T read(K key) throws SQLException;

    T update(T object) throws SQLException;

    void delete(K key) throws SQLException;

    List<T> list() throws SQLException;
}
