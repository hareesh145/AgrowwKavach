package com.ak.util;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;

import androidx.annotation.NonNull;

import com.ak.R;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class LoaderDialog extends Dialog {
    private Context context;

    public LoaderDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.loader_fragment);

        try {
            GifDrawable gifFromAssets = new GifDrawable(context.getAssets(), "loader.gif");
            GifImageView gifImageView = findViewById(R.id.loader);
            gifImageView.setBackground(gifFromAssets);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
