package com.ecommerce.category.core;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {

    public static String getTimeCd() {
        SimpleDateFormat sdf = new SimpleDateFormat("_mmmSS");
        return sdf.format(new Date());
    }
}
