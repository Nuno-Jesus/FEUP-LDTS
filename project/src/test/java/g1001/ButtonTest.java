package g1001;

import g1001.Buttons.*;
import g1001.State.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ButtonTest {
  private Button[] buttons = new Button[5];

  @BeforeEach
  public void setup(){
    buttons[0] = new PlayButton(0, 0, new PlayState(null));
    buttons[1] = new HighscoresButton(0, 0, new HighscoresState());
    buttons[2] = new ExitButton(0, 0, new ExitState());
    buttons[3] = new ContinueButton(0, 0, new PlayState(null));
    buttons[4] = new QuitButton(0, 0, new MenuState());
  }

  @Test
  public void verifyButtonCreation(){
    Button mock = Mockito.mock(Button.class);

    for(Button button : buttons){
      Assertions.assertNotNull(button);
      Assertions.assertFalse(button.isSelected());
    }

    Mockito.when(mock.getState()).thenReturn(new PlayState(null));
    Assertions.assertEquals(buttons[0].execute().getClass(), mock.getState().getClass());

    Mockito.when(mock.getState()).thenReturn(new HighscoresState());
    Assertions.assertEquals(buttons[1].execute().getClass(), mock.getState().getClass());

    Mockito.when(mock.getState()).thenReturn(new ExitState());
    Assertions.assertEquals(buttons[2].execute().getClass(), mock.getState().getClass());

    Mockito.when(mock.getState()).thenReturn(new PlayState(null));
    Assertions.assertEquals(buttons[3].execute().getClass(), mock.getState().getClass());

    Mockito.when(mock.getState()).thenReturn(new MenuState());
    Assertions.assertEquals(buttons[4].execute().getClass(), mock.getState().getClass());
  }
}
