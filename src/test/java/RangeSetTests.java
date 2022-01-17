import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.vsu.cs.custom_set.CustomHashSet;
import ru.vsu.cs.custom_set.CustomSet;
import ru.vsu.cs.range.Range;
import ru.vsu.cs.range.set.HashRangeSet;
import ru.vsu.cs.range.set.RangeSet;
import java.util.Set;

public class RangeSetTests {
    @Test
    void rangeSetCreationTest() {
        RangeSet<Integer> rangeSet = new HashRangeSet<>();
        Range<Integer> range1 = Range.closed(1, 10);
        Range<Integer> range2 = Range.closed(11, 20);

        rangeSet.add(range1);
        rangeSet.add(range2);

        Assertions.assertEquals(rangeSet.asRanges(), Set.of(range1, range2));
    }

    @Test
    void rangeSetRemovingTest() {
        RangeSet<Integer> rangeSet = new HashRangeSet<>();
        Range<Integer> range1 = Range.closed(1, 10);
        Range<Integer> range2 = Range.closed(11, 15);
        Range<Integer> range3 = Range.closed(16, 20);
        Range<Integer> rangeRemove = Range.closed(9, 18);

        rangeSet.add(range1);
        rangeSet.add(range2);
        rangeSet.add(range3);
        rangeSet.remove(rangeRemove);

        Assertions.assertNull(rangeSet.rangeContaining(10));
    }
}
