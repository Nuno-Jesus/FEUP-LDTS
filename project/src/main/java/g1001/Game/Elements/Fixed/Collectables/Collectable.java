package g1001.Game.Elements.Fixed.Collectables;

import com.googlecode.lanterna.graphics.TextGraphics;
import g1001.Game.Elements.Fixed.FixedElement;
import g1001.Direction.Position;

public abstract class Collectable extends FixedElement {
    protected int points;

    public Collectable (Position position, int points){
        super(position);
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    @Override
    protected abstract void draw(TextGraphics graphics);
}
