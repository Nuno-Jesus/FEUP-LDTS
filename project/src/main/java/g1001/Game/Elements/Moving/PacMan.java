package g1001.Game.Elements.Moving;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import g1001.Direction.Direction;
import g1001.Direction.Position;

public class PacMan extends MovingElement {
    private String c;

    public PacMan(Position position, Direction direction){
        super(position, direction);
        this.c = "V";
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getC() {
        return c;
    }

    /** DRAW METHOD **/
    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#fff717"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(),position.getY()), c);
    }
}
