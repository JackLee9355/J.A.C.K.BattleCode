package jackPlayer.Communications;

public enum EntityType {
    ANCHOR(0),
    AD_WELL(1),
    MN_WELL(2),
    EX_WELL(3),
    HEADQUARTER(4); // TODO: more types?
    public final int id;

    EntityType(int id) {
        this.id = id;
    }
}
