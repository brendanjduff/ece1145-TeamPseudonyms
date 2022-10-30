package hotciv.variants;

import hotciv.common.VictoryStrategy;
import hotciv.framework.MutableGame;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.utility.Utility;

public class ConquerAllCitiesOrWinThreeAttacksAfter20RoundsVictoryStrategy implements VictoryStrategy {
    @Override
    public Player getWinner(MutableGame game) {
        if(game.getAge()<-2000){
            ConquerAllCitiesVictoryStrategy conquerAllCitiesVictoryStrategy = new ConquerAllCitiesVictoryStrategy();
            return conquerAllCitiesVictoryStrategy.getWinner(game);
        }else{
            FirstPlayerToThreeSucceasfulAttacksVictoryStrategy firstPlayerToThreeSucceasfulAttacksVictoryStrategy =
                    new FirstPlayerToThreeSucceasfulAttacksVictoryStrategy();
            return firstPlayerToThreeSucceasfulAttacksVictoryStrategy;
        }
        return null;
    }
}
