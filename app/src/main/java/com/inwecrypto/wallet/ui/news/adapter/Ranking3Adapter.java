package com.inwecrypto.wallet.ui.news.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.inwecrypto.wallet.R;
import com.inwecrypto.wallet.bean.Rank2Bean;
import com.inwecrypto.wallet.bean.Rank3Bean;
import com.inwecrypto.wallet.common.Constant;
import com.inwecrypto.wallet.common.util.DensityUtil;
import com.inwecrypto.wallet.common.util.ScreenUtils;
import com.inwecrypto.wallet.event.BaseEventBusBean;
import com.kelin.scrollablepanel.library.PanelAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * 作者：xiaoji06 on 2018/4/2 14:39
 * github：https://github.com/xiaoji06
 * 功能：
 */

public class Ranking3Adapter extends PanelAdapter {

    private static final int TITLE_TYPE = 0;
    private static final int NAME_TYPE = 1;
    private static final int DATE_TYPE = 2;

    private Context context;

    private ArrayList<Rank3Bean> data;

    private boolean isUp=true;
    private int type=2;

    public Ranking3Adapter(Context context){
        this.context=context;
    }

    @Override
    public int getRowCount() {
        return data.size()+1;
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    public int getItemViewType(int row, int column) {
        if (row == 0) {
            return TITLE_TYPE;
        }
        if (column == 0) {
            return NAME_TYPE;
        }
        return DATE_TYPE;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int row, int column) {
        int viewType = getItemViewType(row, column);
        switch (viewType) {
            case TITLE_TYPE:
                setTitleView(column, (TitleViewHolder) holder);
                break;
            case NAME_TYPE:
                setNameView(row, (NameViewHolder) holder);
                break;
            case DATE_TYPE:
                setDateView(row, column, (DateViewHolder) holder);
                break;
            default:
                setDateView(row, column, (DateViewHolder) holder);
        }
    }

    private void setDateView(int row, int column, DateViewHolder holder) {
        switch (column){
            case 1:
                holder.num.setText(data.get(row-1).getBalance()+"ETH");
                break;
            case 2:
                holder.num.setText(""+data.get(row-1).getDauLastDay());
                break;
            case 3:
                holder.num.setText(data.get(row-1).getVolumeLastDay()+"ETH");
                break;
            case 4:
                holder.num.setText(data.get(row-1).getTxLastDay());
                break;
        }
    }

    private void setNameView(final int row, NameViewHolder holder) {
        holder.namell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new BaseEventBusBean(Constant.EVENT_PAIXU3,row-1));
            }
        });
        holder.no.setText(row+"");
        holder.name.setText(data.get(row-1).getTitle());
        holder.long_name.setText(data.get(row-1).getCategory());
        holder.img.setVisibility(View.GONE);
    }

    @SuppressLint("SetTextI18n")
    private void setTitleView(int column, TitleViewHolder holder) {
        holder.line.setVisibility(View.INVISIBLE);
        switch (column){
            case 0:
                holder.text.setText(R.string.pingtaimingcheng);
                holder.line.setVisibility(View.VISIBLE);
                holder.img.setVisibility(View.GONE);
                break;
            case 1:
                holder.text.setText(R.string.shouyihuizong);
                if (type!=1){
                    holder.img.setImageResource(R.mipmap.paixu_icon);
                }else {
                    if (isUp){
                        holder.img.setImageResource(R.mipmap.paixu_icon_up);
                    }else {
                        holder.img.setImageResource(R.mipmap.paixu_icon_down);
                    }
                }
                holder.titlell.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (type==1){
                            isUp=!isUp;
                        }else {
                            type=1;
                            isUp=true;
                        }
                        EventBus.getDefault().post(new BaseEventBusBean(Constant.EVENT_PAIXU3,-11,isUp?1:0));
                    }
                });
                break;
            case 2:
                holder.text.setText(R.string.rihuoyonghushu);
                if (type!=2){
                    holder.img.setImageResource(R.mipmap.paixu_icon);
                }else {
                    if (isUp){
                        holder.img.setImageResource(R.mipmap.paixu_icon_up);
                    }else {
                        holder.img.setImageResource(R.mipmap.paixu_icon_down);
                    }
                }
                holder.titlell.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (type==2){
                            isUp=!isUp;
                        }else {
                            type=2;
                            isUp=true;
                        }
                        EventBus.getDefault().post(new BaseEventBusBean(Constant.EVENT_PAIXU3,-12,isUp?1:0));
                    }
                });
                break;
            case 3:
                holder.text.setText(R.string.jiaoyiliang24);
                if (type!=3){
                    holder.img.setImageResource(R.mipmap.paixu_icon);
                }else {
                    if (isUp){
                        holder.img.setImageResource(R.mipmap.paixu_icon_up);
                    }else {
                        holder.img.setImageResource(R.mipmap.paixu_icon_down);
                    }
                }
                holder.titlell.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (type==3){
                            isUp=!isUp;
                        }else {
                            type=3;
                            isUp=true;
                        }
                        EventBus.getDefault().post(new BaseEventBusBean(Constant.EVENT_PAIXU3,-13,isUp?1:0));
                    }
                });
                break;
            case 4:
                holder.text.setText(R.string.txliang);
                if (type!=4){
                    holder.img.setImageResource(R.mipmap.paixu_icon);
                }else {
                    if (isUp){
                        holder.img.setImageResource(R.mipmap.paixu_icon_up);
                    }else {
                        holder.img.setImageResource(R.mipmap.paixu_icon_down);
                    }
                }
                holder.titlell.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (type==4){
                            isUp=!isUp;
                        }else {
                            type=4;
                            isUp=true;
                        }
                        EventBus.getDefault().post(new BaseEventBusBean(Constant.EVENT_PAIXU3,-14,isUp?1:0));
                    }
                });
                break;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TITLE_TYPE:
                View title_view=LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.ranking_title_layout, parent, false);
                ViewGroup.LayoutParams title_params = title_view.getLayoutParams();
                title_params.width= (int) (ScreenUtils.getScreenWidth(context)/2.8f);
                title_view.setLayoutParams(title_params);
                return new TitleViewHolder(title_view);
            case NAME_TYPE:
                View name_view=LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.ranking_name_layout, parent, false);
                ViewGroup.LayoutParams name_params = name_view.getLayoutParams();
                name_params.width= (int) (ScreenUtils.getScreenWidth(context)/2.8f);
                name_params.height= DensityUtil.dip2px(context,50);
                name_view.setLayoutParams(name_params);
                return new NameViewHolder(name_view);
            case DATE_TYPE:
                View data_view=LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.ranking_data_layout, parent, false);
                ViewGroup.LayoutParams data_params = data_view.getLayoutParams();
                data_params.width= (int) (ScreenUtils.getScreenWidth(context)/2.8f);
                data_params.height= DensityUtil.dip2px(context,50);
                data_view.setLayoutParams(data_params);
                return new DateViewHolder(data_view);
            default:
                break;
        }
        return new DateViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ranking_data_layout, parent, false));
    }

    private static class TitleViewHolder extends RecyclerView.ViewHolder {

        public View titlell;
        public TextView text;
        public ImageView img;
        public View line;

        public TitleViewHolder(View itemView) {
            super(itemView);
            this.titlell=itemView.findViewById(R.id.titlell);
            this.text = (TextView) itemView.findViewById(R.id.text);
            this.line = itemView.findViewById(R.id.line);
            this.img = (ImageView) itemView.findViewById(R.id.img);
        }

    }

    private static class NameViewHolder extends RecyclerView.ViewHolder {

        public View namell;
        public TextView no;
        public TextView name;
        public TextView long_name;
        public ImageView img;

        public NameViewHolder(View itemView) {
            super(itemView);
            this.namell=itemView.findViewById(R.id.namell);
            this.no = (TextView) itemView.findViewById(R.id.no);
            this.name = (TextView) itemView.findViewById(R.id.name);
            this.long_name = (TextView) itemView.findViewById(R.id.long_name);
            this.img= (ImageView) itemView.findViewById(R.id.img);
        }

    }

    private static class DateViewHolder extends RecyclerView.ViewHolder {

        public TextView num;

        public DateViewHolder(View itemView) {
            super(itemView);
            this.num = (TextView) itemView.findViewById(R.id.num);
        }

    }

    public void setData(ArrayList<Rank3Bean> data){
        this.data=data;
    }
}
