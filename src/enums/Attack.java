package enums;

public enum Attack {

    FIREBREATH(0,7),
    WATERGUN(1,7),
    POISONSPRAY(2,7),
    FLAMINGPUNCH(0,6),
    TIDALWAVE(1,6),
    THORNBLOW(2,6);

    private int element;
    private int type;

    Attack(int element, int type) {

        this.element = element;
        this.type = type;

    }

}
