package com.skydoves.colorpickerviewdemo;

/*
 * Copyright (C) 2017 skydoves
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.skydoves.colorpickerview.ColorEnvelope;
import com.skydoves.colorpickerview.ColorPickerView;
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener;
import com.skydoves.colorpickerview.sliders.AlphaSlideBar;

public class MainActivity extends AppCompatActivity {

    private ColorPickerView colorPickerView;

    private boolean FLAG_PALETTE = false;
    private boolean FLAG_SELECTOR = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colorPickerView = findViewById(R.id.colorPickerView);
        colorPickerView.setColorListener(new ColorEnvelopeListener() {
            @Override
            public void onColorSelected(ColorEnvelope envelope, boolean fromUser) {
                setLayoutColor(envelope.getColor());
            }
        });

        final AlphaSlideBar alphaSlideBar = findViewById(R.id.alphaSlideBar);
        colorPickerView.attachAlphaSlider(alphaSlideBar);
        colorPickerView.selectCenter();
    }

    /**
     * set layout color & textView html code
     *
     * @param color color by ColorListener
     */
    private void setLayoutColor(int color) {
        TextView textView = findViewById(R.id.textView);
        textView.setText("#" + colorPickerView.getHexCode(color));

        LinearLayout linearLayout = findViewById(R.id.linearLayout);
        linearLayout.setBackgroundColor(color);
    }

    /**
     * change palette drawable resource
     * you must initialize at first in xml
     *
     * @param v view
     */
    public void palette(View v) {
        if (FLAG_PALETTE)
            colorPickerView.setPaletteDrawable(ContextCompat.getDrawable(this, R.drawable.palette));
        else
            colorPickerView.setPaletteDrawable(ContextCompat.getDrawable(this, R.drawable.palettebar));
        FLAG_PALETTE = !FLAG_PALETTE;
    }

    /**
     * change selector drawable resource
     * you must initialize at first in xml
     *
     * @param v view
     */
    public void selector(View v) {
        if (FLAG_SELECTOR)
            colorPickerView.setSelectorDrawable(ContextCompat.getDrawable(this, R.drawable.wheel));
        else
            colorPickerView.setSelectorDrawable(ContextCompat.getDrawable(this, R.drawable.wheel_dark));
        FLAG_SELECTOR = !FLAG_SELECTOR;
    }

    /**
     * moving selector's points (x, y)
     *
     * @param v view
     */
    public void points(View v) {
        int x = (int) (Math.random() * 600) + 100;
        int y = (int) (Math.random() * 400) + 150;
        colorPickerView.setSelectorPoint(x, y);
    }
}