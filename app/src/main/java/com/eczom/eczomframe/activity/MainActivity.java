package com.eczom.eczomframe.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.eczom.eczomframe.R;
import com.eczom.eczomframe.fragment.CFragment;
import com.eczom.eczomframe.fragment.EFragment;
import com.eczom.eczomframe.fragment.MFragment;
import com.eczom.eczomframe.fragment.OFragment;
import com.eczom.eczomframe.fragment.ZFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity {

    @BindView(R.id.rg_tab)
    RadioGroup rgTab;

    // 各Fragment实例
    private EFragment eFragment;
    private CFragment cFragment;
    private ZFragment zFragment;
    private OFragment oFragment;
    private MFragment mFragment;
    // 当前Fragment缓存
    private Fragment cacheFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFragment();
        initListener();
    }

    private void initFragment() {
        // 初始化各Fragment
        eFragment = new EFragment();
        cFragment = new CFragment();
        zFragment = new ZFragment();
        oFragment = new OFragment();
        mFragment = new MFragment();
    }

    private void initListener() {
        // 监听RadioGroup选择事件
        rgTab.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                Fragment fragment = null;
                switch (i) {
                    case R.id.rb_e:
                        fragment = eFragment;
                        break;
                    case R.id.rb_c:
                        fragment = cFragment;
                        break;
                    case R.id.rb_z:
                        fragment = zFragment;
                        break;
                    case R.id.rb_o:
                        fragment = oFragment;
                        break;
                    case R.id.rb_m:
                        fragment = mFragment;
                        break;
                }
                // 切换对应Fragment
                switchFragment(cacheFragment, fragment);
            }
        });
        // 默认选择E
        rgTab.check(R.id.rb_e);
    }

    private void switchFragment(Fragment nowFragment, Fragment toFragment) {
        if (nowFragment != toFragment) {
            // 缓存即将切换的Fragment，以便下一次对比判断
            cacheFragment = toFragment;
            // 开启事物
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            // 判断即将切换的Fragment是否add过
            if (!toFragment.isAdded()) {
                // 初始无缓存，需判断
                if (nowFragment != null) {
                    // 隐藏当前Fragment
                    ft.hide(nowFragment);
                }
                // 添加即将切换的Fragment，并提交事物
                ft.add(R.id.fl_main, toFragment).commit();
            } else {
                // 初始无缓存，需判断
                if (nowFragment != null) {
                    // 隐藏当前Fragment
                    ft.hide(nowFragment);
                }
                // 显示即将切换的Fragment，并提交事物
                ft.show(toFragment).commit();
            }
        }
    }
}