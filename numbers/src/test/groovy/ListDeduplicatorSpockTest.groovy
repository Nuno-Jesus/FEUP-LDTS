import com.aor.numbers.GenericListSorter
import com.aor.numbers.ListDeduplicator
import spock.lang.Specification

class ListDeduplicatorSpockTest extends Specification{
    private List<Integer> list
    private List<Integer> expected

    def setup(){
        list = [1, 2, 4, 2, 5]
        expected = [1, 2, 4, 5]
    }

    def "eliminate duplicate elements of an array"(){
        given:
            def sorter = Mock(GenericListSorter)
            sorter.sort(_ as List<Integer>) >> Arrays.asList(1, 2, 2, 4, 5)
            def deduplicator = new ListDeduplicator(sorter)

        expect:
            deduplicator.deduplicate(list) == expected
    }

    def "distinct bug 8726"(){
        given:
            def sorter = Mock(GenericListSorter)
            sorter.sort(_ as List<Integer>) >> Arrays.asList(1, 2, 2, 4)
            def deduplicator = new ListDeduplicator(sorter)

        expect:
            deduplicator.deduplicate(Arrays.asList(1, 2, 2, 4)) == Arrays.asList(1, 2, 4)

    }
}
