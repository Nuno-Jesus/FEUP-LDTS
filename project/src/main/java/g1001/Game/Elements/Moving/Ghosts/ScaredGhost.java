package g1001.Game.Elements.Moving.Ghosts;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import g1001.Direction.Direction;
import g1001.Direction.Position;

public class ScaredGhost extends Ghost {
    public ScaredGhost (Position position, Direction direction){
        super(position, direction);
    }

    @Override
    public void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString("#ffffff"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(),position.getY()), "S");
    }
}
