package dao;

import models.Player;
import java.util.List;

public interface PlayerQuizHistoryDAO {
    void addPlayerHistory(Player player, int score);

    void updatePlayerHistory(Player player, int newScore);

    boolean checkHistory(int playerId);

    List<List<String>> getHistory();

    void clearHistory();

    int getHistoryCount();
}
