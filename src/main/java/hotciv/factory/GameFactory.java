package hotciv.factory;

import hotciv.common.AgingStrategy;
import hotciv.common.BattleStrategy;
import hotciv.common.UnitActionStrategy;
import hotciv.common.VictoryStrategy;
import hotciv.common.WorldLayoutStrategy;

public interface GameFactory {

  AgingStrategy createAgingStrategy();

  VictoryStrategy createVictoryStrategy();

  UnitActionStrategy createUnitActionStrategy();

  WorldLayoutStrategy createWorldLayoutStrategy();

  BattleStrategy createBattleStrategy();
}
