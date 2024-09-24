package dao;

import models.Player;

public interface PlayerDAO {
    void addPlayer(Player player, int score);
    void updatePlayerName(Player player, String newName);
    void updatePlayerScore(Player player, int newScore);
    void deletePlayer(int playerId);
    int getPlayerIdByName(Player player);
    int getPlayerScore(Player player);
}
