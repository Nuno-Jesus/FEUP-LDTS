import com.aor.numbers.DivisibleByFilter
import spock.lang.Specification

class DivisibleByFilterSpockTest extends Specification{
    private List<Integer> list1
    private List<Integer> list2

    def setup(){
        list1 = [2, 6, 2764, 290188]
        list2 = [5, 25, 125, 5505, 12345]
    }

    def "accepts numbers divisible by 2"(){
        given:
        def filter = new DivisibleByFilter(2)

        expect:
        list1.every{it -> filter.accept(it)}
        list2.every {it -> !filter.accept(it)}
    }

    def "accepts numbers divisible by 5"(){
        given:
        def filter = new DivisibleByFilter(5)

        expect:
        list1.every{it -> !filter.accept(it)}
        list2.every {it -> filter.accept(it)}
    }
}
