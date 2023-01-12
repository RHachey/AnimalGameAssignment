package enums;

public enum Attack {

    FIREBREATH(0,7, "FIRE BREATH"),
    WATERGUN(1,7, "WATER GUN"),
    POISONSPRAY(2,7, "POISON SPRAY"),
    FLAMINGPUNCH(0,6, "FLAMING PUNCH"),
    TIDALWAVE(1,6, "TIDAL WAVE"),
    THORNBLOW(2,6, "THORN BLOW");

    private int element;
    private int type;
    private String attackName;

    public String attackName() {

        return this.attackName;

    }

    public int element() {

        return element;

    }

    public int type() {

        return type;

    }

    Attack(int element, int type, String attackName) {

        this.element = element;
        this.type = type;
        this.attackName = attackName;

    }

}
