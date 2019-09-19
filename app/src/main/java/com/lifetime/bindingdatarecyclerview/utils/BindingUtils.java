package com.lifetime.bindingdatarecyclerview.utils;

import android.annotation.SuppressLint;

public class BindingUtils {

    @SuppressLint("DefaultLocale")
    public static String convertToSuffix(long count) {
        if (count < 1000) return "" + count;
        int exp = (int) (Math.log(count) / Math.log(1000));
        return String.format("%.1f%c",
                count / Math.pow(1000, exp),
                "kmgtpe".charAt(exp - 1));
    }

    // -- Debug here to learn this

    //    public static String withSuffix(long count) {
//        if (count < 1000) return "" + count;
//        float x = (float) Math.log(count);
//        float y = (float) Math.log(1000);
//        int exp = (int) (x/y);
//        double z = Math.pow(1000, exp);
//        double k = count/z;
//        return String.format("%.1f %c",
//                k,
//                "kMGTPE".charAt(exp-1));
//    }

    // --
}
