// Custom StackPane class that adds a coordinate attribute

import javafx.scene.layout.StackPane;

public class CustomStackPane extends StackPane {
    private int x;
    private int y;

    public CustomStackPane(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}