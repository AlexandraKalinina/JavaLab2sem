package repositories.spring;

import model.Post;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import repositories.PostRepositories;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class PostRepositoriesSpringImpl implements PostRepositories {

    private static final String SQL_SELECT_BY_ID = "select * from posts where id = ?;";

    private static final String SQL_SELECT_ALL = "select * from posts";

    private static final String SQL_INSERT = "insert into posts(name, likes, iduser) values (?,?,?)";

    private static final String SQL_DELETE_BY_ID = "delete from posts where id=?;";

    private JdbcTemplate jdbcTemplate;

    public PostRepositoriesSpringImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Post> postRowMapper = (row, rowNumber) ->
            Post.builder().idPost(row.getLong("id"))
            .name(row.getString("name"))
            .like(row.getInt("likes"))
            .idUser(row.getLong("iduser"))
            .build();

    @Override
    public Optional<Post> find(Long id) {
        try {
            Post post = jdbcTemplate.queryForObject(SQL_SELECT_BY_ID , new Object[]{id}, postRowMapper);
            return Optional.ofNullable(post);
        } catch (
                EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void save(Post object) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection
                    .prepareStatement(SQL_INSERT);
            statement.setString(1, object.getName());
            statement.setInt(2, object.getLike());
            statement.setLong(3, object.getIdUser());

            return statement;
        }, keyHolder);
        object.setIdPost((Long)keyHolder.getKey());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID, id);
    }

    @Override
    public List<Post> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, postRowMapper);
    }
}
