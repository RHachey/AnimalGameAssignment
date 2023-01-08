package enums;

public enum Defense {

    VOLCANICSHIELD(0,6),
    WATERSPHERE(1,6),
    TOXICSCREEN(2,6),
    SPARKSWERVE(0,7),
    WATERWALK(1,7),
    POISONPARRY(2,7);

    private int element;
    private int type;
    Defense(int element, int type) {

        this.element = element;
        this.type = type;

    }

}
