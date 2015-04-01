/*
 Copyright 2013 Neopixl - Olivier Demolliens

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this

file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under

the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF 

ANY KIND, either express or implied. See the License for the specific language governing

permissions and limitations under the License.
 */
package com.neopixl.pixlui.components.imageview;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;

import com.neopixl.pixlui.R;
import com.neopixl.pixlui.intern.AlphaView;
import com.neopixl.pixlui.intern.PixlUIUtils;

/**
 * Add alpha method for old api
 * @author Olivier Demolliens. @odemolliens
 * Dev with Neopixl
 */
public class ImageView extends android.widget.ImageView implements AlphaView {

	/**
	 * Attribute value
	 */
	private float mAlpha = 1;

	public ImageView(Context context) {
		super(context);
		initAttributes(context, null, 0, 0);
	}

	public ImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initAttributes(context, attrs, 0, 0);
	}

	public ImageView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initAttributes(context, attrs, defStyleAttr, 0);
	}

    @TargetApi(Build.VERSION_CODES.LOLLIPOP) public ImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttributes(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initAttributes(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        setAlpha(context, attrs, defStyleAttr, defStyleRes);
    }

    private void setAlpha(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        PixlUIUtils.setAlpha(context, this,
                R.styleable.com_neopixl_pixlui_components_imageview_ImageView,
                R.styleable.com_neopixl_pixlui_components_imageview_ImageView_android_alpha,
                attrs, defStyleAttr, defStyleRes);
    }

	/**
	 * Enable alpha for old api
	 * @param alpha
	 */
	@Override public void setAlpha(float alpha) {
		if (android.os.Build.VERSION.SDK_INT < 11) {
			mAlpha = alpha;
		} else{
			super.setAlpha(alpha);
		}
	}

    @Override public void setImageAlpha(int alpha) {
        setAlpha(alpha);
    }

    /**
	 * Enable alpha for old api
	 * @param alpha
	 */
    @Deprecated @Override public void setAlpha(int alpha) {
		if (android.os.Build.VERSION.SDK_INT < 11) {
            mAlpha = alpha;
		} else{
			super.setAlpha(alpha);
		}
	}

    @Override public float getAlpha() {
        return mAlpha;
    }

    @Override public void onDraw(Canvas canvas){
        if (mAlpha != 1) {
            int drawedAlpha = (int) (mAlpha * 255);
            canvas.saveLayerAlpha(0, 0, canvas.getWidth(), canvas.getHeight(), drawedAlpha, Canvas.HAS_ALPHA_LAYER_SAVE_FLAG);
        }
        super.onDraw(canvas);
	}

}