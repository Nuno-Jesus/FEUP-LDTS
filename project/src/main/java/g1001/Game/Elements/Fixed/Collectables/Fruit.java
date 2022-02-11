package g1001.Game.Elements.Fixed.Collectables;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import g1001.Direction.Position;

public class Fruit extends Collectable {
    public Fruit (Position position){
        super(position, 200);
    }

   @Override
   public void draw(TextGraphics graphics){
       graphics.setForegroundColor(TextColor.Factory.fromString("#f5005a"));
       graphics.enableModifiers(SGR.BOLD);
       graphics.putString(new TerminalPosition(position.getX(),position.getY()), "&");
   }
}
