package g1001;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.sun.tools.javac.Main;
import g1001.State.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;

public class StateTest {
  private State state;
  private Screen screen;
  private KeyStroke key;

  @BeforeEach
  public void setup(){
    screen = Mockito.mock(Screen.class);
    key = Mockito.mock(KeyStroke.class);
    Mockito.when(key.getKeyType()).thenReturn(KeyType.ArrowDown);
  }

  @Test
  public void verifyMenuLoop() throws IOException, InterruptedException{
    state = Mockito.mock(MenuState.class);
    state.execute(screen);
    state.processKey(key);
    state.draw(screen, 0, 0);

    Mockito.verify(state, times(1)).execute(screen);
    Mockito.verify(state, atLeast(1)).processKey(key);
    Mockito.verify(state, atLeast(1)).draw(screen, 0, 0);
  }

  @Test
  public void verifyPlayLoop() throws IOException, InterruptedException{
    state = Mockito.mock(PlayState.class);
    state.execute(screen);
    state.processKey(key);
    state.draw(screen, 0, 0);

    Mockito.verify(state, times(1)).execute(screen);
    Mockito.verify(state, atLeast(1)).processKey(key);
    Mockito.verify(state, atLeast(1)).draw(screen, 0, 0);
  }

  @Test
  public void verifyHighscoresLoop() throws IOException, InterruptedException {
    state = Mockito.mock(HighscoresState.class);
    state.execute(screen);
    state.processKey(key);
    state.draw(screen, 0, 0);

    Mockito.verify(state, times(1)).execute(screen);
    Mockito.verify(state, atLeast(1)).processKey(key);
    Mockito.verify(state, atLeast(1)).draw(screen, 0, 0);
  }

  @Test
  public void verifyGameOverLoop() throws IOException, InterruptedException {
    state = Mockito.mock(GameOverState.class);
    state.execute(screen);
    state.processKey(key);
    state.draw(screen, 0, 0);

    Mockito.verify(state, times(1)).execute(screen);
    Mockito.verify(state, atLeast(1)).processKey(key);
    Mockito.verify(state, atLeast(1)).draw(screen, 0, 0);
  }

  @Test
  public void verifyPauseLoop() throws IOException, InterruptedException {
    state = Mockito.mock(PauseState.class);
    state.execute(screen);
    state.processKey(key);
    state.draw(screen, 0, 0);

    Mockito.verify(state, times(1)).execute(screen);
    Mockito.verify(state, atLeast(1)).processKey(key);
    Mockito.verify(state, atLeast(1)).draw(screen, 0, 0);
  }

  @Test
  public void verifyExitLoop() throws IOException, InterruptedException {
    state = Mockito.mock(ExitState.class);
    Assertions.assertEquals(state.execute(screen), null);
  }

  @Test
  public void verifyMenuTransitions() throws IOException, InterruptedException{
    state = new MenuState();
    KeyStroke key = Mockito.mock(KeyStroke.class);

    // Choosing the play button
    Mockito.when(key.getKeyType()).thenReturn(KeyType.Enter);
    Assertions.assertEquals(state.processKey(key).getClass(), PlayState.class);

    // Choosing the highscores button
    Mockito.when(key.getKeyType()).thenReturn(KeyType.ArrowDown);
    state.processKey(key);
    Mockito.when(key.getKeyType()).thenReturn(KeyType.Enter);
    Assertions.assertEquals(state.processKey(key).getClass(), HighscoresState.class);

    // Choosing the exit button
    Mockito.when(key.getKeyType()).thenReturn(KeyType.ArrowDown);
    state.processKey(key);
    Mockito.when(key.getKeyType()).thenReturn(KeyType.ArrowDown);
    state.processKey(key);
    Mockito.when(key.getKeyType()).thenReturn(KeyType.Enter);
    Assertions.assertEquals(state.processKey(key).getClass(), ExitState.class);
  }

  @Test
  public void verifyPlayTransitions() throws IOException, InterruptedException{
    state = new PlayState(null);
    KeyStroke key = Mockito.mock(KeyStroke.class);

    Mockito.when(key.getKeyType()).thenReturn(KeyType.Escape);
    Assertions.assertEquals(state.processKey(key).getClass(), PauseState.class);
  }

  @Test
  public void verifyGameOverTransitions() throws IOException, InterruptedException{
    state = new GameOverState(100);
    KeyStroke key = Mockito.mock(KeyStroke.class);

    Mockito.when(key.getKeyType()).thenReturn(KeyType.Enter);
    Assertions.assertEquals(state.processKey(key).getClass(), MenuState.class);
  }

  @Test
  public void verifyPauseTransitions() throws IOException, InterruptedException{
    state = new PauseState(null);
    KeyStroke key = Mockito.mock(KeyStroke.class);

    Mockito.when(key.getKeyType()).thenReturn(KeyType.Enter);
    Assertions.assertEquals(state.processKey(key).getClass(), PlayState.class);

    Mockito.when(key.getKeyType()).thenReturn(KeyType.ArrowDown);
    state.processKey(key);
    Mockito.when(key.getKeyType()).thenReturn(KeyType.Enter);
    Assertions.assertEquals(state.processKey(key).getClass(), MenuState.class);
  }

  @Test
  public void verifyHighscoresTransitions() throws IOException{
    state = new HighscoresState();
    KeyStroke key = Mockito.mock(KeyStroke.class);

    Mockito.when(key.getKeyType()).thenReturn(KeyType.Escape);
    Assertions.assertEquals(state.processKey(key).getClass(), MenuState.class);
  }
}
