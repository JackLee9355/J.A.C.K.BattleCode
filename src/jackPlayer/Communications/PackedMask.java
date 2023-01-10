package jackPlayer.Communications;

public enum PackedMask {
    AMPLIFIER_PRESENT(0x8000, 15),
    WELL_TYPE(0x7000, 12),
    WELL_X(0x0FC0, 6),
    WELL_Y(0x003F, 0),
    PAGE_INDEX(0x800, 15);

    public final int mask;
    public final int shift;

    PackedMask (int mask, int shift) {
        this.mask = mask;
        this.shift = shift;
    }
}
