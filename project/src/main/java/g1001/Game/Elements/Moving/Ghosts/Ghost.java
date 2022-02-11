package g1001.Game.Elements.Moving.Ghosts;

import com.googlecode.lanterna.graphics.TextGraphics;
import g1001.Direction.Direction;
import g1001.Game.Elements.Moving.MovingElement;
import g1001.Direction.Position;

public abstract class Ghost extends MovingElement {
    public Ghost(Position position, Direction direction){
        super(position, direction);
    }

    @Override
    public abstract void draw(TextGraphics graphics);
}
