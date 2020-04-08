package ru.spring.semestrovka.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.spring.semestrovka.model.Genre;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class GenreRepositoriesImpl implements GenreRepositories {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_SELECT_BY_ID = "select * from genre where id = ?;";

    private static final String SQL_SELECT_ALL = "select * from genre";

    private static final String SQL_INSERT = "insert into genre(name, id_book) values (?,?)";

    private static final String SQL_DELETE_BY_ID = "delete from genre where id=?;";

    private static final String SQL_SELECT_BY_ID_BOOK = "select * from genre where id_book=?;";

    private RowMapper<Genre> genreRowMapper = (row, rowNumber) ->
            Genre.builder().id(row.getLong("id"))
                    .name(row.getString("name"))
                    .id_book(row.getLong("id_book"))
                    .build();

    @Override
    public Optional<Genre> find(Long id) {
        try {
            Genre genre = jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, new Object[]{id}, genreRowMapper);
            return Optional.ofNullable(genre);
        } catch (
                EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void save(Genre object) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection
                    .prepareStatement(SQL_INSERT);
            statement.setString(1, object.getName());
            statement.setLong(2, object.getId_book());
            return statement;
        }, keyHolder);

        object.setId((Long)keyHolder.getKey());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID,id);
    }

    @Override
    public List<Genre> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, genreRowMapper);
    }

    @Override
    public List<Genre> getGenreByIdBook(Long id) {
        return jdbcTemplate.query(SQL_SELECT_BY_ID_BOOK, new Object[]{id}, genreRowMapper);
    }
}
