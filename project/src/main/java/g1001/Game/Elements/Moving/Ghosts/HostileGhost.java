package g1001.Game.Elements.Moving.Ghosts;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import g1001.Direction.Direction;
import g1001.Direction.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HostileGhost extends Ghost {
    private final List<String> colors = new ArrayList<>();
    public String color;

    public HostileGhost (Position position, Direction direction){
        super(position, direction);
        fillColors();

        Random rand = new Random();
        int i = rand.nextInt(colors.size() - 1);
        color = colors.get(i);
    }

    private void fillColors(){
        colors.add("#f52500");
        colors.add("#00d4f5");
        colors.add("#ff8503");
        colors.add("#ff8cf4");
    }

    /** DRAW METHOD **/
    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString(color));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(),position.getY()), "M");

    }
}
