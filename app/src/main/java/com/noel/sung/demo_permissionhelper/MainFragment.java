package com.noel.sung.demo_permissionhelper;

import android.Manifest;
import android.content.pm.PermissionInfo;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.ViewDataBinding;

import com.noel.sung.demo_permissionhelper.databinding.MainFragmentBinding;
import com.noel.sung.library_npattern_permissionhelper.OnPermissionStateListener;
import com.noel.sung.library_npattern_permissionhelper.PermissionHelper;


/**
 * Created by noel on 2020/7/31.
 */
public class MainFragment extends BasePermissionFragment {


    @Override
    protected int getContentView() {
        return R.layout.fragment_main;
    }

    @Override
    protected void init(ViewDataBinding dataBinding) {
        MainFragmentBinding mainFragmentBinding = (MainFragmentBinding) dataBinding;
        mainFragmentBinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PermissionHelper.getInstance().startWithPermissionCheck(MainFragment.this, new OnPermissionStateListener() {
                    @Override
                    public String[] obtainPermissions() {
                        return new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
                    }

                    @Override
                    public void onAcceptPermission() {
                        Toast.makeText(activity, "你允許了EVENT_WHATEVER_YOU_WANT_2", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onRejectPermission() {
                        Toast.makeText(activity, "你拒絕了EVENT_WHATEVER_YOU_WANT_2", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onNeverAskAgainPermission() {
                        Toast.makeText(activity, "你不再提醒EVENT_WHATEVER_YOU_WANT_2", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }

}
