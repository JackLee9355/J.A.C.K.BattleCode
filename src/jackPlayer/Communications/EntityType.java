package jackPlayer.Communications;

public enum EntityType {
    ANCHOR(0),
    WELL(1),
    HEADQUARTER(2); // TODO: more types?
    public final int id;

    EntityType(int id) {
        this.id = id;
    }
}
