package com.noel.sung.demo_permissionhelper;

import android.Manifest;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.ViewDataBinding;

import com.noel.sung.demo_permissionhelper.databinding.MainFragmentBinding;
import com.noel.sung.library_npattern_permissionhelper.DeniedPermission;
import com.noel.sung.library_npattern_permissionhelper.NeverAskPermission;
import com.noel.sung.library_npattern_permissionhelper.ObtainPermission;
import com.noel.sung.library_npattern_permissionhelper.PermissionHelper;


/**
 * Created by noel on 2020/7/31.
 */
public class MainFragment extends BasePermissionFragment {

    private final int EVENT_WHATEVER_YOU_WANT_2 = 456;

    @Override
    protected int getContentView() {
        return R.layout.fragment_main;
    }

    @Override
    protected void init(ViewDataBinding dataBinding) {
        MainFragmentBinding mainFragmentBinding = (MainFragmentBinding)dataBinding;
        mainFragmentBinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PermissionHelper.getInstance().startWithPermissionCheck(MainFragment.this,EVENT_WHATEVER_YOU_WANT_2);
            }
        });
    }


    @ObtainPermission(targetEvent = EVENT_WHATEVER_YOU_WANT_2, permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE})
    private void showToast() {
        Toast.makeText(activity, "你允許了EVENT_WHATEVER_YOU_WANT_2", Toast.LENGTH_SHORT).show();
    }


    @DeniedPermission(targetEvent = EVENT_WHATEVER_YOU_WANT_2)
    private void deniedPermission(){
        Toast.makeText(activity, "你拒絕了EVENT_WHATEVER_YOU_WANT_2", Toast.LENGTH_SHORT).show();
    }

    @NeverAskPermission(targetEvent = EVENT_WHATEVER_YOU_WANT_2)
    private void neverAskAgainPermission(){
        Toast.makeText(activity, "你不再提醒EVENT_WHATEVER_YOU_WANT_2", Toast.LENGTH_SHORT).show();
    }
}
