package pt.up.fe.ldts.example5

import spock.lang.Specification

class TurtleSpockTest extends Specification {

    def 'Rotate Left'() {
        given:
            def turtle = new Turtle(5, 5, new North());

        when:
            turtle.execute('L' as char);

        then:
            'W' == turtle.getDirection()

        and:
            turtle.execute('L' as char);

        then:
            'S' == turtle.getDirection()

        and:
            turtle.execute('L' as char);

        then:
            'E' == turtle.getDirection()

        and:
            turtle.execute('L' as char);

        then:
            'N' == turtle.getDirection()

        and:
            5 == turtle.getRow()
            5 == turtle.getColumn()
    }

    def 'Rotate Right'() {
        given:
            def turtle = new Turtle(5, 5, new North());

        when:
            turtle.execute('R' as char);

        then:
            'E' == turtle.getDirection()

        and:
            turtle.execute('R' as char);

        then:
            'S' == turtle.getDirection()

        and:
            turtle.execute('R' as char);

        then:
            'W' == turtle.getDirection()

        and:
            turtle.execute('R' as char);

        then:
            'N' == turtle.getDirection()

        and:
            5 == turtle.getRow()
            5 == turtle.getColumn()
    }

    def 'Forward'() {
        given:
            def turtleN = new Turtle(5, 5, new North())
            def turtleW = new Turtle(5, 5, new West());
            def turtleS = new Turtle(5, 5, new South());
            def turtleE = new Turtle(5, 5, new East());

        when:
            turtleN.execute('F' as char)
            turtleW.execute('F' as char);
            turtleS.execute('F' as char);
            turtleE.execute('F' as char);

        then:
            4 == turtleN.getRow()
            5 == turtleN.getColumn()

        and:
            5 == turtleW.getRow()
            4 == turtleW.getColumn()

        and:
            6 == turtleS.getRow()
            5 == turtleS.getColumn()

        and:
            5 == turtleE.getRow()
            6 == turtleE.getColumn()
    }
}
