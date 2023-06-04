package net.iiab.lukas;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.opengl.GL;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;

public class WindowManager {

    private long mainWindow;
    // Maintain a collection of open windows
    private List<Long> windows = new ArrayList<>();

    public void init() {
        // Initialize GLFW and create the main window
        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        GLFW.glfwDefaultWindowHints();
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);

        mainWindow = GLFW.glfwCreateWindow(800, 600, "VoxelStadt", 0, 0);
        if (mainWindow == 0) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        GLFW.glfwMakeContextCurrent(mainWindow);
        GLFW.glfwShowWindow(mainWindow);
        GL.createCapabilities();

        // Register callback functions for window management events
        GLFW.glfwSetKeyCallback(mainWindow, new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                handleKeyInput(window, key, action, mods);
            }
        });

        // Add the main window to the collection
        windows.add(mainWindow);
    }

    public void run() {
        // Main game loop
        while (!GLFW.glfwWindowShouldClose(mainWindow)) {
            // Update game logic

            // Render the windows
            for (long window : windows) {
                GLFW.glfwMakeContextCurrent(window);
                glClear(GL_COLOR_BUFFER_BIT);

                // Render the window contents

                GLFW.glfwSwapBuffers(window);
            }

            GLFW.glfwPollEvents();
        }

        // Clean up resources
        GLFW.glfwTerminate();
    }

    private void handleKeyInput(long window, int key, int action, int mods) {
        if (key == GLFW.GLFW_KEY_ESCAPE && action == GLFW.GLFW_PRESS) {
            // Close the current window
            GLFW.glfwSetWindowShouldClose(window, true);
            windows.remove(window);
        }
    }

    public void createWindow(int width, int height, String title) {
        long newWindow = GLFW.glfwCreateWindow(width, height, title, 0, 0);
        if (newWindow == 0) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        // Register callback functions for the new window's events

        windows.add(newWindow);
    }
}