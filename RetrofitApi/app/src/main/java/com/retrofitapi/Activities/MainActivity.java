package com.retrofitapi.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.support.transition.Slide;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.RelativeLayout;

import com.retrofitapi.Fragments.Fragment1_Rv;
import com.retrofitapi.Fragments.Fragment2_single_data;
import com.retrofitapi.Interfaces.OnMovieSelected;
import com.retrofitapi.R;
import com.retrofitapi.RecyclerviewSetup.MyRvAdapter;
import com.retrofitapi.Singleton.PreferenceHelper;

public class MainActivity extends AppCompatActivity implements OnMovieSelected {

    private Fragment1_Rv mFragment1;
    private Fragment2_single_data mFragment2;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    private RelativeLayout mContainerOfFragment;
    private Context mContext = MainActivity.this;
    private PreferenceHelper mPreferenceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPreferenceHelper = PreferenceHelper.getInstance(mContext);

        mContainerOfFragment = (RelativeLayout) findViewById(R.id.fragment_container_relativelayout);

        mFragment1 = new Fragment1_Rv();
        mFragment2 = new Fragment2_single_data();


        loadDefaultFragment();

        MyRvAdapter.setOnMovieSelected(this);

    }

    private void loadDefaultFragment() {
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        setAnimationOnFragment(mFragmentTransaction);
        mFragmentTransaction.add(R.id.fragment_container_relativelayout, mFragment1, "frag1");
        mFragmentTransaction.commit();
    }

    @Override
    public void onMovieSelected(boolean isSelected) {
        if (isSelected) {
            mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.remove(mFragment1);
            mFragmentTransaction.addToBackStack(null);
            setAnimationOnFragment(mFragmentTransaction);
            mFragmentTransaction.replace(R.id.fragment_container_relativelayout, mFragment2);
            mFragmentTransaction.commit();
        }
    }

    @Override
    public void onBackPressed() {

        if (mFragmentManager.getBackStackEntryCount() == 0) {
            showExitAlert();
        } else {
            mPreferenceHelper.clearAllData();
            mFragmentManager.popBackStackImmediate();
            mFragmentTransaction = mFragmentManager.beginTransaction();

            setAnimationOnFragment(mFragmentTransaction);
            mFragmentTransaction.remove(mFragment2);
            mFragmentTransaction.replace(R.id.fragment_container_relativelayout, mFragment1);
            mFragmentTransaction.commit();
        }
    }

    private void showExitAlert() {
        AlertDialog dialog = new AlertDialog.Builder(mContext)
                .setMessage("Are you sure you want to quit this App?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .create();
        dialog.show();
    }

    private void setAnimationOnFragment(FragmentTransaction fragmentTransaction) {
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
    }
}
