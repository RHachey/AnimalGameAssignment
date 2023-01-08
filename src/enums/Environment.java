package enums;

public enum Environment {

    ICE(0.75, 1.5,1),
    FOREST(0.75, 1.5,1),
    DESERT(0.75, 1.5,1);

    private double fireMod;
    private double waterMod;
    private double poisonMod;

    Environment (double fireMod, double waterMod, double poisonMod) {

        this.fireMod = fireMod;
        this.waterMod = waterMod;
        this.poisonMod = poisonMod;

    }

}
