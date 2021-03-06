package edu.com.mvplibrary.ui.activity;

import android.os.AsyncTask;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import org.json.JSONException;

import edu.com.mvplibrary.model.Channel;
import edu.com.mvplibrary.model.http.HttpUtil;
import edu.com.mvplibrary.model.http.callback.StringHttpCallback;
import edu.com.mvplibrary.model.http.request.HttpRequest;
import edu.com.mvplibrary.ui.widget.ViewDisplay;
import edu.com.mvplibrary.util.ToastUtils;

/**
 * Created by Anthony on 2016/5/11.
 * Class Note:
 */
public abstract class AbsSplashActivity extends AbsBaseActivity {
    private static final short SPLASH_SHOW_SECONDS = 1;
    private long mShowMainTime;

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initViewsAndEvents() {
        mShowMainTime = System.currentTimeMillis() + SPLASH_SHOW_SECONDS * 2000;
        initData();
    }

    protected void initData() {


        HttpRequest.Builder builder = new HttpRequest.Builder();
        HttpRequest request = builder.url(getFirstUrl()).build();
        HttpUtil.getInstance().loadString(request, new StringHttpCallback() {
            @Override
            public void onResponse(String response) {
                //// TODO: 2016/5/17  get url response
                showView();
                ToastUtils.getInstance().showToast("获取一级页面成功");
            }

            @Override
            public void onError(String error) {
                // TODO: 2016/5/17  error get url response
                showView();
                ToastUtils.getInstance().showToast("获取一级页面失败");
            }
        });
    }

    private void showView() {
        AsyncTask<String, String, String> showMainTask = new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String[] params) {
                if (System.currentTimeMillis() < mShowMainTime) {
                    try {
                        long sleepTime = mShowMainTime - System.currentTimeMillis();
                        if (sleepTime > 0) {
                            Thread.sleep(mShowMainTime - System.currentTimeMillis());
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                return null;
            }

            @Override
            protected void onPostExecute(String o) {
                showMain();
                finish();
            }
        };

        showMainTask.execute();
    }

    protected void showMain() {
//        Channel channel =new Channel();
//        channel.setType("1002");
//        ViewDisplay.initialView(mContext, channel);

    }

    protected abstract String getFirstUrl();

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return false;
    }
}
