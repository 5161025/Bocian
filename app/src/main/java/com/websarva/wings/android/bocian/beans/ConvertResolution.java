package com.websarva.wings.android.bocian.beans;

import android.content.Context;
import android.util.DisplayMetrics;

public class ConvertResolution {

    /**
     * dpからpixelへの変換
     * @param dp
     * @param context
     * @return float pixel
     */
    public static float convertDpPixel(float dp, Context context){
            DisplayMetrics metrics = context.getResources().getDisplayMetrics();
            return dp * metrics.density;
    }

    /**
     * pixelからdpへの変換
     * @param px
     * @param context
     * @return float dp
     */
    public static float convertPixelDp(int px, Context context){
            DisplayMetrics metrics = context.getResources().getDisplayMetrics();
            return px / metrics.density;
    }
}
