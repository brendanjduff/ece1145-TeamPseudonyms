package hotciv.factory;

import hotciv.common.AgingStrategy;
import hotciv.common.BattleStrategy;
import hotciv.common.UnitActionStrategy;
import hotciv.common.VictoryStrategy;
import hotciv.common.WorldLayoutStrategy;
import hotciv.variants.FirstPlayerToThreeSuccessfulAttacksVictoryStrategy;
import hotciv.variants.LinearAgingStrategy;
import hotciv.variants.NoUnitActionStrategy;
import hotciv.variants.RandomCombinedBattleStrategy;
import hotciv.variants.SparseWorldLayoutStrategy;


public class EpsilonCivFactory implements GameFactory {

  @Override
  public AgingStrategy createAgingStrategy() {
    return new LinearAgingStrategy();
  }

  @Override
  public VictoryStrategy createVictoryStrategy() {
    return new FirstPlayerToThreeSuccessfulAttacksVictoryStrategy();
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
    return new RandomCombinedBattleStrategy();
  }
}