package com.neopixl.pixlui.components.textview;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Build;
import android.text.Layout;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.widget.TextView;

import com.neopixl.pixlui.R;

public class EllipsizingTextView extends TextView {
	private static final String ELLIPSIS = "\u2026";
	private static final Pattern DEFAULT_END_PUNCTUATION = Pattern.compile("[\\.,\u2026;:\\s]*$", Pattern.DOTALL);

	public interface EllipsizeListener {
		void ellipsizeStateChanged(boolean ellipsized);
	}

	private final List<EllipsizeListener> ellipsizeListeners = new ArrayList<EllipsizeListener>();
	private boolean isEllipsized;
	private boolean isStale;
	private boolean programmaticChange;
	private String fullText;
	private int maxLines;
	private float lineSpacingMultiplier = 1.0f;
	private float lineAdditionalVerticalPadding = 0.0f;
	/**
	 * The end punctuation which will be removed when appending #ELLIPSIS.
	 */
	private Pattern endPunctuationPattern;

	public EllipsizingTextView(Context context) {
		super(context);
        initAttributes(context, null, 0, 0);
	}

	public EllipsizingTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
        initAttributes(context, attrs, 0, 0);
	}

	public EllipsizingTextView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initAttributes(context, attrs, defStyleAttr, 0);
	}

    @TargetApi(Build.VERSION_CODES.LOLLIPOP) public EllipsizingTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttributes(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initAttributes(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super.setEllipsize(null);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.com_neopixl_pixlui_components_textview_EllipsizingTextView, defStyleAttr, defStyleRes);
        final int maxLines = a.getInt(R.styleable.com_neopixl_pixlui_components_textview_EllipsizingTextView_android_maxLines, Integer.MAX_VALUE);
        setMaxLines(maxLines);
        setEndPunctuationPattern(DEFAULT_END_PUNCTUATION);
        a.recycle();
    }

	public void setEndPunctuationPattern(Pattern pattern) {
		this.endPunctuationPattern = pattern;
	}

	public void addEllipsizeListener(EllipsizeListener listener) {
		if (listener == null) {
			throw new NullPointerException();
		}
		ellipsizeListeners.add(listener);
	}

	public void removeEllipsizeListener(EllipsizeListener listener) {
		ellipsizeListeners.remove(listener);
	}

	public boolean isEllipsized() {
		return isEllipsized;
	}

	@Override
	public void setMaxLines(int maxLines) {
		super.setMaxLines(maxLines);
		this.maxLines = maxLines;
		isStale = true;
	}

	public int getMaxLines() {
		return maxLines;
	}

	public boolean ellipsizingLastFullyVisibleLine() {
		return maxLines == Integer.MAX_VALUE;
	}

	@Override
	public void setLineSpacing(float add, float mult) {
		this.lineAdditionalVerticalPadding = add;
		this.lineSpacingMultiplier = mult;
		super.setLineSpacing(add, mult);
	}

	@Override
	protected void onTextChanged(CharSequence text, int start, int before,
			int after) {
		super.onTextChanged(text, start, before, after);
		if (!programmaticChange) {
			fullText = text.toString();
			isStale = true;
		}
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		if (ellipsizingLastFullyVisibleLine()) {
			isStale = true;
		}
	}

	public void setPadding(int left, int top, int right, int bottom) {
		super.setPadding(left, top, right, bottom);
		if (ellipsizingLastFullyVisibleLine()) {
			isStale = true;
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if (isStale) {
			resetText();
		}
		super.onDraw(canvas);
	}

	private void resetText() {
		String workingText = fullText;
		boolean ellipsized = false;
		Layout layout = createWorkingLayout(workingText);
		int linesCount = getLinesCount();
		if (layout.getLineCount() > linesCount) {
			// We have more lines of text than we are allowed to display.
			workingText = fullText.substring(0, layout.getLineEnd(linesCount - 1)).trim();
			while (createWorkingLayout(workingText + ELLIPSIS).getLineCount() > linesCount) {
				int lastSpace = workingText.lastIndexOf(' ');
				if (lastSpace == -1) {
					break;
				}
				workingText = workingText.substring(0, lastSpace);
			}
			// We should do this in the loop above, but it's cheaper this way.
			workingText = endPunctuationPattern.matcher(workingText).replaceFirst("");
			workingText = workingText + ELLIPSIS;
			ellipsized = true;
		}
		if (!workingText.equals(getText())) {
			programmaticChange = true;
			try {
				setText(workingText);
			} finally {
				programmaticChange = false;
			}
		}
		isStale = false;
		if (ellipsized != isEllipsized) {
			isEllipsized = ellipsized;
			for (EllipsizeListener listener : ellipsizeListeners) {
				listener.ellipsizeStateChanged(ellipsized);
			}
		}
	}

	/**
	 * Get how many lines of text we are allowed to display.
	 */
	private int getLinesCount() {
		if (ellipsizingLastFullyVisibleLine()) {
			int fullyVisibleLinesCount = getFullyVisibleLinesCount();
			if (fullyVisibleLinesCount == -1) {
				return 1;
			} else {
				return fullyVisibleLinesCount;
			}
		} else {
			return maxLines;
		}
	}

	/**
	 * Get how many lines of text we can display so their full height is visible.
	 */
	private int getFullyVisibleLinesCount() {
		Layout layout = createWorkingLayout("");
		int height = getHeight() - getPaddingTop() - getPaddingBottom();
		int lineHeight = layout.getLineBottom(0);
		return height / lineHeight;
	}

	private Layout createWorkingLayout(String workingText) {
		return new StaticLayout(workingText, getPaint(),
				getWidth() - getPaddingLeft() - getPaddingRight(),
				Alignment.ALIGN_NORMAL, lineSpacingMultiplier,
				lineAdditionalVerticalPadding, false /* includepad */);
	}

	@Override
	public void setEllipsize(TruncateAt where) {
		// Ellipsize settings are not respected
	}
}