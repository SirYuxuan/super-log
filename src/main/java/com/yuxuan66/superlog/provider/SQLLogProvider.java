package com.yuxuan66.superlog.provider;

import cn.hutool.core.util.ReUtil;
import cn.hutool.db.sql.SqlUtil;
import com.intellij.execution.filters.ConsoleFilterProvider;
import com.intellij.execution.filters.Filter;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.openapi.project.Project;
import com.yuxuan66.superlog.utils.ConsoleUtil;
import com.yuxuan66.superlog.utils.StateUtil;
import org.jetbrains.annotations.NotNull;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Sir丶雨轩
 * @date 2021/4/16
 */
public class SQLLogProvider implements ConsoleFilterProvider {


    @NotNull
    @Override
    public Filter[] getDefaultFilters(@NotNull Project project) {

        Object lock = new Object();

        AtomicReference<String> lastSQL = new AtomicReference<>("");

        List<String> needQuotationMarks = Arrays.asList("(String)", "(Timestamp)");

        return new Filter[]{(data, line) -> {

            if (StateUtil.isMonitor()) {
                // TODO 这快代码有点烂 等待重构中
                new Thread(() -> {
                    synchronized (lock) {
                        try {

                            if (data.contains("==>  Preparing:")) {
                                lastSQL.set(data);
                            }

                            if (data.contains("Parameters:")) {
                                // 准备处理SQL
                                String[] firstTreatment = data.split("Parameters:");
                                String param = firstTreatment[1].substring(1);

                                Pattern paramReg = Pattern.compile("\\([a-z,A-Z]{3,30}\\),?");

                                String[] params = param.split(paramReg.pattern());

                                List<String> typeList = new ArrayList<>();

                                Matcher matcher = paramReg.matcher(param);

                                while (matcher.find()) {
                                    typeList.add(matcher.group().replace(",", ""));
                                }

                                String sql = lastSQL.get();
                                sql = sql.split("==> {2}Preparing:")[1].substring(1).replace("\n", "");

                                String[] sqlFragment = sql.split("\\= ?\\?");
                                StringBuffer result = new StringBuffer();
                                int count = 0;
                                for (int i = 0; i < sqlFragment.length; i++) {
                                    if (" ".equals(sqlFragment[i])) {
                                        continue;
                                    }
                                    String temp = sqlFragment[i];

                                    String[] arr = temp.split("\\? ?,");
                                    result.append(arr.length > 1 ? arr[0] : sqlFragment[i]);
                                    if (arr.length > 1) {
                                        count += arr.length;

                                        for (int j = 0; j < arr.length; j++) {
                                            if (j == 0) {
                                                continue;
                                            }
                                            if (needQuotationMarks.contains(typeList.get(j + i - 1))) {
                                                result.append("'");
                                                result.append(params[i + j - 1].startsWith(" ") ? params[i + j - 1].substring(1) : params[i + j - 1]);
                                                result.append("'");
                                            } else {
                                                result.append(params[i + j - 1].startsWith(" ") ? params[i + j - 1].substring(1) : params[i + j - 1]);
                                            }
                                            result.append(",");
                                        }
                                        result = new StringBuffer(result.substring(0, result.length() - 1));
                                        result.append(")");
                                    }


                                    if (i + count >= typeList.size()) {
                                        continue;
                                    }
                                    result.append("= ");
                                    if (needQuotationMarks.contains(typeList.get(i + count))) {
                                        result.append("'");
                                        result.append(params[i + count].startsWith(" ") ? params[i + count].substring(1) : params[i + count]);
                                        result.append("'");
                                    } else {
                                        result.append(params[i + count].startsWith(" ") ? params[i + count].substring(1) : params[i + count]);
                                    }
                                }
                                ConsoleUtil.print("\n\n==============================================\n");
                                ConsoleUtil.print(SqlUtil.formatSql(result.toString()));
                                ConsoleUtil.print("\n==============================================\n");

                            }
                        } catch (Exception e) {
                            ConsoleUtil.print("\n\n==============================================\n", ConsoleViewContentType.ERROR_OUTPUT);
                            ConsoleUtil.print("SQL解析异常,数据无法对比", ConsoleViewContentType.ERROR_OUTPUT);
                            ConsoleUtil.print("\n==============================================\n", ConsoleViewContentType.ERROR_OUTPUT);
                        }

                    }

                }).start();

            }
            return null;
        }};
    }
}
