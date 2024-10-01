package dao.impl;

import dao.PlayerQuizHistoryDAO;
import models.Player;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerQuizHistoryDAOImpl implements PlayerQuizHistoryDAO {
    PlayerDAOImpl playerDAO = new PlayerDAOImpl();

    @Override
    public void addPlayerHistory(Player player, int score) {
        String query = "INSERT INTO PlayerQuizHistory (player_id, quiz_date, score) VALUES (?, ?, ?)";
        Connection connection;
        PreparedStatement statement;

        int playerId = playerDAO.getPlayerIdByName(player);

        Date quizDate = new Date(System.currentTimeMillis());

        try {
            connection = DBConnection.getConnection();
            statement = connection.prepareStatement(query);

            statement.setInt(1, playerId);
            statement.setDate(2, quizDate);
            statement.setInt(3, score);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updatePlayerHistory(Player player, int newScore) {
        String query = "UPDATE PlayerQuizHistory SET score = score + ?, quiz_date = ? WHERE player_id = ?";

        int playerId = playerDAO.getPlayerIdByName(player);

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);

            Date quizDate = new Date(System.currentTimeMillis());

            stmt.setInt(1, newScore);
            stmt.setDate(2, quizDate);
            stmt.setInt(3, playerId);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean checkHistory(int playerId) {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String query = "SELECT 1 FROM PlayerQuizHistory WHERE player_id = ?";
        boolean exists = false;

        try {
            connection = DBConnection.getConnection();
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, playerId);

            rs = stmt.executeQuery();

            if (rs.next())
                exists = true;

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return exists;
    }

    @Override
    public List<List<String>> getHistory() {
        List<List<String>> output = new ArrayList<>();

        String query = "SELECT p.player_name, p.quizzes_played, pq.quiz_date, pq.score " +
                "FROM PlayerQuizHistory pq " +
                "JOIN Players p ON pq.player_id = p.player_id";

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                List<String> playerRecord = new ArrayList<>();

                // get player name
                String playerName = resultSet.getString("player_name");
                playerRecord.add(playerName);

                // get quizzes played
                int quizzesPlayed = resultSet.getInt("quizzes_played");
                playerRecord.add(String.valueOf(quizzesPlayed));

                // get quiz date
                Date quizDate = resultSet.getDate("quiz_date");
                playerRecord.add(String.valueOf(quizDate));

                // get score
                int score = resultSet.getInt("score");
                playerRecord.add(String.valueOf(score));

                // add the player record to the output list
                output.add(playerRecord);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return output;
    }

    private List<Integer> getIdsFromHistory() {
        String query = "SELECT player_id FROM PlayerQuizHistory";
        List<Integer> playerIds = new ArrayList<>();

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                playerIds.add(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }

        return playerIds;

    }

    @Override
    public void clearHistory() {
        String query = "DELETE FROM PlayerQuizHistory";
        List<Integer> playerIDs = getIdsFromHistory();
        PlayerDAOImpl playerDAOImpl = new PlayerDAOImpl();

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // this needs to be done at last so that "foreign key policy error" doesnt come
        // to light
        // i cried cuz of this FYI
        for (int playerId : playerIDs) {
            playerDAOImpl.deletePlayer(playerId);
        }
    }

    @Override
    public int getHistoryCount() {
        String query = "SELECT COUNT(*) AS total_rows FROM PlayerQuizHistory";
        int historyCount = 0;

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next())
                historyCount = resultSet.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return historyCount;
    }

}
