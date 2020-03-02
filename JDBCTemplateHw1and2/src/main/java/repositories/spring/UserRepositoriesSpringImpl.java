package repositories.spring;
import model.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import repositories.UserRepositories;

import java.util.List;
import java.util.Optional;

public class UserRepositoriesSpringImpl implements UserRepositories {
    private JdbcTemplate jdbcTemplate;

    private RowMapper<User> userRowMapper = (row, rowNumber) ->
            User.builder().idUser(row.getLong("id"))
            .name(row.getString("name"))
            .age(row.getInt("age")).build();

    public UserRepositoriesSpringImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<User> find(Long id) {
        try {
            String sql = "select * from users where id = ?;";
            User user = jdbcTemplate.queryForObject(sql, new Object[]{id}, userRowMapper);
            return Optional.ofNullable(user);
        } catch (
                EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void save(User object) {
        String sql = "insert into users values (nextval('users_id_seq'),?, ?);";
        jdbcTemplate.update(sql, object.getName(), object.getAge());
    }

    @Override
    public void delete(Long id) {
        String sql = "delete from users where id=?;";
        jdbcTemplate.update(sql,id);
    }

    @Override
    public List<User> findAll() {
        String sql = "select * from users";
        return jdbcTemplate.query(sql, userRowMapper);
    }
}
