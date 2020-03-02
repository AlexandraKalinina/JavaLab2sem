package repositories;

import Helpers.DbConnection;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoriesImpl implements UserRepositories {
    private Connection conn;
    {
        try {
            conn = DbConnection.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Optional<User> find(Long id) {
        String sql = "select * from users where id = ?;";
        Optional<User> user = Optional.empty();
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = Optional.of(new User(rs.getLong("id"),
                        rs.getString("name"),
                        rs.getInt("age")));
            }
            rs.close();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
        return user;
    }

    @Override
    public void save(User object) {
        String sql = "insert into users values (nextval('users_id_seq'),?, ?);";
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, object.getName());
            ps.setInt(2, object.getAge());
            ps.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "delete from users where id=?;";
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1,id);
            ps.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }

    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "select * from users";
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                users.add(new User(rs.getLong("id"),
                        rs.getString("name"),
                        rs.getInt("age")));
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
        return users;
    }
}
