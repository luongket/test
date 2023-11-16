//package dao;
//
//import database.libralyData;
//import model.testmysql.words;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//public class vocabularyDAO implements DAOInterface<words>{
//    public static vocabularyDAO getInstance() {
//        return new vocabularyDAO();
//    }
//    @Override
//    public int insert(words words) {
//        int test = 0;
//        try {
//            Connection connection = libralyData.getConnection();
//            String mysql = "INSERT INTO vocabularys (id, words, meaning, note) "+
//                    " VALUES (?, ?, ?, ?)";
//            PreparedStatement pst = connection.prepareStatement(mysql);
//            pst.setInt(1, words.getId());
//            pst.setString(2, words.getWord());
//            pst.setString(3, words.getMeaning());
//            pst.setString(4, words.getNote());
//            test = pst.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return test;
//    }
//
//    @Override
//    public int update(words words) {
//        int test = 0;
//        try {
//            Connection connection = libralyData.getConnection();
//            String mysql = "UPDATE vocabularys "+
//                    " SET " +
//                    " words=?"+
//                    ", meaning=?"+
//                    ", note=?" +
//                    " WHERE id=?";
//            PreparedStatement pst = connection.prepareStatement(mysql);
//            pst.setString(1, words.getWord());
//            pst.setString(2, words.getMeaning());
//            pst.setString(3, words.getNote());
//            pst.setInt(4, words.getId());
//            test = pst.executeUpdate();
//            libralyData.closeConnection(connection);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return test;
//    }
//
//    @Override
//    public int delete(words words) {
//        int test = 0;
//        try {
//            Connection connection = libralyData.getConnection();
//            String mysql = "DELETE from vocabularys "+
//                    " WHERE id=?";
//            PreparedStatement pst = connection.prepareStatement(mysql);
//            pst.setInt(1, words.getId());
//            test = pst.executeUpdate();
//            libralyData.closeConnection(connection);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return test;
//    }
//
//    @Override
//    public ArrayList<words> selectAll() {
//        ArrayList<words> wordsArrayList = new ArrayList<words>();
//        try {
//            Connection connection = libralyData.getConnection();
//            String mysql = "SELECT * FROM vocabularys ";
//            PreparedStatement pst = connection.prepareStatement(mysql);
//            ResultSet rs = pst.executeQuery();
//            while(rs.next()) {
//                int id = rs.getInt("id");
//                String word = rs.getString("words");
//                String meaning = rs.getString("meaning");
//                String note = rs.getString("note");
//                words wordlist = new words(id, word, meaning, note);
//                wordsArrayList.add(wordlist);
//            }
//            libralyData.closeConnection(connection);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return wordsArrayList;
//    }
//
//    @Override
//    public words seclectById(words words) {
//        words wordSelect = null;
//        try {
//            Connection connection= libralyData.getConnection();
//            String mysql = "SELECT * FROM vocabularys where id=?";
//            PreparedStatement pst = connection.prepareStatement(mysql);
//            pst.setInt(1, words.getId());
//            ResultSet rs = pst.executeQuery();
//            while(rs.next()) {
//                int id = rs.getInt("id");
//                String word = rs.getString("words");
//                String meaning = rs.getString("meaning");
//                String note = rs.getString("note");
//                wordSelect = new words(id, word, meaning, note);
//            }
//            libralyData.closeConnection(connection);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return wordSelect;
//    }
//
//    @Override
//    public ArrayList<words> seclectByCondition(String condition) {
//        return null;
//    }
//}
