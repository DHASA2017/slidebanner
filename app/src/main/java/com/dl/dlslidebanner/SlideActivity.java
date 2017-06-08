package com.dl.dlslidebanner;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dl.commonslidebanner.DLBannerView;
import com.dl.commonslidebanner.DLHolderCreator;
import com.dl.commonslidebanner.DLViewHolder;

import java.util.Arrays;
import java.util.List;

public class SlideActivity extends AppCompatActivity {

    public static final Integer[] RES = {R.drawable.first,R.drawable.second,R.drawable.third,R.drawable.fourth};
    private DLBannerView bannerView;
    private List<Integer> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);
        StatusBarUtils.setFullScreen(this);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        initData();
        initView();
    }

    private void initData() {
        datas = Arrays.asList(RES);
    }

    private void initView() {
        bannerView = (DLBannerView) findViewById(R.id.slide_view);
        bannerView.setPages(datas, new DLHolderCreator() {
            @Override
            public DLViewHolder createViewHolder() {
                return new ViewPagerHolder();
            }
        });
    }

    public static final class ViewPagerHolder implements DLViewHolder<Integer> {
        private ImageView mImageView;
        private TextView tvLogin;

        @Override
        public View createView(Context context) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_banner_slide,null);
            mImageView = (ImageView) view.findViewById(R.id.viewPager_item_image);
            tvLogin = (TextView) view.findViewById(R.id.goto_login);
            return view;
        }

        @Override
        public void onBind(final Context context, int position, Integer data,List<Integer> datas) {
            mImageView.setImageResource(data);
            if(datas.size()-1==position){
                tvLogin.setVisibility(View.VISIBLE);
                tvLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context,"登录成功",Toast.LENGTH_SHORT).show();
                    }
                });
            }else{
                tvLogin.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        bannerView.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        bannerView.start();
    }
}
