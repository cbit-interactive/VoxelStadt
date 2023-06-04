package net.iiab.lukas;

public class EngineManager {

    public static final long NANOSECOND = 1000000000L; // TIME IS COMING HELL YEAH
    public static final long FRAMERATE = 1000; // FPS

    private static int fps;
    private static float frametime = 1.0f / FRAMERATE;

    private boolean isRunning;
    private WindowManager window;

}
