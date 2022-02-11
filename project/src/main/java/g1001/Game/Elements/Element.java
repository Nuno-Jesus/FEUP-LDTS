package g1001.Game.Elements;

import com.googlecode.lanterna.graphics.TextGraphics;
import g1001.Direction.Position;

public abstract class Element {
    protected Position position;

    public Element(Position position){
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    protected abstract void draw(TextGraphics graphics);
}
