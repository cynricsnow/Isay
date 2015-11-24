package com.clearday.ywl.isay.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.clearday.ywl.isay.R;
import com.clearday.ywl.isay.models.UserItem;

import java.util.List;

public class Tab3Adapter extends BaseAdapter {

    private Context context;
    private List<UserItem> userItemList;

    public Tab3Adapter(Context context, List<UserItem> datas) {
        this.context = context;
        userItemList = datas;
    }

    @Override
    public int getCount() {
		return userItemList.size();
	}

    @Override
    public UserItem getItem(int position) {
		return userItemList.get(position);
	}

    @Override
    public long getItemId(int position) {
		return position;
	}

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_user, null);
            holder.v_divider = convertView.findViewById(R.id.v_divider);
            holder.navContent = convertView.findViewById(R.id.nav_content);
            holder.navIcon = (ImageView) convertView.findViewById(R.id.nav_icon);
            holder.navTitle = (TextView) convertView.findViewById(R.id.nav_title);
            holder.navSubTitle = (TextView) convertView.findViewById(R.id.nav_sub_title);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // set data
        UserItem item = getItem(position);
        holder.v_divider.setVisibility(item.isShowTopDivider() ? View.VISIBLE : View.GONE);
        holder.navIcon.setImageResource(item.getNavIcon());
        holder.navTitle.setText(item.getNavTitle());
        holder.navSubTitle.setText(item.getNavSubTitle());

        holder.navContent.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position){
                    case 0:
                        //TODO 我的赞同
                        break;
                    case 1:
                        //TODO 我的收藏
                        break;
                    case 2:
                        //TODO 我的地点
                        break;
                    case 3:
                        //TODO 我的轨迹
                        break;
                    case 4:
                        //TODO 编辑资料
                        break;
                    case 5:
                        //TODO 服务平台
                        break;
                    case 6:
                        //TODO 草稿箱
                        break;
                    default:
                        break;
                }
            }
        });

        // 获取屏幕宽度大小、设置bitmap的宽度
        convertView.setDrawingCacheEnabled(true);
        WindowManager windowManager = ((Activity) context).getWindowManager();
        Display display = windowManager.getDefaultDisplay();

        // 如果没有调用这个方法，得到的bitmap为null
        convertView.measure(View.MeasureSpec.makeMeasureSpec(display.getWidth(), View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(256, View.MeasureSpec.UNSPECIFIED));
        // 设置布局的尺寸和位置
        convertView.layout(0, 0, convertView.getMeasuredWidth(), convertView.getMeasuredHeight());
        // 生成bitmap
        Bitmap bitmap = Bitmap.createBitmap(convertView.getWidth(), convertView.getHeight(),
                Bitmap.Config.RGB_565);
        // 利用bitmap生成画布
        Canvas canvas = new Canvas(bitmap);
        // 把view中的内容绘制在画布上
        convertView.draw(canvas);

        return convertView;
    }

    public static class ViewHolder{
        public View v_divider;
        public View navContent;
        public ImageView navIcon;
        public TextView navTitle;
        public TextView navSubTitle;
    }

}
