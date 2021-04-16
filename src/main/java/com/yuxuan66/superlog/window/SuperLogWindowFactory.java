package com.yuxuan66.superlog.window;

import com.intellij.execution.filters.Filter;
import com.intellij.execution.filters.TextConsoleBuilder;
import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.openapi.ui.popup.IconButton;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.components.JBTextField;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.tabs.impl.JBEditorTabs;
import com.intellij.ui.treeStructure.Tree;
import com.intellij.util.Icons;
import com.intellij.util.PlatformIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;

/**
 * 核心的日志输出窗口
 * @author Sir丶雨轩
 * @date 2021/4/16
 */
public class SuperLogWindowFactory implements ToolWindowFactory {


    private static ConsoleView console;


    public static void print(String text,ConsoleViewContentType viewContentType){
        if(console!= null){
            console.print(text,viewContentType);
        }
    }

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        TextConsoleBuilder consoleBuilder = TextConsoleBuilderFactory.getInstance().createBuilder(project);
        console = consoleBuilder.getConsole();
        console.print("欢迎使用SuperLog 日志输出增强系统",ConsoleViewContentType.ERROR_OUTPUT);



        SimpleToolWindowPanel toolWindowPanel = new SimpleToolWindowPanel(false, true);
        toolWindowPanel.setContent(console.getComponent());
        DefaultActionGroup toolbarActions = new DefaultActionGroup();
        AnAction action = new AnAction("清空日志","清空所有日志", PlatformIcons.PUBLIC_ICON) {
            @Override
            public void actionPerformed(AnActionEvent e) {
                console.clear();
            }
        };
        toolbarActions.add(action);
        ActionToolbar toolbar = ActionManager.getInstance().createActionToolbar(ActionPlaces.UNKNOWN, toolbarActions, false);

        toolWindowPanel.setToolbar(toolbar.getComponent());

        Content content  = toolWindow.getContentManager().getFactory().createContent(toolWindowPanel, "", false);
        toolWindow.getContentManager().addContent(content);


    }
}
