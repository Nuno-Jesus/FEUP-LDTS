import com.aor.numbers.GenericListSorter
import com.aor.numbers.ListSorter
import spock.lang.Specification

class ListSorterSpockTest extends Specification{
    private List<Integer> list
    private List<Integer> expected

    def setup(){
        list = [3, 2, 6, 1, 4, 5, 7]
        expected = [1, 2, 3, 4, 5, 6, 7]
    }

    def "sort a list in crescent order"(){
        given:
            def sorter = new ListSorter()

        expect:
            expected == sorter.sort(list)
    }

    def "distinct bug 8726"(){
        given:
            def test = [1, 2, 4, 2]
            def sorter = new ListSorter()
            def mock = Mock(GenericListSorter)
            mock.sort(_ as List<Integer>) >> Arrays.asList(1, 2, 2, 4)

        expect:
            sorter.sort(test) == mock.sort(test)
    }
}
