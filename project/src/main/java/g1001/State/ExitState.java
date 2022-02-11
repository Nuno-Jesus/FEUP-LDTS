package g1001.State;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class ExitState implements State{
  @Override
  public State execute(Screen screen) throws InterruptedException, IOException{
    System.exit(0);
    return null;
  }

  @Override
  public void draw(Screen screen, int row, int col) throws IOException{

  }

  @Override
  public State processKey(KeyStroke key) throws IOException{
    return null;
  }
}
