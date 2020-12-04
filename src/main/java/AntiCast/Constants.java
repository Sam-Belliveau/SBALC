package AntiCast;

public interface Constants {
    // Area that is protected from lava casts
    long MAX_X_COORD = 500;
    long MAX_Y_COORD = 500;

    // Amount of time to check for casts over
    long SAMPLE_TIME_MS = 15_000;

    // Number of casts needed to trigger protection
    long MAX_CASTS_WARN = 16;
    long MAX_CASTS_CANCEL = 24;
}
