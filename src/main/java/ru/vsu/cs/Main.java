package ru.vsu.cs;

import ru.vsu.cs.custom_map.CustomHashMap;
import ru.vsu.cs.custom_map.CustomMap;
import ru.vsu.cs.custom_set.CustomHashSet;
import ru.vsu.cs.custom_set.CustomSet;
import ru.vsu.cs.range.Bound;
import ru.vsu.cs.range.BoundType;
import ru.vsu.cs.range.Range;
import ru.vsu.cs.range.map.HashRangeMap;
import ru.vsu.cs.range.map.RangeMap;
import ru.vsu.cs.range.set.AbstractRangeSet;
import ru.vsu.cs.range.set.HashRangeSet;
import ru.vsu.cs.range.set.RangeSet;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.Callable;

public class Main {

    public static void main(String[] args) {
        HashRangeMap<Integer, String> hashRangeMap = new HashRangeMap<>();
        hashRangeMap.put(Range.closed(1, 5), "a");
        hashRangeMap.put(Range.closed(6, 10), "b");
        hashRangeMap.put(Range.closed(11, 15), "c");
        hashRangeMap.put(Range.closed(16, 20), "d");
        hashRangeMap.put(Range.closed(21, 33), "e");
        hashRangeMap.remove(Range.closed(4, 18));

        System.out.println(hashRangeMap);




//        Range<Integer> range1 = new Range<>(new Bound<>(1, BoundType.OPEN), new Bound<>(15, BoundType.OPEN));
//        Range<Integer> range2 = new Range<>(new Bound<>(3, BoundType.CLOSED), new Bound<>(8, BoundType.CLOSED));
//        System.out.println(range2.isBelong(range1));

//        Range<Integer> range1 = Range.closedOpen(-15,4);
//        Range<Integer> range2 = Range.openClosed(0,9);
//        System.out.println(range1.span(range2));

//        Range<Integer> range3 = Range.open(1,8);
//        Range<Integer> range4 = Range.closedOpen(5,9);
//        System.out.println(range3.subtract(range4));

//        Range<Integer> range = Range.closed(4, 1);
//        System.out.println(range);


//        Bound<Integer> b1 = new Bound<>(1, BoundType.CLOSED);
//        Bound<Integer> b2 = new Bound<>(2, BoundType.CLOSED);
//        //System.out.println(b1.compareTo(b2));
//
//        Range<Integer> range1 = Range.open(4,6);
//        Range<Integer> range2 = Range.closed(4,6);
//        Range<Integer> range3 = Range.openClosed(4,6);
//        Range<Integer> range4 = Range.closedOpen(4,6);
        //System.out.println(range1);

//        System.out.println(range1.isIntersect(range2));

//        System.out.println(range1.in(3));
//        System.out.println(range2.in(3));
//        System.out.println(range3.in(3));
//        System.out.println(range4.in(3));
//        System.out.println("__________");
//        System.out.println(range1.in(4));
//        System.out.println(range2.in(4));
//        System.out.println(range3.in(4));
//        System.out.println(range4.in(4));
//        System.out.println("__________");
//        System.out.println(range1.in(5));
//        System.out.println(range2.in(5));
//        System.out.println(range3.in(5));
//        System.out.println(range4.in(5));
//        System.out.println("__________");
//        System.out.println(range1.in(6));
//        System.out.println(range2.in(6));
//        System.out.println(range3.in(6));
//        System.out.println(range4.in(6));
//        System.out.println("__________");
//        System.out.println(range1.in(7));
//        System.out.println(range2.in(7));
//        System.out.println(range3.in(7));
//        System.out.println(range4.in(7));
//        System.out.println("__________");

//        System.out.println(range1.isIntersect(range2));

//        Range<Integer> r1 = Range.open(1, 3);
//        Range<Integer> r2 = Range.closedOpen(3, 7);
//
//        System.out.println(r2.span(r1));
//        System.out.println(r1.span(r2));

//        System.out.println(range1.isBelong(range2));
//
//        HashRangeSet<Integer> hrs = new HashRangeSet<>();
//        hrs.add(Range.open(0, 1));
//        hrs.add(Range.openClosed(1, 2));
//        System.out.println(hrs);

//        RangeSet<Integer> rSet = HashRangeSet.create();
//        rSet.add(Range.closed(1, 5));
//        rSet.add(Range.closed(6, 10));
//        rSet.add(Range.closed(11, 15));
//        rSet.add(Range.closed(16, 20));
//        rSet.add(Range.closed(7, 12));


//        rSet.add(Range.closed(1, 3));
//        rSet.add(Range.closed(3, 10));
//        rSet.add(Range.closed(2, 5));
//        rSet.add(Range.closed(0, 100));
//        rSet.add(Range.closed(50, 150));
//        rSet.add(Range.closed(-50, 100));
//        System.out.println(rSet);


//        RangeSet<Integer> rangeSet = HashRangeSet.create();
//        rangeSet.add(Range.closed(1, 10));
//        rangeSet.add(Range.closed(11, 20));
//        System.out.println(rangeSet);

//        RangeSet<Integer> rSet = HashRangeSet.create();
//        rSet.add(Range.closed(0, 5));
//        rSet.add(Range.closed(6, 10));
//        rSet.add(Range.closed(11, 15));
//        rSet.remove(Range.closed(-100, 20));
//        System.out.println(rSet);

//        System.out.println(Range.closed(1, 4).subtract(Range.closed(0, 3)));
//        System.out.println(Range.closed(1, 4).subtract(Range.open(3, 6)));

    }
}