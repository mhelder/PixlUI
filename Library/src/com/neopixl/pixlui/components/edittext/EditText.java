package com.neopixl.pixlui.components.edittext;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;

import com.neopixl.pixlui.R;
import com.neopixl.pixlui.components.textview.FontFactory;
import com.neopixl.pixlui.intern.FontStyleView;
import com.neopixl.pixlui.intern.PixlUIUtils;

public class EditText extends android.widget.EditText implements FontStyleView {

	public EditText(Context context) {
        super(context);
        initAttributes(context, null, 0, 0);
	}

	public EditText(Context context, AttributeSet attrs) {
		super(context, attrs);
        initAttributes(context, attrs, 0, 0);
	}

	public EditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
        initAttributes(context, attrs, defStyle, 0);
	}

    @TargetApi(Build.VERSION_CODES.LOLLIPOP) public EditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttributes(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initAttributes(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        setCustomFont(context, attrs, defStyleAttr, defStyleRes);
    }

	private void setCustomFont(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        PixlUIUtils.setCustomFont(context, this,
                R.styleable.com_neopixl_pixlui_components_edittext_EditText,
                R.styleable.com_neopixl_pixlui_components_edittext_EditText_typeface,
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