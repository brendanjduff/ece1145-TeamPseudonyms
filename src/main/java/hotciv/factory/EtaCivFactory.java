package hotciv.factory;

import hotciv.common.AgingStrategy;
import hotciv.common.BattleStrategy;
import hotciv.common.UnitActionStrategy;
import hotciv.common.VictoryStrategy;
import hotciv.common.WorkforceStrategy;
import hotciv.common.WorldLayoutStrategy;
import hotciv.variants.AttackerWinsBattleStrategy;
import hotciv.variants.ConstantWorkforceStrategy;
import hotciv.variants.GrowingWorkforceStrategy;
import hotciv.variants.LinearAgingStrategy;
import hotciv.variants.NoUnitActionStrategy;
import hotciv.variants.RedWinsAt3000BCVictoryStrategy;
import hotciv.variants.SparseWorldLayoutStrategy;

public class EtaCivFactory implements GameFactory {

  @Override
  public AgingStrategy createAgingStrategy() {
    return new LinearAgingStrategy();
  }

  @Override
  public VictoryStrategy createVictoryStrategy() {
    return new RedWinsAt3000BCVictoryStrategy();
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
    return new GrowingWorkforceStrategy();
  }
}
