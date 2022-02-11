package g1001.State;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public interface State {
  public State execute(Screen screen) throws InterruptedException, IOException;
  public void draw(Screen screen, int row, int col) throws IOException;
  public State processKey(KeyStroke key) throws IOException;
}
