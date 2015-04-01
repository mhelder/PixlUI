package com.neopixl.pixlui.components.radiobutton;

import com.android.export.AllCapsTransformationMethod;
import com.neopixl.pixlui.R;
import com.neopixl.pixlui.components.textview.FontFactory;
import com.neopixl.pixlui.intern.CapitalizableView;
import com.neopixl.pixlui.intern.FontStyleView;
import com.neopixl.pixlui.intern.PixlUIContants;
import com.neopixl.pixlui.intern.PixlUIUtils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
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
public class RadioButton extends android.widget.RadioButton implements FontStyleView, CapitalizableView {

	public RadioButton(Context context) {
		super(context);
        initAttributes(context, null, 0, 0);
	}

	public RadioButton(Context context, AttributeSet attrs) {
		super(context, attrs);
        initAttributes(context, attrs, 0, 0);
	}

	public RadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initAttributes(context, attrs, defStyleAttr, 0);
	}

    @TargetApi(Build.VERSION_CODES.LOLLIPOP) public RadioButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttributes(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initAttributes(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        setAllCaps(context, attrs, defStyleAttr, defStyleRes);
        setCustomFont(context, attrs, defStyleAttr, defStyleRes);
    }

    private void setAllCaps(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        PixlUIUtils.setAllCaps(context, this,
                R.styleable.com_neopixl_pixlui_components_radiobutton_RadioButton,
                R.styleable.com_neopixl_pixlui_components_radiobutton_RadioButton_android_textAllCaps,
                attrs, defStyleAttr, defStyleRes);
    }

    private void setCustomFont(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        PixlUIUtils.setCustomFont(context, this,
                R.styleable.com_neopixl_pixlui_components_radiobutton_RadioButton,
                R.styleable.com_neopixl_pixlui_components_radiobutton_RadioButton_typeface,
                attrs, defStyleAttr, defStyleRes);
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
