package cat.itacademy.s05.t01.model.cards;

public enum Ranks {
    TWO("2",2),
    THREE("3",3),
    FOUR("4",4),
    FIVE("5",5),
    SIX("6", 6),
    SEVEN("7",7),
    EIGHT("8",8),
    NINE("9",9),
    TEN("10",10),
    J("J",10),
    Q("Q",10),
    K("K",10),
    A("A",11);

    private final String label;
    private final int value;

    Ranks(String label, int value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public int getValue() {
        return value;
    }
}
