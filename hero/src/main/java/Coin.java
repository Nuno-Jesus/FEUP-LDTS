import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Coin extends Element{
    /******************* CONSTRUCTOR ******************/

    public Coin(int x, int y){
        super(new Position(x, y));
    }

    /******************** METHODS *********************/

    public void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
        graphics.putString(new TerminalPosition(position.getX(), position.getY()),"0");
    }
}
