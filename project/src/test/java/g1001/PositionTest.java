package g1001;

import g1001.Direction.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PositionTest {
    Position pos1, pos2, pos3;

    @BeforeEach
    public void setup(){
        pos1 = new Position(4, 7);
        pos2 = new Position(4, 7);
        pos3 = new Position(2, 3);
    }

    @Test
    public void createPosition(){
        Position pos = new Position(4, 7);

        Assertions.assertEquals(pos.getX(), 4);
        Assertions.assertEquals(pos.getY(), 7);
    }

    @Test
    public void checkEqual(){
        Assertions.assertTrue(pos1.equals(pos1));
        Assertions.assertTrue(pos1.equals(pos2));
        Assertions.assertFalse(pos2.equals(null));
        Assertions.assertFalse(pos2.equals(pos3));
        Assertions.assertFalse(pos2.equals(""));
    }

    @Test
    public void changeValue(){
        Assertions.assertTrue(pos1.equals(pos2));

        pos1.setX(12);

        Assertions.assertFalse(pos1.equals(pos2));
    }
}
