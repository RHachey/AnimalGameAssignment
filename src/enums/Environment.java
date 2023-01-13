package enums;

public enum Environment {

    ICE(0.75, 1.5,1),
    FOREST(0.75, 1.5,1),
    DESERT(0.75, 1.5,1);

    private double fireMod;
    private double waterMod;
    private double poisonMod;

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

    Environment (double fireMod, double waterMod, double poisonMod) {

        this.fireMod = fireMod;
        this.waterMod = waterMod;
        this.poisonMod = poisonMod;

    }

}
