package com.neopixl.pixlui.components.radiobutton;

import com.android.export.AllCapsTransformationMethod;
import com.neopixl.pixlui.R;
import com.neopixl.pixlui.components.textview.FontFactory;
import com.neopixl.pixlui.intern.FontStyleView;
import com.neopixl.pixlui.intern.PixlUIContants;
import com.neopixl.pixlui.intern.PixlUIUtils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;

/*
 Copyright 2014 Olivier Demolliens

 Licensed under the Apache License, Version 2.0 (the "License"); you may not use this

 file except in compliance with the License. You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software distributed under

 the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF 

 ANY KIND, either express or implied. See the License for the specific language governing

 permissions and limitations under the License.
 */
public class RadioButton extends android.widget.RadioButton implements FontStyleView {

	public RadioButton(Context context) {
		super(context);
	}

	public RadioButton(Context context, AttributeSet attrs) {
		super(context, attrs);
        initAttributes(context, attrs, 0);
	}

	public RadioButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initAttributes(context, attrs, defStyle);
	}

    private void initAttributes(Context context, AttributeSet attrs, int defStyle) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.com_neopixl_pixlui_components_radiobutton_RadioButton);
        final boolean allCaps = a.getBoolean(R.styleable.com_neopixl_pixlui_components_radiobutton_RadioButton_android_textAllCaps, false);
        a.recycle();

        setAllCaps(allCaps);
        setCustomFont(context, attrs, defStyle);
    }

    private void setCustomFont(Context ctx, AttributeSet attrs, int defStyle) {
        PixlUIUtils.setCustomFont(ctx, this,
                R.styleable.com_neopixl_pixlui_components_textview_TextView,
                R.styleable.com_neopixl_pixlui_components_textview_TextView_typeface,
                attrs, defStyle);
    }

	/**
	 * Use this method to uppercase all char in text.
	 * 
	 * @param allCaps
	 */
	@Override public void setAllCaps(boolean allCaps) {
		if (android.os.Build.VERSION.SDK_INT < 14) {
			if (allCaps) {
				setTransformationMethod(new AllCapsTransformationMethod(
						getContext()));
			} else {
				setTransformationMethod(null);
			}
		} else {
			super.setAllCaps(allCaps);
		}
	}

	/**
	 * Use this method to set a custom font in your code (/assets/fonts/)
	 * 
	 * @param ctx
	 * @param font
	 *            Name, don't forget to add file extension
	 * @return
	 */
	public boolean setCustomFont(Context ctx, String font) {
		Typeface tf = FontFactory.getInstance(ctx).getFont(font);
		if (tf != null) {
			setTypeface(tf);
			return true;
		} else {
			return false;
		}
	}
}
