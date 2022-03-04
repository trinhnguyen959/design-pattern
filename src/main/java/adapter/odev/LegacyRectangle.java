package adapter.odev;

public class LegacyRectangle {
    public void draw(int x, int y, int w, int h) {
        System.out.println(String.format("Drawing rectangle %d %d %d %d", x, y, w, h));
    }
}
