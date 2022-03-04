package adapter.odev;

public class LineAdapter implements Shape {
    private LegacyLine legacyLine;

    public LineAdapter(LegacyLine legacyLine) {
        this.legacyLine = legacyLine;
    }

    @Override
    public void draw(int x1, int y1, int x2, int y2) {
        legacyLine.draw(x1, y1, x2, y2);
    }
}
