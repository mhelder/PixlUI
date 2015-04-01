package com.neopixl.pixlui.components.textview;

import android.content.Context;
import android.graphics.Typeface;
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
    }

    public TextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context, attrs, 0);
    }

    public TextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setCustomFont(context, attrs, defStyle);
    }

    private void setCustomFont(Context ctx, AttributeSet attrs, int defStyle) {
        PixlUIUtils.setCustomFont(ctx, this,
                R.styleable.com_neopixl_pixlui_components_textview_TextView,
                R.styleable.com_neopixl_pixlui_components_textview_TextView_typeface,
                attrs, defStyle);
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