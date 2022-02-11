package g1001.Game.Elements.Fixed;

import com.googlecode.lanterna.graphics.TextGraphics;
import g1001.Game.Elements.Element;
import g1001.Direction.Position;

public abstract class FixedElement extends Element {

    public FixedElement(Position position){
        super(position);
    }

    @Override
    protected abstract void draw(TextGraphics graphics);
}
