package dao;

import model.testmysql.word;
import java.sql.*;
import java.util.ArrayList;
import database.libralyData; // Thêm import cho lớp quản lý kết nối

public class dictionaryDAO implements DAOInterface<word> {
    // Kết nối đến cơ sở dữ liệu
    private Connection connection;

    // Hàm kết nối cơ sở dữ liệu
    public dictionaryDAO() {
        try {
            // Sử dụng lớp libralyData để lấy kết nối cơ sở dữ liệu
            connection = libralyData.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static dictionaryDAO getInstance() {
        return new dictionaryDAO();
    }

    @Override
    public int insert(word word) {
        try {
            // Tạo truy vấn SQL để chèn bản ghi vào cơ sở dữ liệu
            String query = "INSERT INTO dictionary (word, html) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, word.getWord());
            preparedStatement.setString(2, word.getDefinition());

            // Thực hiện truy vấn chèn
            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(word word) {
        try {
            // Tạo truy vấn SQL để cập nhật bản ghi trong cơ sở dữ liệu
            String query = "UPDATE dictionary SET html = ? WHERE word = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, word.getDefinition());
            preparedStatement.setString(2, word.getWord());

            // Thực hiện truy vấn cập nhật
            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(word word) {
        try {
            // Tạo truy vấn SQL để xóa bản ghi từ cơ sở dữ liệu
            String query = "DELETE FROM dictionary WHERE word = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, word.getWord());

            // Thực hiện truy vấn xóa
            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public ArrayList<word> selectAll() {
        ArrayList<word> wordList = new ArrayList<>();

        try {
            // Tạo truy vấn SQL để lấy tất cả bản ghi từ bảng dictionary
            String query = "SELECT * FROM dictionary";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String wordText = resultSet.getString("word");
                String definition = resultSet.getString("html");
                word word = new word(wordText, definition);
                wordList.add(word);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return wordList;
    }

    @Override
    public word seclectById(word word) {
        word words = null;
        try {
            // Tạo truy vấn SQL để lấy bản ghi dựa trên từ khóa word
            String query = "SELECT * FROM dictionary WHERE word = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, word.getWord());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String wordText = resultSet.getString("word");
                String definition = resultSet.getString("html");
                words = new word(wordText, definition);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return words;
    }

    @Override
    public ArrayList<word> seclectByCondition(String condition) {
        ArrayList<word> wordList = new ArrayList<>();

        try {
            // Tạo truy vấn SQL để lấy bản ghi dựa trên điều kiện
            String query = "SELECT * FROM dictionary WHERE " + condition;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String wordText = resultSet.getString("word");
                String definition = resultSet.getString("html");
                word word = new word(wordText, definition);
                wordList.add(word);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return wordList;
    }
}
