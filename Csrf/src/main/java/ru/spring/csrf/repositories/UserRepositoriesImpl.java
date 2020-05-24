package ru.spring.csrf.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.spring.csrf.model.Role;
import ru.spring.csrf.model.User;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Component(value = "userRepositories")
public class UserRepositoriesImpl implements UserRepositories {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_SELECT_BY_ID = "select * from users where id = ?;";

    private static final String SQL_SELECT_ALL = "select * from users";

    private static final String SQL_INSERT = "insert into users(name, password, email, role) values (?,?,?,?)";

    private static final String SQL_DELETE_BY_ID = "delete from users where id=?;";

    private static final String SQL_SELECT_BY_EMAIL = "select * from users where email=?;";

    private RowMapper<User> userRowMapper = (row, rowNumber) ->
            User.builder().id(row.getLong("id"))
                    .name(row.getString("name"))
                    .password(row.getString("password"))
                    .email(row.getString("email"))
                    .role(Role.valueOf(row.getString("role")))
                    .build();
    @Override
    public Optional<User> getUserByLogin(String email) {
        try {
            User user = jdbcTemplate.queryForObject(SQL_SELECT_BY_EMAIL, new Object[]{email}, userRowMapper);
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> find(Long id) {
        try {
            User user = jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, new Object[]{id}, userRowMapper);
            return Optional.ofNullable(user);
        } catch (
                EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void save(User object) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection
                    .prepareStatement(SQL_INSERT);
            statement.setString(1, object.getName());
            statement.setString(2, object.getPassword());
            statement.setString(3, object.getEmail());
            statement.setString(4, object.getRole().toString());
            return statement;
        }, keyHolder);

        object.setId((Long)keyHolder.getKey());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID,id);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, userRowMapper);
    }
}
