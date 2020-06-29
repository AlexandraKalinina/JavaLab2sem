package ru.parser.simbirSoft.repositories;

import ru.parser.simbirSoft.models.Word;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WordRepositoryImpl implements WordRepository {

    private Connection connection;

    //language=SQL
    private final String SQL_INSERT_WORD = "insert into " +
            "words(word, count) values (?, ?);";

    public WordRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    private RowMapper<Word> userRowMapper = row -> {
        Long id = row.getLong("id");
        String word = row.getString("word");
        Integer count = row.getObject("count", Integer.class);
        return new Word(id, word, count);
    };

    @Override
    public void save(Word model) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_WORD,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, model.getWord());
            statement.setInt(2, model.getCount());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException();
            }

            ResultSet generatesKeys = statement.getGeneratedKeys();

            if (generatesKeys.next()) {
                model.setId(generatesKeys.getLong("id"));
            } else {
                throw new SQLException();
            }
            statement.close();
            generatesKeys.close();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void update(Word model) {

    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public Optional<Word> find(Long id) {
        Word word = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from words where id = " + id + ";");
            if (resultSet.next()) {
                word = userRowMapper.mapRow(resultSet);
            }
            statement.close();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return Optional.ofNullable(word);
    }

    @Override
    public List<Word> findAll() {
        List<Word> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from words");
            while (resultSet.next()) {
                Word word = userRowMapper.mapRow(resultSet);
                result.add(word);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return result;
    }
}
