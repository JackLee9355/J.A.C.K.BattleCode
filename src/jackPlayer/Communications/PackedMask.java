package jackPlayer.Communications;

public enum PackedMask {
    AMPLIFIER_PRESENT(0x8000, 15),
    WELL_TYPE(0x7000, 12),
    WELL_X(0x0FC0, 6),
    WELL_Y(0x003F, 0),
    WELL_WORKER_COUNT(0xFF00, 8),
    WELL_PRESSURE(0x00FF, 0),
    PAGE_INDEX(0xF000, 12),
    COORDINATION(0x0C00, 10),
    FOCUS_X(0x03E0, 5),
    FOCUS_Y(0x001F, 0),
    HEADQUARTER_X(0x0FC0, 6),
    HEADQUARTER_Y(0x003F, 0),
    INPUT_X(0x0FC0, 6),
    INPUT_Y(0x003F, 0),
    INPUT_TYPE(0xF000, 12);

    public final int mask;
    public final int shift;

    PackedMask(int mask, int shift) {
        this.mask = mask;
        this.shift = shift;
    }
}
