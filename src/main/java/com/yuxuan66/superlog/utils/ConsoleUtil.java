package com.yuxuan66.superlog.utils;

import com.intellij.execution.filters.TextConsoleBuilder;
import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.openapi.project.Project;

/**
 * @author Sir丶雨轩
 * @date 2021/4/16
 */
public class ConsoleUtil {

    private volatile static ConsoleView consoleView;

    public synchronized static ConsoleView getConsole(Project project){

        if(consoleView == null){
            TextConsoleBuilder consoleBuilder = TextConsoleBuilderFactory.getInstance().createBuilder(project);
            consoleView = consoleBuilder.getConsole();
        }

        return consoleView;
    }

    public static void clear(){
        consoleView.clear();
    }

    public static void print(String str, ConsoleViewContentType contentType){
        consoleView.print(str,contentType);
    }

    public static void print(String str){
        consoleView.print(str,ConsoleViewContentType.USER_INPUT);
    }
}
