package com.inwecrypto.wallet.ui.news.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.inwecrypto.wallet.App;
import com.inwecrypto.wallet.R;
import com.inwecrypto.wallet.bean.ProjectDetaileBean;
import com.inwecrypto.wallet.common.util.AppUtil;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * 作者：xiaoji06 on 2018/2/8 15:21
 * github：https://github.com/xiaoji06
 * 功能：
 */

public class CProjectAdatpter extends CommonAdapter<ProjectDetaileBean> {
    public CProjectAdatpter(Context context, int layoutId, List<ProjectDetaileBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, ProjectDetaileBean projectDetaileBean, int position) {
        holder.getView(R.id.munll).setVisibility(View.INVISIBLE);
        holder.setText(R.id.news, "");
        holder.setText(R.id.time, "");
        holder.getView(R.id.img_bg).setVisibility(View.VISIBLE);
        holder.getView(R.id.img).setVisibility(View.VISIBLE);
        holder.getView(R.id.projectll).setVisibility(View.VISIBLE);
        if (null != projectDetaileBean.getCategory_user() && projectDetaileBean.getCategory_user().isIs_favorite_dot()) {
            holder.getView(R.id.munll).setVisibility(View.VISIBLE);
        }
        Glide.with(mContext).load(R.drawable.project_ico_bg).crossFade().into((ImageView) holder.getView(R.id.img_bg));
        Glide.with(mContext).load(null == projectDetaileBean.getImg() ? "" : projectDetaileBean.getImg()).priority(Priority.LOW).crossFade().into((ImageView) holder.getView(R.id.img));
        holder.setText(R.id.title, (null == projectDetaileBean.getName() ? "" : projectDetaileBean.getName())
                + " (" + (null == projectDetaileBean.getLong_name() ? "" : projectDetaileBean.getLong_name()) + ")");
        if (null != projectDetaileBean.getLast_article()) {
            holder.setText(R.id.news, projectDetaileBean.getLast_article().getTitle());
            holder.setText(R.id.time, AppUtil.getGTime(projectDetaileBean.getLast_article().getCreated_at()));
        }

        if (null != projectDetaileBean.getIco()) {
            if (App.get().getUnit() == 1) {
                if (null == projectDetaileBean.getIco().getPrice_cny()){
                    holder.setText(R.id.price, "¥0.00");
                }else {
                    if (new BigDecimal(projectDetaileBean.getIco().getPrice_cny()).floatValue()<0.01){
                        holder.setText(R.id.price, null == projectDetaileBean.getIco().getPrice_cny() ? "¥0.00" : ("¥" + new BigDecimal(projectDetaileBean.getIco().getPrice_cny()).toPlainString()));
                    }else {
                        holder.setText(R.id.price, null == projectDetaileBean.getIco().getPrice_cny() ? "¥0.00" : ("¥" + new BigDecimal(projectDetaileBean.getIco().getPrice_cny()).setScale(2, BigDecimal.ROUND_DOWN).toPlainString()));
                    }
                }
            } else {
                if (null == projectDetaileBean.getIco().getPrice_usd()){
                    holder.setText(R.id.price, "$0.00");
                }else {
                    if (new BigDecimal(projectDetaileBean.getIco().getPrice_usd()).floatValue()<0.01){
                        holder.setText(R.id.price, null == projectDetaileBean.getIco().getPrice_usd() ? "$0.00" : ("$" + new BigDecimal(projectDetaileBean.getIco().getPrice_usd()).toPlainString()));
                    }else {
                        holder.setText(R.id.price, null == projectDetaileBean.getIco().getPrice_usd() ? "$0.00" : ("$" + new BigDecimal(projectDetaileBean.getIco().getPrice_usd()).setScale(2, BigDecimal.ROUND_DOWN).toPlainString()));
                    }

                }
            }
            if (null != projectDetaileBean.getIco().getPercent_change_24h()) {
                if (!projectDetaileBean.getIco().getPercent_change_24h().contains("-")) {
                    holder.setText(R.id.charge, null == projectDetaileBean.getIco().getPercent_change_24h() ? "+0.00%" : ("+" + projectDetaileBean.getIco().getPercent_change_24h() + "%"));
                    holder.getView(R.id.charge).setBackgroundResource(R.drawable.project_up_bg);
                } else {
                    holder.setText(R.id.charge, null == projectDetaileBean.getIco().getPercent_change_24h() ? "-0.00%" : (projectDetaileBean.getIco().getPercent_change_24h() + "%"));
                    holder.getView(R.id.charge).setBackgroundResource(R.drawable.project_down_bg);
                }
            } else {
                holder.setText(R.id.charge, "+0.00%");
                holder.getView(R.id.charge).setBackgroundResource(R.drawable.project_up_bg);
            }
        } else {
            holder.setText(R.id.price, ((App.get().getUnit() == 1 ? "¥" : "$") + "0.00"));
            holder.setText(R.id.charge, "+0.00%");
            holder.getView(R.id.charge).setBackgroundResource(R.drawable.project_up_bg);
        }

    }
}
