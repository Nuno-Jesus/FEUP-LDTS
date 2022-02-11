package g1001.Game.Elements.Fixed;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import g1001.Direction.Position;

public class Wall extends FixedElement {
    public Wall (Position position){
        super(position);
    }

    @Override
    /** DRAW METHOD **/ public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#1400f5"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(),position.getY()), "#");
    }
}
