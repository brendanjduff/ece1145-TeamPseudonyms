package hotciv.standard;

import hotciv.framework.*;


public class Transcript implements Game {
    public Transcript(Game g) {
        game = g;
    }

    final Game game;

    @Override
    public Tile getTileAt(Position p) {
        return game.getTileAt(p);
    }

    @Override
    public Unit getUnitAt(Position p) {
        return game.getUnitAt(p);
    }

    @Override
    public City getCityAt(Position p) {
        return game.getCityAt(p);
    }

    @Override
    public Player getPlayerInTurn() {
        return game.getPlayerInTurn();
    }

    @Override
    public Player getWinner() {
        return game.getWinner();
    }

    @Override
    public int getAge() {
        return game.getAge();
    }

    @Override
    public boolean moveUnit(Position from, Position to) {
        System.out.println(game.getPlayerInTurn().toString() + " moves " + game.getUnitAt(from).getTypeString() +
                " from " + from.toString() + " to " + to.toString());
        return game.moveUnit(from, to);
    }

    @Override
    public void endOfTurn() {
        System.out.println(game.getPlayerInTurn().toString() + " ends their turn");
        game.endOfTurn();
    }

    @Override
    public void changeWorkForceFocusInCityAt(Position p, String balance) {
        System.out.println(game.getPlayerInTurn().toString() + " changes work force focus in city at "
            + game.getCityAt(p).toString() + " to " + balance);
        game.changeWorkForceFocusInCityAt(p, balance);
    }

    @Override
    public void changeProductionInCityAt(Position p, String unitType) {
        System.out.println(game.getPlayerInTurn().toString() + " changes production in city at "
                + game.getCityAt(p).toString() + " to " + unitType);
        game.changeProductionInCityAt(p, unitType);
    }

    @Override
    public void performUnitActionAt(Position p) {
        System.out.println(game.getPlayerInTurn().toString() + " performs unit action at " + p.toString());
        game.performUnitActionAt(p);
    }
}
