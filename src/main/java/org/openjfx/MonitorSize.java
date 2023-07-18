package org.openjfx;

import java.awt.Toolkit;

public class MonitorSize {
    java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int height = screenSize.height;
    int width = screenSize.width;
}
