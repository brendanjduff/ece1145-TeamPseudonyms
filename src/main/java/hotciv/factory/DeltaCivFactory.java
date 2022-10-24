package hotciv.factory;

import hotciv.common.AgingStrategy;
import hotciv.common.ArcherActionStrategy;
import hotciv.common.BattleStrategy;
import hotciv.common.SettlerActionStrategy;
import hotciv.common.VictoryStrategy;
import hotciv.common.WorldLayoutStrategy;
import hotciv.variants.AttackerWinsBattleStrategy;
import hotciv.variants.FullWorldLayoutStrategy;
import hotciv.variants.LinearAgingStrategy;
import hotciv.variants.NoArcherActionStrategy;
import hotciv.variants.NoSettlerActionStrategy;
import hotciv.variants.RedWinsAt3000BCVictoryStrategy;

public class DeltaCivFactory implements GameFactory {

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
    return new NoArcherActionStrategy();
  }

  @Override
  public SettlerActionStrategy createSettlerActionStrategy() {
    return new NoSettlerActionStrategy();
  }

  @Override
  public WorldLayoutStrategy createWorldLayoutStrategy() {
    return new FullWorldLayoutStrategy();
  }

  @Override
  public BattleStrategy createBattleStrategy() {
    return new AttackerWinsBattleStrategy();
  }
}
