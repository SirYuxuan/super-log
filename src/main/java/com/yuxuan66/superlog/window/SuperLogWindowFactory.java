package com.yuxuan66.superlog.window;

import com.intellij.execution.filters.TextConsoleBuilder;
import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.yuxuan66.superlog.action.ClearConsoleAction;
import com.yuxuan66.superlog.action.EndMonitorAction;
import com.yuxuan66.superlog.action.StartMonitorAction;
import com.yuxuan66.superlog.utils.ConsoleUtil;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * 核心的日志输出窗口
 * @author Sir丶雨轩
 * @date 2021/4/16
 */
public class SuperLogWindowFactory implements ToolWindowFactory {



    private DefaultActionGroup createActionGroup(){
        DefaultActionGroup actionGroup = new DefaultActionGroup();
        actionGroup.add(new StartMonitorAction());
        actionGroup.add(new EndMonitorAction());
        actionGroup.add(new ClearConsoleAction());
        return actionGroup;
    }

    private ActionToolbar createActionToolBar(){
        return ActionManager.getInstance().createActionToolbar("Super Log", createActionGroup(), false);
    }



    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {

        SimpleToolWindowPanel toolWindowPanel = new SimpleToolWindowPanel(false, true);

        toolWindowPanel.setContent(ConsoleUtil.getConsole(project).getComponent());

        toolWindowPanel.setToolbar(createActionToolBar().getComponent());

        Content content =  toolWindow.getContentManager().getFactory().createContent(toolWindowPanel, "By Sir丶雨轩 ", false);

        toolWindow.getContentManager().addContent(content);

        ConsoleUtil.print("欢迎使用SuperLog 日志增强插件 By Sir丶雨轩");

    }
}
