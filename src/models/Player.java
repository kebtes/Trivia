package models;

import java.util.Objects;

public class Player {
    private String playerName;
    private int playerScore;

    public Player(String playerName) {
        this.playerName = playerName;
    }

    public void incrementScore() {
        playerScore += 1;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void restartScore() {
        playerScore = 0;
    }

    @Override
    public boolean equals(Object obj) {
        // if the object is literally this one
        if (this == obj)
            return true;

        // if the object we comparing this instance with is either null,
        // or isn't from the same class this one is from
        if (obj == null || getClass() != obj.getClass())
            return false;
        Player player = (Player) obj;
        return Objects.equals(playerName, player.playerName);
    }

    @Override
    // this hashes the object, specifically by its name
    // so that we ensure equal objects have the same hash code
    public int hashCode() {
        return Objects.hash(playerName);
    }
}
