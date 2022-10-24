package hotciv.factory;

import hotciv.common.AgingStrategy;
import hotciv.common.ArcherActionStrategy;
import hotciv.common.SettlerActionStrategy;
import hotciv.common.VictoryStrategy;
import hotciv.common.WorldLayoutStrategy;
import hotciv.variants.BuildCitySettlerActionStrategy;
import hotciv.variants.FortifyArcherActionStrategy;
import hotciv.variants.LinearAgingStrategy;
import hotciv.variants.RedWinsAt3000BCVictoryStrategy;
import hotciv.variants.SparseWorldLayoutStrategy;

public class GammaCivFactory implements GameFactory {

  @Override
  public AgingStrategy createAgingStrategy() {
    return new LinearAgingStrategy();
  }

  @Override
  public VictoryStrategy createVictoryStrategy() {
    return new RedWinsAt3000BCVictoryStrategy();
  }

  @Override
  public ArcherActionStrategy createArcherActionStrategy() {
    return new FortifyArcherActionStrategy();
  }

  @Override
  public SettlerActionStrategy createSettlerActionStrategy() {
    return new BuildCitySettlerActionStrategy();
  }

  @Override
  public WorldLayoutStrategy createWorldLayoutStrategy() {
    return new SparseWorldLayoutStrategy();
  }
}
