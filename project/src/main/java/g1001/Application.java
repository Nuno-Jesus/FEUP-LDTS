package g1001;

import g1001.Game.GameController;
import g1001.State.*;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException, InterruptedException {
        GameController gameController = new GameController(new MenuState(), 85, 40);
        gameController.run();
    }
}
