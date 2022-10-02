package hotciv.variants;

import hotciv.common.ArcherActionStrategy;
import hotciv.framework.Unit;

public class NoArcherActionStrategy implements ArcherActionStrategy {
    @Override
    public void fortify(Unit unit) {
        // No action taken
    }
}
