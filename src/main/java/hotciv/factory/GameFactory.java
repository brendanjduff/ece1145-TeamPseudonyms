package hotciv.factory;

import hotciv.common.AgingStrategy;
import hotciv.common.ArcherActionStrategy;
import hotciv.common.BattleStrategy;
import hotciv.common.SettlerActionStrategy;
import hotciv.common.VictoryStrategy;
import hotciv.common.WorldLayoutStrategy;

public interface GameFactory {

  AgingStrategy createAgingStrategy();

  VictoryStrategy createVictoryStrategy();

  ArcherActionStrategy createArcherActionStrategy();

  SettlerActionStrategy createSettlerActionStrategy();

  WorldLayoutStrategy createWorldLayoutStrategy();

  BattleStrategy createBattleStrategy();
}
