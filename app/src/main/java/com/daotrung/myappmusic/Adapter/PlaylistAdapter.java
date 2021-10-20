package com.daotrung.myappmusic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.daotrung.myappmusic.Model.PlayList;
import com.daotrung.myappmusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PlaylistAdapter extends ArrayAdapter<PlayList> {
    ArrayList<PlayList> playListArrayList;

    public PlaylistAdapter(@NonNull Context context, int resource, @NonNull List<PlayList> objects) {
        super(context, resource, objects);
    }

    class ViewHolder{
        TextView txtTenPlayList;
        ImageView imgBackGround, imgPlayList;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.dong_playlist, null);
            viewHolder = new ViewHolder();
            viewHolder.txtTenPlayList = convertView.findViewById(R.id.textviewtenplaylist);
            viewHolder.imgPlayList = convertView.findViewById(R.id.imageviewplaylist);
            viewHolder.imgBackGround = convertView.findViewById(R.id.imagebackgroundplaylist);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final PlayList playList = getItem(position);
        Picasso.with(getContext()).load(playList.getHinhPlayList()).into(viewHolder.imgBackGround);
        Picasso.with(getContext()).load(playList.getIcon()).into(viewHolder.imgPlayList);
        viewHolder.txtTenPlayList.setText(playList.getTen());





        return convertView;
    }
}
