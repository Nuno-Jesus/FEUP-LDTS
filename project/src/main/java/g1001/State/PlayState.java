package g1001.State;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import g1001.Game.Maze;
import g1001.Music.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class PlayState implements State{
  private Maze maze;
  private Music music;
  private boolean isMusicPlaying;

  public PlayState(Maze oldMaze){
    try{
      if(oldMaze == null){
        maze = new Maze(28, 30);
        maze.readMaze(1);
      }

      else
        maze = oldMaze;

      music = new Music("/music/Chill.wav");
    }

    catch(IOException e){
      System.out.println("An exception has occurred.");
    }
  }

  @Override
  public State execute(Screen screen) throws InterruptedException, IOException{
    KeyStroke oldKey = null;
    boolean isPacmanPowered = false;
    int instancesPassed = 0;

    music.startMusic();
    isMusicPlaying = true;

    while (true){
      TimeUnit.MILLISECONDS.sleep(250);

      if (maze.getHostileGhosts().size() == 0) {
        if (!isPacmanPowered) {
          isPacmanPowered = true;
        } else {
          instancesPassed += 1;
          if (instancesPassed == 20) {
            instancesPassed = 0;
            isPacmanPowered = false;

            maze.reverseGhosts();
          }
        }
      }

      draw(screen, 0, 0);
      KeyStroke key = screen.pollInput();

      if (key == null) {
        if (oldKey != null){
          State newState = processKey(oldKey);
          if(newState != null)
            return newState;
        }
        continue;
      }

      State newState = processKey(key);
      if(newState != null)
        return newState;

      oldKey = key;
    }
  }

  @Override
  public void draw(Screen screen, int row, int col) throws IOException{
    screen.clear();
    maze.draw(screen.newTextGraphics());
    screen.refresh();
  }

  @Override
  public State processKey(KeyStroke key) throws IOException{
    if(key == null)
      return null;

    else if(key.getKeyType() == KeyType.Escape) {
      music.stopMusic();
      isMusicPlaying = false;
      return new PauseState(maze);
    }

    else if (key.getKeyType() == KeyType.Character && (key.getCharacter() == 'm' || key.getCharacter() == 'M')) {
      if (isMusicPlaying) {
        System.out.println(101);
        music.stopMusic();
        isMusicPlaying = false;
      }
    }

    else if (key.getKeyType() == KeyType.Character && (key.getCharacter() == 'p' || key.getCharacter() == 'P')) {
      if (!isMusicPlaying) {
        System.out.println(101);
        music.startMusic();
        isMusicPlaying = true;
      }
    }

    else if (key.getKeyType() == KeyType.EOF)
      System.exit(0);

    State state = maze.processKey(key);
    if(state instanceof GameOverState) {
      music.stopMusic();
      isMusicPlaying = false;
    }
    return state;
  }
}
