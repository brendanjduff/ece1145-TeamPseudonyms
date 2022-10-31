package hotciv.variants;

import hotciv.common.VictoryStrategy;
import hotciv.framework.MutableGame;
import hotciv.framework.Player;

import java.util.Map;

public class FirstPlayerToThreeSuccessfulAttacksVictoryStrategy implements VictoryStrategy {

    @Override
    public Player getWinner(MutableGame game) {
        Map<Player, Integer> wins = game.getBattleWins();
        for (Map.Entry<Player,Integer> successfulAttacks : wins.entrySet()) {
            if(successfulAttacks.getValue() >= 3)
                return successfulAttacks.getKey();
        }
        return null;
    }
}
