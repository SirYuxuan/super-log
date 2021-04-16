package com.yuxuan66.superlog.action;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;
import com.yuxuan66.superlog.utils.ConsoleUtil;

/**
 * @author Sir丶雨轩
 * @date 2021/4/16
 */
public class ClearConsoleAction extends AnAction {

    public ClearConsoleAction(){
        Presentation presentation = getTemplatePresentation();
        presentation.setText("清空日志");
        presentation.setDescription("清空所有日志");
        presentation.setIcon(AllIcons.Actions.GC);
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        ConsoleUtil.clear();
    }
}
