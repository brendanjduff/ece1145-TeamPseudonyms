package hotciv.factory;

import hotciv.common.AgingStrategy;
import hotciv.common.BattleStrategy;
import hotciv.common.UnitActionStrategy;
import hotciv.common.VictoryStrategy;
import hotciv.common.WorkforceStrategy;
import hotciv.common.WorldLayoutStrategy;
import hotciv.variants.AttackerWinsBattleStrategy;
import hotciv.variants.ConquerAllCitiesVictoryStrategy;
import hotciv.variants.ConstantWorkforceStrategy;
import hotciv.variants.NoUnitActionStrategy;
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
  public UnitActionStrategy createUnitActionStrategy() {
    return new NoUnitActionStrategy();
  }

  @Override
  public WorldLayoutStrategy createWorldLayoutStrategy() {
    return new SparseWorldLayoutStrategy();
  }

  @Override
  public BattleStrategy createBattleStrategy() {
    return new AttackerWinsBattleStrategy();
  }

  @Override
  public WorkforceStrategy createWorkforceStrategy() {
    return new ConstantWorkforceStrategy();
  }
}
