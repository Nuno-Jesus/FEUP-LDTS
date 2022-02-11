package g1001;

import g1001.Game.GameController;
import g1001.State.PlayState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class GameTest{
  private GameController gameController;

  @BeforeEach
  public void setup(){
    gameController = new GameController(new PlayState(null),160, 65);
  }

  @Test
  public void terminalSizeTest(){
    Assertions.assertEquals(gameController.getTerminalCols(), 160);
    Assertions.assertEquals(gameController.getTerminalRows(), 65);
  }

}