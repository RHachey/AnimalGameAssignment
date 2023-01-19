package enums;

import javadraw.*;

public enum Environment {

    ICE(0.75, 1.5,1, new Color(177, 177, 255)),
    FOREST(1.5, 1,0.75, new Color(177, 255, 177)),
    DESERT(1, 0.75,1.5, new Color(255, 255, 177));

    private double fireMod;
    private double waterMod;
    private double poisonMod;
    private Color color;

    public Color color() {

        return this.color;

    }

    public double mod(int n) {

        return switch (n) {

            case 0 -> this.fireMod;

            case 1 -> this.waterMod;

            case 2 -> this.poisonMod;

            case 3 -> this.fireMod;

            case 4 -> this.waterMod;

            case 5 -> this.poisonMod;

            default -> 1;

        };

    }

    Environment (double fireMod, double waterMod, double poisonMod, Color color) {

        this.fireMod = fireMod;
        this.waterMod = waterMod;
        this.poisonMod = poisonMod;
        this.color = color;

    }

}
