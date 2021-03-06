package com.dl.dlslidebanner;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.dl.commonslidebanner.DLBannerView;
import com.dl.commonslidebanner.DLHolderCreator;
import com.dl.commonslidebanner.DLViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dl
 */

public class MZModeBannerFragment extends Fragment{
    public static final int []RES = new int[]{R.drawable.first,R.drawable.second,R.drawable.third,R.drawable.fourth};
    private DLBannerView mMZBanner;
    private DLBannerView mNormalBanner;


    public static MZModeBannerFragment newInstance(){
        return new MZModeBannerFragment();
    }

    private void initView(View view) {

        mMZBanner = (DLBannerView) view.findViewById(R.id.banner);
        mMZBanner.setBannerPageClickListener(new DLBannerView.BannerPageClickListener() {
            @Override
            public void onPageClick(View view, int position) {
                Toast.makeText(getContext(),"click page:"+position,Toast.LENGTH_LONG).show();
            }
        });
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<RES.length;i++){
            list.add(RES[i]);
        }
        mMZBanner.setIndicatorVisible(false);
        mMZBanner.setPages(list, new DLHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });

        mNormalBanner = (DLBannerView) view.findViewById(R.id.banner_normal);

        mNormalBanner.setPages(list, new DLHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });


    }

    public static class BannerViewHolder implements DLViewHolder<Integer> {
        private ImageView mImageView;
        @Override
        public View createView(Context context) {
            // 返回页面布局文件
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item,null);
            mImageView = (ImageView) view.findViewById(R.id.banner_image);
            return view;
        }

        @Override
        public void onBind(Context context, int position, Integer data,List<Integer> datas) {
            // 数据绑定
            mImageView.setImageResource(data);
        }
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main,null);
        initView(view);
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        mMZBanner.pause();
        mNormalBanner.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMZBanner.start();
        mNormalBanner.start();
    }
}
