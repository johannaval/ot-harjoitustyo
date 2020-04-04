package snakegame.dao;

import java.sql.SQLException;
import java.util.List;

public interface DaoHighscoreBoard<T, K> {

    void create(T object) throws SQLException;

    T read(K key) throws SQLException;

    T update(T object) throws SQLException;

    List<T> list() throws SQLException;
}

