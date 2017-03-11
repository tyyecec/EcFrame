package com.eczom.eczomframe.fragment;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.eczom.eczomframe.base.BaseFragment;

public class ZFragment extends BaseFragment {

    private TextView textView;

    @Override
    public View initView() {
        textView = new TextView(bfContext);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(70);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("Z");
    }
}
