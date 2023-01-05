package com.ecommerce.category.config.p6spy;

import java.util.Locale;

import org.hibernate.engine.jdbc.internal.FormatStyle;

import com.p6spy.engine.logging.Category;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;

public class P6spyFormat implements MessageFormattingStrategy {
    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared,
            String sql, String url) {
        sql = formatSql(category, sql);
        return elapsed + "ms|" + sql;
    }

    private String formatSql(String category, String sql) {
        if (sql == null || sql.trim().equals(""))
            return sql;

        if (Category.STATEMENT.getName().equals(category)) {
            String tempSql = sql.trim().toLowerCase(Locale.ROOT);

            if (tempSql.startsWith("create") || tempSql.startsWith("alter") || tempSql.startsWith("comment")) {
                sql = FormatStyle.DDL.getFormatter().format(sql);
            } else {
                sql = FormatStyle.BASIC.getFormatter().format(sql);
            }
        }

        return sql;
    }
}
