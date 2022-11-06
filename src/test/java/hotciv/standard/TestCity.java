package hotciv.standard;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import org.junit.Test;

public class TestCity {

  private CityImpl city;

  @Test
  public void cityIsOwnedByRed() {
    city = new CityImpl(Player.RED);
    assertThat(city.getOwner(), is(Player.RED));
  }

  @Test
  public void cityIsOwnedByBlue() {
    city = new CityImpl(Player.BLUE);
    assertThat(city.getOwner(), is(Player.BLUE));
  }

  @Test
  public void cityPopulationIsOne() {
    city = new CityImpl(Player.RED);
    assertThat(city.getSize(), is(1));
  }

  @Test
  public void cityProductionIsSixEachTurn() {
    city = new CityImpl(Player.RED);
    assertThat(city.getTreasury(), is(0));
    city.fillTreasury();
    assertThat(city.getTreasury(), is(6));
  }

  @Test
  public void shouldSetProductionTypeToArcher() {
    city = new CityImpl(Player.RED);
    city.setProduction(GameConstants.ARCHER);
    assertThat(city.getProduction(), is(GameConstants.ARCHER));
  }

  @Test
  public void shouldSetProductionTypeToLegion() {
    city = new CityImpl(Player.RED);
    city.setProduction(GameConstants.LEGION);
    assertThat(city.getProduction(), is(GameConstants.LEGION));
  }

  @Test
  public void shouldSetProductionTypeToSettler() {
    city = new CityImpl(Player.RED);
    city.setProduction(GameConstants.SETTLER);
    assertThat(city.getProduction(), is(GameConstants.SETTLER));
  }

  @Test
  public void shouldSetProductionTypeToUFO() {
    city = new CityImpl(Player.RED);
    city.setProduction(GameConstants.UFO);
    assertThat(city.getProduction(), is(GameConstants.UFO));
  }

  @Test
  public void cityShouldProduceArcherAt10Production() {
    city = new CityImpl(Player.RED);
    city.setProduction(GameConstants.ARCHER);
    city.fillTreasury();
    assertThat(city.unitCostMet(), is(false));
    city.fillTreasury();
    assertThat(city.unitCostMet(), is(true));
    city.produceUnit();
    assertThat(city.getProduction(), is(GameConstants.ARCHER));
    assertThat(city.getTreasury(), is(2));
  }

  @Test
  public void cityShouldProduceLegionAt15Production() {
    city = new CityImpl(Player.RED);
    city.setProduction(GameConstants.LEGION);
    city.fillTreasury();
    assertThat(city.unitCostMet(), is(false));
    city.fillTreasury();
    assertThat(city.unitCostMet(), is(false));
    city.fillTreasury();
    assertThat(city.unitCostMet(), is(true));
    city.produceUnit();
    assertThat(city.getProduction(), is(GameConstants.LEGION));
    assertThat(city.getTreasury(), is(3));
  }

  @Test
  public void cityShouldProduceSettlerAt30Production() {
    city = new CityImpl(Player.RED);
    city.setProduction(GameConstants.SETTLER);
    city.fillTreasury();
    assertThat(city.unitCostMet(), is(false));
    city.fillTreasury();
    assertThat(city.unitCostMet(), is(false));
    city.fillTreasury();
    assertThat(city.unitCostMet(), is(false));
    city.fillTreasury();
    assertThat(city.unitCostMet(), is(false));
    city.fillTreasury();
    assertThat(city.unitCostMet(), is(true));
    city.produceUnit();
    assertThat(city.getProduction(), is(GameConstants.SETTLER));
    assertThat(city.getTreasury(), is(0));
  }

  @Test
  public void cityShouldProduceUFOAt60Production() {
    city = new CityImpl(Player.RED);
    city.setProduction(GameConstants.UFO);
    city.fillTreasury();
    assertThat(city.unitCostMet(), is(false));
    city.fillTreasury();
    assertThat(city.unitCostMet(), is(false));
    city.fillTreasury();
    assertThat(city.unitCostMet(), is(false));
    city.fillTreasury();
    assertThat(city.unitCostMet(), is(false));
    city.fillTreasury();
    assertThat(city.unitCostMet(), is(false));
    city.fillTreasury();
    assertThat(city.unitCostMet(), is(false));
    city.fillTreasury();
    assertThat(city.unitCostMet(), is(false));
    city.fillTreasury();
    assertThat(city.unitCostMet(), is(false));
    city.fillTreasury();
    assertThat(city.unitCostMet(), is(false));
    city.fillTreasury();
    assertThat(city.unitCostMet(), is(true));
    city.produceUnit();
    assertThat(city.getProduction(), is(GameConstants.UFO));
    assertThat(city.getTreasury(), is(0));
  }

  @Test
  public void shouldChangeCityOwnerFromRedToBlue() {
    city = new CityImpl(Player.RED);
    assertThat(city.getOwner(), is(Player.RED));
    city.setOwner(Player.BLUE);
    assertThat(city.getOwner(), is(Player.BLUE));
  }
}
