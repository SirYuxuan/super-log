package com.yuxuan66.superlog.provider;

import com.intellij.execution.filters.ConsoleFilterProvider;
import com.intellij.execution.filters.Filter;
import com.intellij.openapi.project.Project;
import com.yuxuan66.superlog.filter.LogFilter;
import org.jetbrains.annotations.NotNull;

/**
 * @author Sir丶雨轩
 * @date 2021/4/16
 */
public class SQLLogProvider implements ConsoleFilterProvider {

    @NotNull
    @Override
    public Filter[] getDefaultFilters(@NotNull Project project) {
        return new Filter[]{new LogFilter()};
    }

}
