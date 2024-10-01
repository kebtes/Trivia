package dao.impl;

import dao.PlayerDAO;
import models.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DBConnection;

public class PlayerDAOImpl implements PlayerDAO {
    @Override
    public void addPlayer(Player player, int score) {
        String query = "INSERT INTO Players (player_name, total_score, quizzes_played) VALUES (?, ?, ?)";

        Connection connection;
        PreparedStatement stmt;

        try {
            connection = DBConnection.getConnection();
            stmt = connection.prepareStatement(query);

            stmt.setString(1, player.getPlayerName());
            stmt.setInt(2, score);
            stmt.setInt(3, 1);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getPlayerIdByName(Player player) {
        String query = "SELECT player_id FROM Players WHERE player_name = ?";
        int playerId = -1;

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, player.getPlayerName());

            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                playerId = resultSet.getInt("player_id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return playerId;
    }

    @Override
    public void updatePlayerScore(Player player, int newScore) {
        String query = "UPDATE Players SET total_score = total_score + ?, quizzes_played = quizzes_played + 1 WHERE player_id = ?";
        int playerId = getPlayerIdByName(player);

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setInt(1, newScore);
            stmt.setInt(2, playerId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePlayer(int playerId) {
        String query = "DELETE From Players WHERE player_id = ?";

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setInt(1, playerId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    public boolean playerExists(String playerName) {
        String query = "SELECT COUNT(*) FROM Players WHERE player_name = ?";

        try (Connection connection = utils.DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, playerName);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public int getPlayerScore(Player player) {
        String query = "SELECT score FROM Players WHERE player_id = ?";

        int playerId = getPlayerIdByName(player);
        int scoreOutput = -1;

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setInt(1, playerId);

            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next())
                scoreOutput = resultSet.getInt(3);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return scoreOutput;
    }

    @Override
    public void updatePlayerName(Player player, String newName) {
        String query = "UPDATE Players SET player_name = ? WHERE player_id = ?";
        int playerId = getPlayerIdByName(player);

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setString(1, newName);
            stmt.setInt(2, playerId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
