package com.yuxuan66.superlog.utils;

import com.intellij.ide.util.PropertiesComponent;

/**
 * @author Sir丶雨轩
 * @date 2021/4/16
 */
public class StateUtil {

    public static void startMonitor(){
        PropertiesComponent.getInstance().setValue("SuperLogMonitor",true);
    }

    public static void endMonitor(){
        PropertiesComponent.getInstance().setValue("SuperLogMonitor",false);
    }

    public static Boolean isMonitor(){
        return PropertiesComponent.getInstance().getBoolean("SuperLogMonitor",true);
    }
}
