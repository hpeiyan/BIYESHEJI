package com.gdin.teach.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.gdin.teach.R;
import com.gdin.teach.adapter.MainTeacherAdapter;
import com.gdin.teach.util.CommomUtil;
import com.gdin.teach.view.NoScrollViewPager;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 黄培彦 on 16/3/13.
 * Email: peiyanhuang@yeah.net
 * 类说明:
 */
public class MainTeacherFragment extends BaseFragment implements View.OnClickListener {
    @Bind(R.id.vp_main_teacher)
    NoScrollViewPager mVpMainTeacher;
    @Bind(R.id.rb_teacher_before_class)
    RadioButton mRbTeacherBeforeClass;
    @Bind(R.id.rb_teacher_in_class)
    RadioButton mRbTeacherInClass;
    @Bind(R.id.rb_teacher_personal)
    RadioButton mRbTeacherPersonal;
    @Bind(R.id.rg_main_teacher_bottom)
    RadioGroup mRgMainTeacherBottom;

    public ArrayList<BaseFragment> mFragmentArrayList;
    public FragmentManager mFragmentManager;
    public MainTeacherAdapter mMainTeacherAdapter;

    @Override
    public View initView() {
        return mActivity.getLayoutInflater().inflate(R.layout.fragment_main_teacher, null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initListener();
        mFragmentManager = getActivity().getSupportFragmentManager();
        mMainTeacherAdapter = new MainTeacherAdapter(mFragmentArrayList, mFragmentManager);
        mVpMainTeacher.setAdapter(mMainTeacherAdapter);
        mRbTeacherBeforeClass.setChecked(true);
        mVpMainTeacher.setCurrentItem(0);
        mVpMainTeacher.setOffscreenPageLimit(3);
    }

    private void initListener() {
        mRbTeacherBeforeClass.setOnClickListener(this);
        mRbTeacherInClass.setOnClickListener(this);
        mRbTeacherPersonal.setOnClickListener(this);
    }

    private void initData() {
        mFragmentArrayList = new ArrayList<BaseFragment>();
        mFragmentArrayList.add(new BeforeClassTeacherFragment());
        mFragmentArrayList.add(new InClassTeacherFragment());
        mFragmentArrayList.add(new PersonalTeacherFragment());

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rb_teacher_before_class:
                CommomUtil.toastMessage(getContext(), "rb_teacher_before_class");
                mRgMainTeacherBottom.check(R.id.rb_teacher_before_class);
                mVpMainTeacher.setCurrentItem(0);
                break;
            case R.id.rb_teacher_in_class:
                mRgMainTeacherBottom.check(R.id.rb_teacher_in_class);
                mVpMainTeacher.setCurrentItem(1);
                break;
            case R.id.rb_teacher_personal:
                mRgMainTeacherBottom.check(R.id.rb_teacher_personal);
                mVpMainTeacher.setCurrentItem(2);
                break;

        }
    }
}
