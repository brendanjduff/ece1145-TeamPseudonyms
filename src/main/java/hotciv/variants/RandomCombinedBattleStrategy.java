package hotciv.variants;

import hotciv.common.BattleStrategy;
import hotciv.framework.MutableGame;
import hotciv.framework.MutableUnit;
import hotciv.framework.Position;
import hotciv.utility.Utility2;

public class RandomCombinedBattleStrategy implements BattleStrategy {

  @Override
  public boolean battle(MutableUnit attacker, Position attackerPosition, MutableUnit defender,
      Position defenderPosition, MutableGame game) {
    int attackPower = getCombinedAttackingStrength(attacker, attackerPosition, game);
    int defensePower = getCombinedDefensiveStrength(defender, defenderPosition, game);
    return attackPower * game.getRNG().d6() > defensePower * game.getRNG().d6();
  }

  int getCombinedAttackingStrength(MutableUnit attacker, Position p, MutableGame game) {
    return (attacker.getAttackingStrength() + Utility2.getFriendlySupport(game, p,
        attacker.getOwner())) * Utility2.getTerrainFactor(game, p);
  }

  int getCombinedDefensiveStrength(MutableUnit defender, Position p, MutableGame game) {
    return (defender.getDefensiveStrength() + Utility2.getFriendlySupport(game, p,
        defender.getOwner())) * Utility2.getTerrainFactor(game, p);
  }
}
