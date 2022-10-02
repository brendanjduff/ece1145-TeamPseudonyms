package hotciv.variants;

import hotciv.common.VictoryStrategy;
import hotciv.framework.City;
import hotciv.framework.Player;
import hotciv.framework.Position;

import java.util.ArrayList;

public class ConquerAllCitiesVictoryStrategy implements VictoryStrategy {
    public Player getWinner(int age, java.util.Map<Position, City> cities) {
        ArrayList<Player> owners = new ArrayList<Player>();

        owners.add(cities.get(new Position(1,1)).getOwner());
        owners.add(cities.get(new Position(4,1)).getOwner());

        if(owners.get(0) == owners.get(1)) {
            return owners.get(0);
        } else {
            return null;
        }
    }
}
