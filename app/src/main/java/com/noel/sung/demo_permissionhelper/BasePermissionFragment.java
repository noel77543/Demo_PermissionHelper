package com.noel.sung.demo_permissionhelper;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.noel.sung.library_npattern_permissionhelper.PermissionHelper;


/**
 * Created by noel on 2020/7/31.
 */
public abstract class BasePermissionFragment extends Fragment {

    protected FragmentActivity activity;
    private View view;


    //-----------

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            activity = (MainActivity) context;
        }
    }

    //-----------

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            ViewDataBinding viewDataBinding = DataBindingUtil.inflate(inflater, getContentView(), null, false);
            view = viewDataBinding.getRoot();
            init(viewDataBinding);
        } else {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
        }
        return view;
    }


    //----------

    /***
     * 設置佈局
     */
    protected abstract int getContentView();

    //----------

    /***
     * 初始化行為
     */
    protected abstract void init(ViewDataBinding dataBinding);

    //----------

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionHelper.getInstance().onRequestPermissionsResult(this, requestCode,permissions,grantResults);
    }
}
