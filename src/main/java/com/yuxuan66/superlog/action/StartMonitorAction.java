package com.yuxuan66.superlog.action;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.util.PlatformIcons;
import com.yuxuan66.superlog.utils.StateUtil;

/**
 * @author Sir丶雨轩
 * @date 2021/4/16
 */
public class StartMonitorAction extends AnAction {


    public StartMonitorAction(){
        Presentation presentation = getTemplatePresentation();
        presentation.setText("开始监听");
        presentation.setDescription("开始监听日志输出");
        presentation.setIcon(AllIcons.Actions.Execute);
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        StateUtil.startMonitor();
    }
}
