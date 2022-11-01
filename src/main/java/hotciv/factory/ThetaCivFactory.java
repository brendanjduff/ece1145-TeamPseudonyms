package hotciv.factory;

import hotciv.common.AgingStrategy;
import hotciv.common.BattleStrategy;
import hotciv.common.UnitActionStrategy;
import hotciv.common.VictoryStrategy;
import hotciv.common.WorldLayoutStrategy;
import hotciv.variants.AttackerWinsBattleStrategy;
import hotciv.variants.Fortify_BuildCity_Abduct_UnitActionStrategy;
import hotciv.variants.LinearAgingStrategy;
import hotciv.variants.RedWinsAt3000BCVictoryStrategy;
import hotciv.variants.SparseWorldLayoutStrategy;

public class ThetaCivFactory implements GameFactory {

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
    return new Fortify_BuildCity_Abduct_UnitActionStrategy();
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
