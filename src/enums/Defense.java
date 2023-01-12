package enums;

public enum Defense {

    VOLCANICSHIELD(0,6, "VOLCANIC SHIELD"),
    WATERSPHERE(1,6, "WATER SPHERE"),
    TOXICSCREEN(2,6, "TOXIC SCREEN"),
    SPARKSWERVE(0,7, "SPARK SWERVE"),
    WATERWALK(1,7, "WATER WALK"),
    POISONPARRY(2,7, "POISON PARRY");

    private int element;
    private int type;
    private String defenseName;

    public String defenseName() {

        return defenseName;

    }

    public int element() {

        return element;

    }

    public int type() {

        return type;

    }

    Defense(int element, int type, String defenseName) {

        this.element = element;
        this.type = type;
        this.defenseName = defenseName;

    }

}
