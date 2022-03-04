package adapter.odev;

import java.util.ArrayList;
import java.util.List;

public class AdapterMain {
    public static void main(String[] args) {
        List<Shape> shapes = new ArrayList<>();
        shapes.add(new RectangleAdapter(new LegacyRectangle()));
        shapes.add(new LineAdapter(new LegacyLine()));

        int x1 = 5, y1 = 10, x2 = -3, y2 = 2;
        shapes.forEach(shape -> shape.draw(x1, y1, x2, y2));
    }
}
