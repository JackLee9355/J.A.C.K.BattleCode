package jackPlayer.Communications;

public enum PageLocation {
    CONTROL(-1, 0, 1, 1),
    HEADQUARTERS(0, 2, 6, 1),
    WELLS(0, 10, 64, 2),
    ENEMY_HEADQUARTERS(1, 2, 6, 1),
    ANCHORS(1, 10, 64, 1),
    INPUT(-1, 1, 2, 1);

    public final int page;
    public final int index;
    public final int end;
    public final int size;
    public static final int NUM_PAGES = 2;

    PageLocation(int page, int index, int end, int size) {
        this.page = page;
        this.index = index;
        this.end = end;
        this.size = size;
    }
}
