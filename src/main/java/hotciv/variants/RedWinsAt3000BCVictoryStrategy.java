package hotciv.variants;

import hotciv.common.VictoryStrategy;
import hotciv.framework.City;
import hotciv.framework.Player;
import hotciv.framework.Position;

public class RedWinsAt3000BCVictoryStrategy implements VictoryStrategy {
    public Player getWinner(int age, java.util.Map<Position, City> cities) {
        if(age == -3000){
            return Player.RED;
        }else{
            return null;
        }
    }
}
