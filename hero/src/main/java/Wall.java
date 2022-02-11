import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Wall extends Element{
    /******************* CONSTRUCTOR ******************/
    public Wall(int x, int y) {
        super(new Position(x, y));
    }

    /******************** METHODS *********************/

    public void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000"));

        if( (position.getY() % 2 == 0 && position.getX() % 2 == 0) ||
            (position.getY() % 2 == 1 && position.getX() % 2 == 1))
            graphics.putString(new TerminalPosition(position.getX(), position.getY()), "#");
        else
            graphics.putString(new TerminalPosition(position.getX(), position.getY()), "*");
    }
}
