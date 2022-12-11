package hotciv.factory;

import hotciv.common.AgingStrategy;
import hotciv.common.BattleStrategy;
import hotciv.common.UnitActionStrategy;
import hotciv.common.VictoryStrategy;
import hotciv.common.WorkforceStrategy;
import hotciv.common.WorldLayoutStrategy;
import hotciv.variants.AttackerWinsBattleStrategy;
import hotciv.variants.ConquerAllCitiesOrWinThreeAttacksAfter20RoundsVictoryStrategy;
import hotciv.variants.ConstantWorkforceStrategy;
import hotciv.variants.LinearAgingStrategy;
import hotciv.variants.NoUnitActionStrategy;
import hotciv.variants.SparseWorldLayoutStrategy;

public class ZetaCivFactory implements GameFactory {

  @Override
  public AgingStrategy createAgingStrategy() {
    return new LinearAgingStrategy();
  }

  @Override
  public VictoryStrategy createVictoryStrategy() {
    return new ConquerAllCitiesOrWinThreeAttacksAfter20RoundsVictoryStrategy();
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