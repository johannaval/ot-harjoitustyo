package snakegame.dao;

import java.util.*;
import java.sql.*;


interface DaoPlayer<T, K> {

    void create(T object) throws SQLException;

    T read(K key) throws SQLException;

    T update(T object) throws SQLException;

    List<T> list() throws SQLException;
}


