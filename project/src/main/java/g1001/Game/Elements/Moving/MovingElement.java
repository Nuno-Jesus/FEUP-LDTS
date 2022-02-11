package g1001.Game.Elements.Moving;

import com.googlecode.lanterna.graphics.TextGraphics;
import g1001.Direction.Direction;
import g1001.Game.Elements.Element;
import g1001.Direction.Position;

public abstract class MovingElement extends Element {
    protected Direction direction;

    public MovingElement(Position position, Direction direction){
        super(position);
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Position move(){
        return direction.move(position);
    }

    @Override
    protected abstract void draw(TextGraphics graphics);
}
