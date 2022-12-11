package hotciv.visual;

import hotciv.factory.SemiCivFactory;
import hotciv.framework.Game;
import hotciv.standard.GameImpl;
import hotciv.tools.CompositionTool;
import hotciv.tools.EndOfTurnTool;
import hotciv.tools.ManageCityTool;
import hotciv.tools.MoveTool;
import hotciv.tools.PerformActionTool;
import hotciv.tools.SetFocusTool;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;

public class PlaySemiCiv {

  public static void main(String[] args) {
    Game game = new GameImpl(new SemiCivFactory());

    DrawingEditor editor =
        new MiniDrawApplication("SemiCiv",
            new HotCivFactory4(game));
    editor.open();
    editor.showStatus("Play SemiCiv");

    CompositionTool comp = new CompositionTool(game, editor);
    editor.setTool(comp);
    comp.addTool(new MoveTool(game, editor));
    comp.addTool(new SetFocusTool(game, editor));
    comp.addTool(new EndOfTurnTool(game));
    comp.addTool(new PerformActionTool(game));
    comp.addTool(new ManageCityTool(game));
  }
}
