package de.tinf15b4.frp.api;

import java.awt.*;

public interface MouseEvent {
    enum Type {UP, DOWN, MOVE}

    Type type();
    Point point();
}
