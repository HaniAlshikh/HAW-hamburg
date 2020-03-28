package de.alshikh.haw.grundlagen.fortsetzung.lib;

public final class ToolBox {

    // Private constructor to prevent instantiation
    private ToolBox() {
        throw new UnsupportedOperationException();
    }

    public static String formatNoRound(double d) {
        return d == (long) d ? String.format("%d",(long)d) : String.format("%s",d);
    }
}
