package com.anujtayal.test.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.Window;
import android.widget.ProgressBar;

import com.anujtayal.test.R;

public class TestDialog extends Dialog {
    private Context mContext;

    public TestDialog(Context context) {
        super(context);
        mContext = context;
        setCanceledOnTouchOutside(false);
    }

    @Override
    public void show() {
        super.show();
        setContentView(R.layout.layout_app_dialog);
        ProgressBar pBar = findViewById(R.id.progressBar);
        if (pBar != null) {
            pBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(mContext,
                    R.color.colorPrimary), PorterDuff.Mode.SRC_IN);

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
    }
}
