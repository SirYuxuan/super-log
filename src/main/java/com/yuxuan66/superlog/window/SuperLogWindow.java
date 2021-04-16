package com.yuxuan66.superlog.window;

import com.intellij.execution.filters.Filter;
import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.openapi.editor.EditorSettings;
import com.intellij.openapi.editor.ScrollType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.EditorTextField;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.components.JBTextField;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

/**
 * @author Sir丶雨轩
 * @date 2021/4/16
 */
public class SuperLogWindow {


    private JPanel mainPanel;


    EditorTextField textField = new EditorTextField();

    public SuperLogWindow(
        Project project,ToolWindow toolWindow) {
        init( project);

    }

    private void init(Project project) {
        mainPanel = new JPanel();
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize(); //得到屏幕的尺寸
        mainPanel.setLayout(new GridLayout());
        mainPanel.setPreferredSize(new Dimension((int)screenSize.getWidth()-50,(int)screenSize.getHeight()/3*2));
        textField.setPreferredSize(new Dimension((int)screenSize.getWidth()-50,(int)screenSize.getHeight()/3*2));

        textField.setText("欢迎使用SuperLog");
        textField.setOneLineMode(false);
        mainPanel.add(textField);


    }

    public JPanel getContent() {
        return mainPanel;
    }
}
