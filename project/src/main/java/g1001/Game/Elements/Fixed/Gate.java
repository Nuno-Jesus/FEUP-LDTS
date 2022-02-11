package g1001.Game.Elements.Fixed;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import g1001.Direction.Position;

public class Gate extends FixedElement {
    private final char side;

    public Gate (Position position){
        super(position);

        if (position.getX() == 0) side = 'L';
        else if (position.getY() == 0) side = 'T';
        else if (position.getX() == 27) side = 'R';
        else side = 'B';

    }

    public char getSide() {
        return side;
    }

    @Override
    /** DRAW METHOD **/ public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(),position.getY()), " ");
    }
}
