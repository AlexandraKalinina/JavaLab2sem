package ru.spring.semestrovka.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.spring.semestrovka.model.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class BookRepositoriesImpl implements BookRepositories {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_SELECT_BY_ID = "select * from books where id = ?;";

    private static final String SQL_SELECT_ALL = "select * from books";

    private static final String SQL_INSERT = "insert into books(name, text) values (?,?)";

    private static final String SQL_DELETE_BY_ID = "delete from books where id=?;";

    private static final String SQL_SELECT_BY_NAME = "select * from books where text=?;";

    private RowMapper<Book> bookRowMapper = (row, rowNumber) ->
            Book.builder().id(row.getLong("id"))
                    .name(row.getString("name"))
                    .text(row.getString("text"))
                    .build();

    @Override
    public Optional<Book> find(Long id) {
        try {
            Book book = jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, new Object[]{id}, bookRowMapper);
            return Optional.ofNullable(book);
        } catch (
                EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void save(Book object) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection
                    .prepareStatement(SQL_INSERT);
            statement.setString(1, object.getName());
            statement.setString(2, object.getText());
            return statement;
        }, keyHolder);

        object.setId((Long)keyHolder.getKey());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID,id);
    }

    @Override
    public List<Book> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, bookRowMapper);
    }

    @Override
    public Optional<Book> getBookByPath(String text) {
        try {
            Book book = jdbcTemplate.queryForObject(SQL_SELECT_BY_NAME, new Object[]{text}, bookRowMapper);
            return Optional.ofNullable(book);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
