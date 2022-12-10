package hotciv.tools;

import hotciv.factory.GameFactory;
import hotciv.framework.Game;
import hotciv.framework.MutableGame;
import hotciv.standard.GameImpl;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

public class EndOfTurnTool extends NullTool {

    Game game;

    public EndOfTurnTool(Game game) {
        this.game = game;
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y) {
        //TODO: Check for click on top shield on age section
    }

    @Override
    public void mouseUp(MouseEvent e, int x, int y) {
        //TODO: Check for click on top shield on age section
        //30,7 is the position of the shield
        //System.out.println("X:"+ x+ "   Y:"+ y);
        if(x==30 && y==7){
            game.endOfTurn();
        }
    }

}
