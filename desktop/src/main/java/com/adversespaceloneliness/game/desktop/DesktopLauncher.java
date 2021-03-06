package com.adversespaceloneliness.game.desktop;

import com.adversespaceloneliness.game.core.AdverseSpaceLoneliness;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {

    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

        config.useVsync(true);
        config.useOpenGL3(true, 3, 0);

        new Lwjgl3Application(new AdverseSpaceLoneliness(), config);
    }
}