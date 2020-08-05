package com.noel.sung.demo_permissionhelper;

import android.Manifest;
import android.content.pm.PermissionInfo;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.noel.sung.demo_permissionhelper.databinding.MainActivityBinding;
import com.noel.sung.library_npattern_permissionhelper.DeniedPermission;
import com.noel.sung.library_npattern_permissionhelper.NeverAskPermission;
import com.noel.sung.library_npattern_permissionhelper.ObtainPermission;
import com.noel.sung.library_npattern_permissionhelper.PermissionHelper;


/**
 * Created by noel on 2020/7/31.
 */
public class MainActivity extends BasePermissionActivity {

    private final int EVENT_WHATEVER_YOU_WANT = 123;
    private MainActivityBinding mainActivityBinding;

    @Override
    protected void init() {
        mainActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainActivityBinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PermissionHelper.getInstance().startWithPermissionCheck(MainActivity.this, EVENT_WHATEVER_YOU_WANT);
            }
        });
    }

    @ObtainPermission(targetEvent = EVENT_WHATEVER_YOU_WANT, permissions = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA})
    private void gotoFragment() {
        mainActivityBinding.button.setVisibility(View.GONE);
        replaceFragment(R.id.frame_layout, new MainFragment(), false);
    }

    @DeniedPermission(targetEvent = EVENT_WHATEVER_YOU_WANT)
    private void deniedPermission() {
        Toast.makeText(this, "你拒絕了EVENT_WHATEVER_YOU_WANT", Toast.LENGTH_SHORT).show();
    }

    @NeverAskPermission(targetEvent = EVENT_WHATEVER_YOU_WANT)
    private void neverAskAgainPermission() {
        Toast.makeText(this, "你不再提醒EVENT_WHATEVER_YOU_WANT", Toast.LENGTH_SHORT).show();
    }

}