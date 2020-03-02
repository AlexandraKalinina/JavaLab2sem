package repositories;

import Helpers.DbConnection;
import model.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostRepositoriesImpl implements PostRepositories {
    private Connection conn;
    {
        try {
            conn = DbConnection.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Optional<Post> find(Long id) {
        String sql = "select * from posts where id = ?;";
        Optional<Post> post = Optional.empty();
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                post = Optional.of(new Post(rs.getLong("id"),
                        rs.getString("name"), rs.getInt("likes"),
                        rs.getLong("iduser")));
            }
            rs.close();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
        return post;
    }

    @Override
    public void save(Post object) {
        String sql = "insert into posts values (nextval('posts_id_seq'), ?, ?, ?);";
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, object.getName());
            ps.setInt(2, object.getLike());
            ps.setLong(3, object.getIdUser());
            ps.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }

    }

    @Override
    public void delete(Long id) {
        String sql = "delete from posts where id=?;";
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1,id);
            ps.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }

    }

    @Override
    public List<Post> findAll() {
        List<Post> posts = new ArrayList<>();
        String sql = "select * from posts";
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                posts.add(new Post(rs.getLong("id"), rs.getString("name"),
                        rs.getInt("likes"),
                        rs.getLong("iduser")));
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
        return posts;
    }
}
