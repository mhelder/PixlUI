package com.neopixl.pixlui.intern;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.util.AttributeSet;

public class PixlUIUtils {

    public static void setCustomFont(Context ctx, FontStyleView view, int[] attrs, int typefaceId, AttributeSet set, int defStyleAttr, int defStyleRes) {
        // Retrieve style attributes.
        TypedArray a = ctx.obtainStyledAttributes(set, attrs, defStyleAttr, defStyleRes);
        String typefaceName = a.getString(typefaceId);
        a.recycle();

        if (typefaceName != null) {
            view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG | Paint.LINEAR_TEXT_FLAG);
            view.setCustomFont(ctx, typefaceName);
        }
    }

    public static void setAlpha(Context ctx, AlphaView view, int[] attrs, int alphaId, AttributeSet set, int defStyleAttr, int defStyleRes) {
        // Retrieve style attributes.
        TypedArray a = ctx.obtainStyledAttributes(set, attrs, defStyleAttr, defStyleRes);
        float alpha = a.getFloat(alphaId, 1f);
        a.recycle();

        view.setAlpha(alpha);
    }

    public static void setAllCaps(Context ctx, CapitalizableView view, int[] attrs, int allCapsId, AttributeSet set, int defStyleAttr, int defStyleRes) {
        // Retrieve style attributes.
        TypedArray a = ctx.obtainStyledAttributes(set, attrs, defStyleAttr, defStyleRes);
        boolean allCaps = a.getBoolean(allCapsId, false);
        a.recycle();

        view.setAllCaps(allCaps);
    }
}
