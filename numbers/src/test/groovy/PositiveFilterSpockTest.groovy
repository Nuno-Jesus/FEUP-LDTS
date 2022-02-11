import com.aor.numbers.PositiveFilter
import spock.lang.Specification

class PositiveFilterSpockTest extends Specification{
    private List<Integer> positives
    private List<Integer> negatives

    def setup(){
        positives = [1, 2, 20, 40000, Integer.MAX_VALUE]
        negatives = [0, -1, -2, -30, -65536, Integer.MIN_VALUE]
    }

    def "accepts positive numbers"(){
        given:
        def filter = new PositiveFilter();

        expect:
        positives.every{it -> filter.accept(it)}
    }

    def "does not accept negative numbers"(){
        given:
        def filter = new PositiveFilter();

        expect:
        negatives.every{it -> !filter.accept(it)}
    }
}