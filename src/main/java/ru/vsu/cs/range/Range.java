package ru.vsu.cs.range;

public class Range<C extends Comparable>  {
    private Bound<C> lowerBound;
    private Bound<C> upperBound;

    public Range(Bound<C> lowerBound, Bound<C> upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        if (
                lowerBound.compareTo(upperBound) > 0 ||
                lowerBound.boundType == BoundType.OPEN &&
                upperBound.boundType == BoundType.OPEN &&
                lowerBound.endPoint == upperBound.endPoint
        ) {
            throw new IllegalArgumentException("Invalid range: " + toString(lowerBound, upperBound));
        }
    }

    static <C extends Comparable<?>> Range<C> create(Bound<C> lowerBound, Bound<C> upperBound) {
        return new Range<>(lowerBound, upperBound);
    }

    public static <C extends Comparable<?>> Range<C> open(C lower, C upper) {
        return create(Bound.open(lower), Bound.open(upper));
    }

    public static <C extends Comparable<?>> Range<C> closed(C lower, C upper) {
        return create(Bound.closed(lower), Bound.closed(upper));
    }

    public static <C extends Comparable<?>> Range<C> openClosed(C lower, C upper) {
        return create(Bound.open(lower), Bound.closed(upper));
    }

    public static <C extends Comparable<?>> Range<C> closedOpen(C lower, C upper) {
        return create(Bound.closed(lower), Bound.open(upper));
    }

    public boolean isIntersect(Range<C> range) {
        return this.in(range.getLowerEndpoint()) || this.in(range.getUpperEndpoint()) ||
                range.in(this.getLowerEndpoint()) || range.in(this.getUpperEndpoint());
    }

    public boolean isBelong(Range<C> range) {
        return this.lowerBound.endPoint.compareTo(range.lowerBound.endPoint) >= 0 &&
                        this.upperBound.endPoint.compareTo(range.upperBound.endPoint) <= 0;
    }

    public boolean in(C value) {
        boolean flag = true;

        if (
                value.compareTo(this.lowerBound.endPoint)
                        <
                        (this.lowerBound.boundType == BoundType.CLOSED ? 0 : 1)
        ) {
            flag &= false;
        }
        else {
            flag &= true;
        }

        if (
                value.compareTo(this.upperBound.endPoint)
                        >
                        (this.upperBound.boundType == BoundType.CLOSED ? 0 : -1)
        ) {
            flag &= false;
        }
        else {
            flag &= true;
        }
        return flag;
    }

    public Range<C> span(Range<C> range) {
        Range<C> newRange = this;
        if (this.isIntersect(range)) {
            if (range.lowerBound.endPoint.compareTo(this.lowerBound.endPoint) < 0) {
                newRange.setLowerBound(range.getLowerBound());
            }
            if (range.upperBound.endPoint.compareTo(this.upperBound.endPoint) > 0) {
                newRange.setUpperBound(range.getUpperBound());
            }
        }
        return newRange;
    }

    private Range<C> leftSpan(Range<C> range) {
        Bound<C> newLeftBound = new Bound<>(range.getLowerEndpoint(), range.getLowerBoundType());
        Bound<C> newRightBound = new Bound<>(this.getUpperEndpoint(), this.getUpperBoundType());
        return new Range<>(newLeftBound, newRightBound);
    }

    private Range<C> rightSpan(Range<C> range) {
        Bound<C> newLeftBound = new Bound<>(this.getLowerEndpoint(), this.getLowerBoundType());
        Bound<C> newRightBound = new Bound<>(range.getUpperEndpoint(), range.getUpperBoundType());
        return new Range<>(newLeftBound, newRightBound);
    }

    public Range<C> merge(Range<C> range) {
        if (range.isBelong(this)) {
            return this;
        }
        else if (this.isBelong(range)) {
            return range;
        }
        else {
            return null;
        }
    }

    public Range<C> subtract(Range<C> range) {
        if (this.isIntersect(range)) {
            if (range.upperBound.endPoint.compareTo(this.upperBound.endPoint) <= 0) {
                return leftSubtract(range);
            }
            else {
                return rightSubtract(range);
            }
        }
        return null;
    }

    private Range<C> leftSubtract(Range<C> range) {
        BoundType newLeftBoundType = range.getUpperBoundType() == BoundType.CLOSED ? BoundType.OPEN : BoundType.CLOSED;
        Bound<C> newLeftBound = new Bound<>(range.getUpperEndpoint(), newLeftBoundType);
        
        Bound<C> newRightBound = this.getUpperBound();
        return new Range<>(newLeftBound, newRightBound);
    }

    private Range<C> rightSubtract(Range<C> range) {
        Bound<C> newLeftBound = this.getLowerBound();

        BoundType newRightBoundType = range.getLowerBoundType() == BoundType.CLOSED ? BoundType.OPEN : BoundType.CLOSED;
        Bound<C> newRightBound = new Bound<>(range.getLowerEndpoint(), newRightBoundType);
        return new Range<>(newLeftBound, newRightBound);
    }

    @Override
    public String toString() {
        return toString(this.lowerBound, this.upperBound);
    }

    private String toString(Bound<C> lowerBound, Bound<C> upperBound) {
        StringBuilder sb = new StringBuilder();
        sb.append(lowerBound.describeAsLowerBound());
        sb.append("...");
        sb.append(upperBound.describeAsUpperBound());
        return sb.toString();
    }

    public Bound<C> getLowerBound() {
        return lowerBound;
    }

    public Bound<C> getUpperBound() {
        return upperBound;
    }

    public C getLowerEndpoint() {
        return lowerBound.endPoint;
    }

    public C getUpperEndpoint() {
        return upperBound.endPoint;
    }

    public BoundType getLowerBoundType() {
        return lowerBound.boundType;
    }

    public BoundType getUpperBoundType() {
        return upperBound.boundType;
    }

    public void setLowerBound(Bound<C> lowerBound) {
        this.lowerBound = lowerBound;
    }

    public void setUpperBound(Bound<C> upperBound) {
        this.upperBound = upperBound;
    }
}
