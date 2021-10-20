package com.daotrung.myappmusic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daotrung.myappmusic.Activity.PlayNhacActivity;
import com.daotrung.myappmusic.Model.BaiHat;
import com.daotrung.myappmusic.R;
import com.daotrung.myappmusic.Service.APIService;
import com.daotrung.myappmusic.Service.Dataservice;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaiHatHotAdapter extends RecyclerView.Adapter<BaiHatHotAdapter.ViewHolder>{

    Context context;
    ArrayList<BaiHat> baiHatArrayList;

    public BaiHatHotAdapter(Context context, ArrayList<BaiHat> baiHatArrayList) {
        this.context = context;
        this.baiHatArrayList = baiHatArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_bai_hat_hot,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baiHat = baiHatArrayList.get(position);
        holder.txtCaSi.setText(baiHat.getCasi());
        holder.txtTen.setText(baiHat.getTenbaihat());
        Picasso.with(context).load(baiHat.getHinhbaihat()).into(holder.imgHinh);

    }

    @Override
    public int getItemCount() {
        return baiHatArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtTen,txtCaSi;
        ImageView imgHinh, imgLuotThich;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTen = itemView.findViewById(R.id.textviewbaihathot);
            txtCaSi = itemView.findViewById(R.id.textviewtencasibaihathot);
            imgHinh = itemView.findViewById(R.id.imageviewbaihathot);
            imgLuotThich = itemView.findViewById(R.id.imageviewluotthich);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc",baiHatArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
            imgLuotThich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                     imgLuotThich.setImageResource(R.drawable.iconloved);
                     Dataservice dataservice = APIService.getService();
                     Call<String> callback = dataservice.UpdateLuotThich("1",baiHatArrayList.get(getPosition()).getIdbaihat());
                     callback.enqueue(new Callback<String>() {
                         @Override
                         public void onResponse(Call<String> call, Response<String> response) {
                             String ketqua = response.body();
                             if(ketqua.equals("Ok")){
                                 Toast.makeText(context, "Da Thich "+baiHatArrayList.get(getPosition()).getTenbaihat(), Toast.LENGTH_SHORT).show();
                             }else{
                                 Toast.makeText(context, "Loi", Toast.LENGTH_SHORT).show();
                             }
                         }

                         @Override
                         public void onFailure(Call<String> call, Throwable t) {

                         }
                     });
                     imgLuotThich.setEnabled(false);
                }
            });


        }
    }
}

