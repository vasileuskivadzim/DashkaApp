package com.dashkasystems.testapp1;

/**
 * Created by pandasystems on 12/3/16.
 */

class DAPoint {
    public double x;
    public double y;

    public int intX() {
        return (int) x;
    }

    public int intY() {
        return (int) y;
    }

    public DAPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

}

class DASize {
    public double width;
    public double height;

    public int intWidth() {
        return (int) width;
    }

    public int intHeight() {
        return (int) height;
    }

    public DASize(double width, double height) {
        this.width = width;
        this.height = height;
    }
}

class DARect {
    public DAPoint origin;
    public DASize size;

    public DARect(DAPoint origin, DASize size) {
        this.origin = origin;
        this.size = size;
    }

    public DARect(double x, double y, double width, double height) {
        this.origin = new DAPoint(x, y);
        this.size = new DASize(width, height);
    }

    public double x() {
        return origin.x;
    }
    public double y() {
        return origin.y;
    }
    public double width() {
        return size.width;
    }
    public double height() {
        return size.height;
    }
}