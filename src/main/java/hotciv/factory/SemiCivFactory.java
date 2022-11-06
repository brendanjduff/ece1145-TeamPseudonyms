package hotciv.factory;

import hotciv.common.*;
import hotciv.variants.*;

public class SemiCivFactory implements GameFactory{
    @Override
    public AgingStrategy createAgingStrategy() {
        return new ProgressiveAgingStrategy();
    }

    @Override
    public VictoryStrategy createVictoryStrategy() {
        return new FirstPlayerToThreeSuccessfulAttacksVictoryStrategy();
    }

    @Override
    public UnitActionStrategy createUnitActionStrategy() {
        return new Fortify_BuildCity_UnitActionStrategy();
    }

    @Override
    public WorldLayoutStrategy createWorldLayoutStrategy() {
        return new FullWorldLayoutStrategy();
    }

    @Override
    public BattleStrategy createBattleStrategy() {
        return new RandomCombinedBattleStrategy();
    }

    //TODO: Add EtaCiv CityWorkforceFocus and PopulationIncrease strategy
}
