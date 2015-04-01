package com.neopixl.pixlui.components.textview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;

import com.neopixl.pixlui.R;
import com.neopixl.pixlui.intern.FontStyleView;
import com.neopixl.pixlui.intern.PixlUIUtils;

/**
 * TextView with custom font by XML or Code
 * This class provided too a font factory
 * @author odemolliens
 *
 */
public class TextView extends EllipsizingTextView implements FontStyleView {

    public TextView(Context context) {
        super(context);
        initAttributes(context, null, 0, 0);
    }

    public TextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttributes(context, attrs, 0, 0);
    }

    public TextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttributes(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP) public TextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttributes(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initAttributes(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        setCustomFont(context, attrs, defStyleAttr, defStyleRes);
    }

    private void setCustomFont(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        PixlUIUtils.setCustomFont(context, this,
                R.styleable.com_neopixl_pixlui_components_textview_TextView,
                R.styleable.com_neopixl_pixlui_components_textview_TextView_typeface,
                attrs, defStyleAttr, defStyleRes);
    }

    /**
     * Use this method to set a custom font in your code (/assets/fonts/)
     * @param ctx
     * @param font Font Name, don't forget to add file extension
     * @return
     */
    public boolean setCustomFont(Context ctx, String font) {
        Typeface tf = FontFactory.getInstance(ctx).getFont(font);
        if(tf != null) {
            setTypeface(tf);
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onSizeChanged (int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }
}