package com.example.pja2.adaptador;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.pja2.R;


public class galleryPhotoAdapter extends BaseAdapter {
    private Context mContext;
    public int[] imgArray = {
            R.drawable.camera,
            R.drawable.camera,
            R.drawable.camera,
            R.drawable.camera,
            R.drawable.camera,
            R.drawable.camera,
            R.drawable.img_placeholder,
            R.drawable.img_placeholder,
            R.drawable.img_placeholder,
            R.drawable.img_placeholder,
            R.drawable.img_placeholder,
            R.drawable.img_placeholder,
            R.drawable.img_placeholder
    };

    public galleryPhotoAdapter(Context mContext){
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return imgArray.length;
    }

    @Override
    public Object getItem(int i) {
        return imgArray[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(imgArray[i]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(340, 350));
        return imageView;
    }
}
