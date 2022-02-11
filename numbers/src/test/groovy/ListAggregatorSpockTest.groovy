import com.aor.numbers.GenericListDeduplicator
import com.aor.numbers.ListAggregator
import spock.lang.Specification

class ListAggregatorSpockTest extends Specification{
    private List<Integer> list

    def setup(){
        list = [1, 2, 4, 2, 5]
    }

    def "sum of all elements in an array"(){
        given:
            def aggregator = new ListAggregator()

        expect:
            aggregator.sum(list) == 14
    }

    def "max of an integer array"(){
        given:
            def aggregator = new ListAggregator()

        expect:
            aggregator.max(list) == 5
    }

    def "min of an integer array"(){
        given:
            def aggregator = new ListAggregator()

        expect:
            aggregator.min(list) == 1
    }

    def "number of distinct elements in an array"(){
        given:
            def aggregator = new ListAggregator()
            def deduplicator = Mock(GenericListDeduplicator)
            deduplicator.deduplicate(_ as List<Integer>) >> [1, 2, 4, 5]

        expect:
            aggregator.distinct(list, deduplicator) == 4
    }

    def "max bug 7263"(){
        given:
            def test = [-1, -4, -5]
            def aggregator = new ListAggregator()


    }

    def "distinct bug 8726"(){
        given:
            def test = [1, 2, 4, 2]
            def aggregator = new ListAggregator()
            def deduplicator = Mock(GenericListDeduplicator)
            deduplicator.deduplicate(_ as List<Integer>) >> [1, 2, 4]

        expect:
            aggregator.distinct(test, deduplicator) == 3
    }



}
