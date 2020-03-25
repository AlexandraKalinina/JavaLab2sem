package repositories.spring;
import model.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import repositories.UserRepositories;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class UserRepositoriesSpringImpl implements UserRepositories {

    private static final String SQL_SELECT_BY_ID = "select * from ( (select * from users where id = ?) u left join posts on u.id = posts.iduser) limit 1";
    //private static final String SQL_SELECT_BY_ID = "select * from users where id = ?;";

    //private static final String SQL_SELECT_ALL = "select * from users";
    private static final String SQL_SELECT_ALL = "select * from users left join posts p on users.id = p.iduser";

    private static final String SQL_INSERT = "insert into users(name, age) values (?,?)";

    private static final String SQL_DELETE_BY_ID = "delete from users where id=?;";


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
            statement.setInt(2, object.getAge());

            return statement;
        }, keyHolder);
        object.setIdUser((Long)keyHolder.getKey());
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
