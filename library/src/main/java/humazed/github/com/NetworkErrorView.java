/*
 * Copyright (C) 2017 İhsan Işık
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package humazed.github.com;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import humazed.github.com.library.R;

/**
 * A custom view for displaying a beautiful network error view.
 */
public class NetworkErrorView extends LinearLayout {
    private ImageView imageView;
    private TextView messageTextView;
    private Button retryButton;

    private RetryListener retryListener;

    public NetworkErrorView(Context context) {
        this(context, null);
    }

    public NetworkErrorView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.nev_style);
    }

    public NetworkErrorView(Context context, AttributeSet attrs, int defStyle) {
        this(context, attrs, defStyle, 0);
    }

    public NetworkErrorView(Context context, AttributeSet attrs, int defStyle, int defStyleRes) {
        super(context, attrs);
        init(attrs, defStyle, defStyleRes);
    }

    private void init(AttributeSet attrs, int defStyle, int defStyleRes) {
        TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.ErrorView, defStyle, defStyleRes);
        LayoutInflater.from(getContext()).inflate(R.layout.network_error_view_layout, this, true);
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER_HORIZONTAL);

        imageView = findViewById(R.id.error_image);
        messageTextView = findViewById(R.id.error_message);
        retryButton = findViewById(R.id.retry_button);

        int imageTint;

        String message;
        int messageColor;

        String retryText;
        int retryBackground;
        int retryColor;

        try {
            message = a.getString(R.styleable.ErrorView_nev_message);
            messageColor = a.getColor(R.styleable.ErrorView_nev_messageColor, -1);
            imageTint = a.getColor(R.styleable.ErrorView_nev_imageTint, -1);
            retryBackground = a.getResourceId(R.styleable.ErrorView_nev_retryBackground, -1);
            retryText = a.getString(R.styleable.ErrorView_nev_retryText);
            retryColor = a.getColor(R.styleable.ErrorView_nev_retryColor, -1);

            if (imageTint != -1) setImageTint(imageTint);

            if (message != null) setMessage(message);

            if (retryText != null) retryButton.setText(retryText);
            if (messageColor != -1) messageTextView.setTextColor(messageColor);
            if (retryColor != -1) retryButton.setTextColor(retryColor);
            if (retryBackground != -1) retryButton.setBackgroundResource(retryBackground);
        } finally {
            a.recycle();
        }

        retryButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (retryListener != null) {
                    retryListener.onRetry();
                }
            }
        });
    }

    /**
     * show {@link NetworkErrorView}.
     */
    public void show() {
        setVisibility(VISIBLE);

        AnimatedVectorDrawableCompat image = AnimatedVectorDrawableCompat.create(getContext(), R.drawable.avd_no_connection);
        imageView.setImageDrawable(image);
        image.start();
    }

    /**
     * Hide {@link NetworkErrorView}.
     */
    public void hide() {
        setVisibility(INVISIBLE);
    }

    /**
     * Tints the error image with given color.
     */
    public NetworkErrorView setImageTint(int color) {
        imageView.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        return this;
    }

    /**
     * Sets the error title to a given {@link java.lang.String}.
     */
    public NetworkErrorView setMessage(String text) {
        messageTextView.setText(text);
        return this;
    }


    /**
     * Returns the current title string.
     */
    public CharSequence getMessage() {
        return messageTextView.getText();
    }

    /**
     * Sets the error title text to a given color.
     */
    public NetworkErrorView setMessageColor(int color) {
        messageTextView.setTextColor(color);
        return this;
    }

    /**
     * Sets the retry button text to a given string.
     */
    public NetworkErrorView setRetryText(String text) {
        retryButton.setText(text);
        return this;
    }

    /**
     * Sets the retry button text to a given string resource.
     */
    public NetworkErrorView setRetryText(int res) {
        retryButton.setText(res);
        return this;
    }

    /**
     * Returns the retry button text.
     */
    public CharSequence getRetryText() {
        return retryButton.getText();
    }

    /**
     * Sets the retry button's text color to a given color.
     */
    public NetworkErrorView setRetryColor(int color) {
        retryButton.setTextColor(color);
        return this;
    }

    /**
     * Attaches a listener to the view which will be notified when retry events occur.
     */
    public NetworkErrorView setRetryListener(RetryListener listener) {
        retryListener = listener;
        return this;
    }

    public interface RetryListener {
        void onRetry();
    }
}