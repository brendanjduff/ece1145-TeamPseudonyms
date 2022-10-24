package hotciv.factory;

import hotciv.common.AgingStrategy;
import hotciv.common.ArcherActionStrategy;
import hotciv.common.BattleStrategy;
import hotciv.common.SettlerActionStrategy;
import hotciv.common.VictoryStrategy;
import hotciv.common.WorldLayoutStrategy;
import hotciv.variants.AttackerWinsBattleStrategy;
import hotciv.variants.ConquerAllCitiesVictoryStrategy;
import hotciv.variants.NoArcherActionStrategy;
import hotciv.variants.NoSettlerActionStrategy;
import hotciv.variants.ProgressiveAgingStrategy;
import hotciv.variants.SparseWorldLayoutStrategy;

public class BetaCivFactory implements GameFactory {

  @Override
  public AgingStrategy createAgingStrategy() {
    return new ProgressiveAgingStrategy();
  }

  @Override
  public VictoryStrategy createVictoryStrategy() {
    return new ConquerAllCitiesVictoryStrategy();
  }

  @Override
  public ArcherActionStrategy createArcherActionStrategy() {
    return new NoArcherActionStrategy();
  }

  @Override
  public SettlerActionStrategy createSettlerActionStrategy() {
    return new NoSettlerActionStrategy();
  }

  @Override
  public WorldLayoutStrategy createWorldLayoutStrategy() {
    return new SparseWorldLayoutStrategy();
  }

  @Override
  public BattleStrategy createBattleStrategy() {
    return new AttackerWinsBattleStrategy();
  }
}
