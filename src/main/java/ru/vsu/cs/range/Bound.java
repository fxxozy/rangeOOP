package ru.vsu.cs.range;

public class Bound<C extends Comparable> implements Comparable<Bound<C>> {
    final C endPoint;
    final BoundType boundType;

    public Bound(C endPoint, BoundType boundType) {
        this.endPoint = endPoint;
        this.boundType = boundType;
    }

    static <C extends Comparable> Bound<C> closed(C endPoint) {
        return new Bound<>(endPoint, BoundType.CLOSED);
    }

    static <C extends Comparable> Bound<C> open(C endPoint) {
        return new Bound<>(endPoint, BoundType.OPEN);
    }

    @Override
    public int compareTo(Bound<C> cBound) {
        return this.endPoint.compareTo(cBound.endPoint);
    }

    public BoundType getBoundType() {
        return boundType;
    }

    public String describeAsLowerBound() {
        return (boundType == BoundType.CLOSED ? "[" : "(") + endPoint;
    }

    public String describeAsUpperBound() {
        return endPoint + (boundType == BoundType.CLOSED ? "]" : ")");
    }
}
