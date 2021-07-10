package com.noel.sung.demo_permissionhelper;

import android.Manifest;
import android.content.pm.PermissionInfo;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.noel.sung.demo_permissionhelper.databinding.MainActivityBinding;
import com.noel.sung.library_npattern_permissionhelper.OnPermissionStateListener;
import com.noel.sung.library_npattern_permissionhelper.PermissionHelper;


/**
 * Created by noel on 2020/7/31.
 */
public class MainActivity extends BasePermissionActivity {

    private MainActivityBinding mainActivityBinding;

    @Override
    protected void init() {
        mainActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainActivityBinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PermissionHelper.getInstance().startWithPermissionCheck(MainActivity.this, new OnPermissionStateListener() {
                    @Override
                    public String[] obtainPermissions() {
                        return new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA};
                    }

                    @Override
                    public void onAcceptPermission() {
                        mainActivityBinding.button.setVisibility(View.GONE);
                        replaceFragment(R.id.frame_layout, new MainFragment(), false);
                    }

                    @Override
                    public void onRejectPermission() {
                        Toast.makeText(MainActivity.this, "你拒絕了EVENT_WHATEVER_YOU_WANT", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onNeverAskAgainPermission() {
                        Toast.makeText(MainActivity.this, "你不再提醒EVENT_WHATEVER_YOU_WANT", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }


}