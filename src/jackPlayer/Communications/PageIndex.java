package jackPlayer.Communications;

public enum PageIndex {
    PAGE_NUMBER(0),
    HEADQUARTERS(2),
    WELLS(10);

    public final int index;

    PageIndex(int index) {
        this.index = index;
    }
}
