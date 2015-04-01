package com.neopixl.pixlui.components.button;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;

import com.neopixl.pixlui.R;
import com.neopixl.pixlui.components.textview.FontFactory;
import com.neopixl.pixlui.intern.FontStyleView;
import com.neopixl.pixlui.intern.PixlUIUtils;

public class Button extends android.widget.Button implements FontStyleView {

	public Button(Context context) {
		super(context);
        initAttributes(context, null, 0, 0);
	}

	public Button(Context context, AttributeSet attrs) {
		super(context, attrs);
        initAttributes(context, attrs, 0, 0);
	}

	public Button(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
        initAttributes(context, attrs, defStyleAttr, 0);
	}

    @TargetApi(Build.VERSION_CODES.LOLLIPOP) public Button(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttributes(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initAttributes(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        setCustomFont(context, attrs, defStyleAttr, defStyleRes);
    }

    private void setCustomFont(Context ctx, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        PixlUIUtils.setCustomFont(ctx, this,
                R.styleable.com_neopixl_pixlui_components_button_Button,
                R.styleable.com_neopixl_pixlui_components_button_Button_typeface,
                attrs, defStyleAttr, defStyleRes);
	}

	/**
	 * Use this method to set a custom font in your code (/assets/fonts/)
	 * @param ctx
	 * @param font Name, don't forget to add file extension
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

}