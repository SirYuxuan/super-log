package com.yuxuan66.superlog.provider;

import com.intellij.execution.filters.ConsoleFilterProvider;
import com.intellij.execution.filters.Filter;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.openapi.project.Project;
import com.yuxuan66.superlog.utils.StateUtil;
import com.yuxuan66.superlog.window.SuperLogWindowFactory;
import org.jetbrains.annotations.NotNull;

/**
 * @author Sir丶雨轩
 * @date 2021/4/16
 */
public class SQLLogProvider implements ConsoleFilterProvider {


    @NotNull
    @Override
    public Filter[] getDefaultFilters(@NotNull Project project) {
        return new Filter[]{(data, line) -> {
            if(StateUtil.isMonitor()){
                System.out.println(data);
            }
            return null;
        }};
    }
}
