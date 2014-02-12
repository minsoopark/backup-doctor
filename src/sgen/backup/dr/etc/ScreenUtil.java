package sgen.backup.dr.etc;

import android.content.Context;

public class ScreenUtil {
    private static final String TAG = "ScreenUtil";

    public static final int LIMIT_MINIMUM_Y = 50;
    public static final int LIMIT_MAXIMUM_Y = 150;

    private static int limitMiniumYPixel = Integer.MIN_VALUE;
    private static int limitMaximumYPixel = Integer.MIN_VALUE;

    private static float cachedDensity = 0.0f;
    private static float cachedDensityDpi = 0.0f;

    /**
     * dpToPx와 흡사하나 DisplayMetrics.density를 캐시하며, 리턴 값을 int로 cast 해준다.
     *
     * @param dip Device Independent Pixel
     * @return Raster Pixel as int
     */
    public static int toPx(Context context, float dip) {
        if(cachedDensity==0.0f)
            cachedDensity = context.getResources().getDisplayMetrics().density;
        return (int) (dip * cachedDensity);
    }
}
