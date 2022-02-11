import com.aor.numbers.GenericListFilter
import com.aor.numbers.ListFilterer
import spock.lang.Specification

class ListFiltererSpockTest extends Specification{
    private List<Integer> list1
    private List<Integer> list2

    def setup(){
        list1 = [1, 40, 11214, Integer.MAX_VALUE, 12938, 349, 90]
        list2 = [1, 41, 21, 1253, 3, 9, 1025]
    }

    def "keep only positive elements"(){
        given:
        def filter = Mock(GenericListFilter)
        def filterer = new ListFilterer(filter)
        filter.accept(_ as Integer) >> true

        expect:
        filterer.filter(list1) == list1
    }

    def "keep only elements that are divisible by 2"(){
        given:
        def filter = Mock(GenericListFilter)
        def filterer = new ListFilterer(filter)
        filter.accept(_ as Integer) >> false

        expect:
        filterer.filter(list2).size() == 0
    }
}

