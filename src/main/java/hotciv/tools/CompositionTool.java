package hotciv.tools;

import hotciv.framework.Game;
import hotciv.framework.Position;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Tool;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class CompositionTool extends NullTool {
    private final DrawingEditor editor;
    Game game;
    List<Tool> tools;

    public CompositionTool(Game game, DrawingEditor editor) {
        this.editor = editor;
        this.game = game;
        tools = new ArrayList<>();
    }

    public void addTool(Tool tool) {
        tools.add(tool);
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y) {
        super.mouseUp(e,x,y);
        for(Tool t : tools) {
            t.mouseDown(e,x,y);
        }
    }

    @Override
    public void mouseUp(MouseEvent e, int x, int y) {
        super.mouseDown(e,x,y);
        for(Tool t : tools) {
            t.mouseUp(e,x,y);
        }
    }
}
