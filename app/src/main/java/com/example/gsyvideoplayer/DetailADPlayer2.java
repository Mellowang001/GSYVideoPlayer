package com.example.gsyvideoplayer;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.shuyu.gsyvideoplayer.GSYBaseActivityDetail;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack;
import com.shuyu.gsyvideoplayer.listener.LockClickListener;
import com.shuyu.gsyvideoplayer.video.NormalGSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.GSYADVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer;


public class DetailADPlayer2 extends GSYBaseActivityDetail<NormalGSYVideoPlayer> {

    private NormalGSYVideoPlayer detailPlayer;

    private GSYADVideoPlayer adPlayer;

    private String urlAd = "http://video.7k.cn/app_video/20171202/6c8cf3ea/v.m3u8.mp4";

    private String urlAd2 = "http://video.7k.cn/app_video/20171202/6c8cf3ea/v.m3u8.mp4";

    private String url = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_ad_player2);

        detailPlayer = (NormalGSYVideoPlayer) findViewById(R.id.detail_player);
        adPlayer = (GSYADVideoPlayer) findViewById(R.id.ad_player);

        //普通模式
        resolveNormalVideoUI();

        initVideoBuilderMode();

        detailPlayer.setLockClickListener(new LockClickListener() {
            @Override
            public void onClick(View view, boolean lock) {
                if (orientationUtils != null) {
                    //配合下方的onConfigurationChanged
                    orientationUtils.setEnable(!lock);
                }
            }
        });
        detailPlayer.setStartAfterPrepared(false);
        detailPlayer.setReleaseWhenLossAudio(false);

        GSYVideoOptionBuilder adBuilder = getGSYVideoOptionBuilder();
        adBuilder.setUrl(urlAd)
                .setVideoAllCallBack(new GSYSampleCallBack(){
            @Override
            public void onAutoComplete(String url, Object... objects) {
                adPlayer.release();
                adPlayer.setVisibility(View.GONE);
                //todo 如果在全屏下的处理
                getGSYVideoPlayer().getCurrentPlayer().startAfterPrepared();
            }

        });
        adBuilder.build(adPlayer);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //TODO AD
    }

    @Override
    protected void onPause() {
        super.onPause();
        //TODO AD
    }

    @Override
    protected void onResume() {
        super.onResume();
        //TODO AD
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //TODO AD
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //TODO AD
    }

    @Override
    public NormalGSYVideoPlayer getGSYVideoPlayer() {
        return detailPlayer;
    }

    @Override
    public GSYVideoOptionBuilder getGSYVideoOptionBuilder() {
        //不需要builder的
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.mipmap.xxx1);
        return new GSYVideoOptionBuilder()
                .setThumbImageView(imageView)
                .setUrl(url)
                .setCacheWithPlay(true)
                .setVideoTitle(" ")
                .setIsTouchWiget(true)
                .setRotateViewAuto(false)
                .setLockLand(false)
                .setShowFullAnimation(false)//打开动画
                .setNeedLockFull(true)
                .setSeekRatio(1);
    }

    @Override
    public void onPrepared(String url, Object... objects) {
        super.onPrepared(url, objects);
        adPlayer.setVisibility(View.VISIBLE);
        adPlayer.startPlayLogic();
    }

    @Override
    public void clickForFullScreen() {

    }

    /**
     * 是否启动旋转横屏，true表示启动
     *
     * @return true
     */
    @Override
    public boolean getDetailOrientationRotateAuto() {
        return true;
    }

    @Override
    public void onEnterFullscreen(String url, Object... objects) {
        super.onEnterFullscreen(url, objects);
        //隐藏调全屏对象的返回按键
        GSYVideoPlayer gsyVideoPlayer = (GSYVideoPlayer) objects[1];
        gsyVideoPlayer.getBackButton().setVisibility(View.GONE);
    }


    private void resolveNormalVideoUI() {
        //增加title
        detailPlayer.getTitleTextView().setVisibility(View.VISIBLE);
        detailPlayer.getBackButton().setVisibility(View.VISIBLE);
    }
}

