package ru.spring.semestrovka.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.spring.semestrovka.model.Author;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class AuthorRepositoriesImpl implements AuthorRepositories {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_SELECT_BY_ID = "select * from authors where id = ?;";

    private static final String SQL_SELECT_ALL = "select * from authors";

    private static final String SQL_INSERT = "insert into authors(name, surname, patronymic, id_book) values (?,?,?,?)";

    private static final String SQL_DELETE_BY_ID = "delete from authors where id=?;";

    private static final String SQL_SELECT_BY_ID_BOOK = "select * from authors where id_book=?;";

    private RowMapper<Author> authorRowMapper = (row, rowNumber) ->
            Author.builder().id(row.getLong("id"))
                    .name(row.getString("name"))
                    .surname(row.getString("surname"))
                    .patronymic(row.getString("patronymic"))
                    .id_book(row.getLong("id_book"))
                    .build();
    @Override
    public Optional<Author> find(Long id) {
        try {
            Author author = jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, new Object[]{id}, authorRowMapper);
            return Optional.ofNullable(author);
        } catch (
                EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void save(Author object) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection
                    .prepareStatement(SQL_INSERT);
            statement.setString(1, object.getName());
            statement.setString(2, object.getSurname());
            statement.setString(3, object.getPatronymic());
            statement.setLong(4, object.getId_book());
            return statement;
        }, keyHolder);

        object.setId((Long)keyHolder.getKey());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID,id);
    }

    @Override
    public List<Author> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, authorRowMapper);
    }


    @Override
    public List<Author> getAuthorsByIdBook(Long id) {
        return jdbcTemplate.query(SQL_SELECT_BY_ID_BOOK, new Object[]{id}, authorRowMapper);
    }

}
