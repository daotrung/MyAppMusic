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

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachBaiHatAdapter extends RecyclerView.Adapter<DanhSachBaiHatAdapter.ViewHolder>{

    Context context;
    ArrayList<BaiHat> mangBaiHat;

    public DanhSachBaiHatAdapter(Context context, ArrayList<BaiHat> mangBaiHat) {
        this.context = context;
        this.mangBaiHat = mangBaiHat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_danh_sach_bai_hat,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baiHat = mangBaiHat.get(position);
        holder.txtCaSi.setText(baiHat.getCasi());
        holder.txtTenBaiHat.setText(baiHat.getTenbaihat());
        holder.txtIndex.setText(position + 1 + "");

    }

    @Override
    public int getItemCount() {
        return mangBaiHat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtIndex, txtTenBaiHat, txtCaSi;
        ImageView imgLuotThich;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCaSi = itemView.findViewById(R.id.textviewtencasi);
            txtIndex = itemView.findViewById(R.id.textviewdanhsachindex);
            txtTenBaiHat = itemView.findViewById(R.id.textviewtenbaihat);
            imgLuotThich = itemView.findViewById(R.id.imageviewluotthichdanhsachbaihat);

            imgLuotThich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imgLuotThich.setImageResource(R.drawable.iconloved);
                    Dataservice dataservice = APIService.getService();
                    Call<String> callback = dataservice.UpdateLuotThich("1",mangBaiHat.get(getPosition()).getIdbaihat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua = response.body();
                            if(ketqua.equals("Ok")){
                                Toast.makeText(context, "Da Thich "+mangBaiHat.get(getPosition()).getTenbaihat(), Toast.LENGTH_SHORT).show();
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc",mangBaiHat.get(getPosition()));
                    context.startActivity(intent);
                }
            });

        }
    }
}

