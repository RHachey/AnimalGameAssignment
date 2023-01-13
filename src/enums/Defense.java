package enums;

public enum Defense {

    VOLCANICSHIELD(3,6, "VOLCANIC SHIELD"),
    WATERSPHERE(4,6, "WATER SPHERE"),
    TOXICSCREEN(5,6, "TOXIC SCREEN"),
    SPARKSWERVE(3,7, "SPARK SWERVE"),
    WATERWALK(4,7, "WATER WALK"),
    POISONPARRY(5,7, "POISON PARRY");

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
