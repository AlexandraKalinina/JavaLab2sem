package repositories.spring;

import model.Post;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import repositories.PostRepositories;

import java.util.List;
import java.util.Optional;

public class PostRepositoriesSpringImpl implements PostRepositories {
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
            String sql = "select * from posts where id = ?;";
            Post post = jdbcTemplate.queryForObject(sql, new Object[]{id}, postRowMapper);
            return Optional.ofNullable(post);
        } catch (
                EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void save(Post object) {
        String sql = "insert into posts values (nextval('posts_id_seq'),?, ?, ?);";
        jdbcTemplate.update(sql, object.getName(), object.getLike(), object.getIdUser());
    }

    @Override
    public void delete(Long id) {
        String sql = "delete from posts where id=?;";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Post> findAll() {
        String sql = "select * from posts";
        return jdbcTemplate.query(sql, postRowMapper);
    }
}
