package edu.com.mvplibrary.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

//import butterknife.ButterKnife;

/**
 * Created by Anthony on 2016/2/25.
 * Class Note:
 * Base Fragment for all the Fragment defined in the project
 * 1 fragment is a View in MVP, extended from {@link AbsBaseFragment} to do
 * some base operation.
 * 2 do operation in initViewAndEvents(){@link #initViewsAndEvents(View rootView)}
 */
public abstract class AbsBaseFragment extends Fragment {
    /**
     * url passed into fragment
     */
    public static String EXTRA_URL = "url";
    private String mUrl;
    /**
     * activity context of fragment
     */
    protected Context mContext;
    /**
     * view of fragment
     */
    protected View mRootView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        if (getArguments() != null) {
            mUrl = getArguments().getString(EXTRA_URL);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(getContentViewID(), container, false);
        ButterKnife.bind(this, mRootView);//绑定framgent 与ButterKnife
        initViewsAndEvents(mRootView);
        return mRootView;

    }


    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public String getFragmentUrl() {
        return mUrl;
    }

//    public void setFragmentUrl(String url) {
//        this.mUrl = url;
//    }


    /**
     * override this method to do operation in the fragment
     */
    protected void initViewsAndEvents(View rootView) {

    }


    /**
     * override this method to return content view id of the fragment
     */
    protected abstract int getContentViewID();
}