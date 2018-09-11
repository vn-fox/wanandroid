package com.wng.wanandroid.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wng.wanandroid.R;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment implements BaseContract.View {

    protected Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        ButterKnife.bind(this, view);
        onFragmentViewCreated();
        return view;

    }
    public void onFragmentViewCreated() {}
    public abstract int getLayout();

    @Override
    public boolean isNetworkConnected() {
        return false;
    }

    @Override
    public void showError(String message) {
        Log.e(getClass().getName(), message);
        Toast.makeText(context, R.string.toast_message_error_occurred, Toast.LENGTH_SHORT).show();
    }

}
