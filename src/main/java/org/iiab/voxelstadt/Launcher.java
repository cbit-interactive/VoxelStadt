package org.iiab.voxelstadt;

import net.iiab.lukas.WindowManager;

public class Launcher {
    public static void main(String[] args){
        WindowManager windowManager = new WindowManager();
        windowManager.init();
        windowManager.run();

    }
}
