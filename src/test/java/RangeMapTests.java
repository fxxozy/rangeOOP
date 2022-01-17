import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.vsu.cs.range.Range;
import ru.vsu.cs.range.map.HashRangeMap;
import ru.vsu.cs.range.map.RangeMap;

import java.util.Map;
import java.util.Set;

public class RangeMapTests {

    @Test
    void rangeMapCreationTest() {
        RangeMap<Integer, String> rangeMap = new HashRangeMap<>();
        Range<Integer> range1 = Range.closed(1, 10);
        Range<Integer> range2 = Range.closed(11, 20);

        String v1 = "a";
        String v2 = "b";

        rangeMap.put(range1, v1);
        rangeMap.put(range2, v2);

        Map<Range<Integer>, String> map = rangeMap.asMapOfRanges();
        Assertions.assertEquals(map.keySet(), Set.of(range1, range2));
    }

    @Test
    void rangeMapGettingValTest() {
        RangeMap<Integer, String> rangeMap = new HashRangeMap<>();
        Range<Integer> range1 = Range.closed(1, 10);
        Range<Integer> range2 = Range.closed(11, 20);

        String v1 = "a";
        String v2 = "b";

        rangeMap.put(range1, v1);
        rangeMap.put(range2, v2);

        Map<Range<Integer>, String> map = rangeMap.asMapOfRanges();
        Assertions.assertEquals(v1, rangeMap.get(5));
        Assertions.assertEquals(v2, rangeMap.get(15));
    }

    @Test
    void rangeSetRemovingTest() {
        RangeMap<Integer, String> rangeMap = new HashRangeMap<>();
        Range<Integer> range1 = Range.closed(1, 10);
        Range<Integer> range2 = Range.closed(11, 20);

        String v1 = "a";
        String v2 = "b";

        rangeMap.put(range1, v1);
        rangeMap.put(range2, v2);

        rangeMap.remove(Range.open(5, 15));
        Assertions.assertNull(rangeMap.get(10));
    }

}
