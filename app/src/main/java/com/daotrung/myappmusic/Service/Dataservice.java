package com.daotrung.myappmusic.Service;

import com.daotrung.myappmusic.Model.Album;
import com.daotrung.myappmusic.Model.BaiHat;
import com.daotrung.myappmusic.Model.ChuDe;
import com.daotrung.myappmusic.Model.PlayList;
import com.daotrung.myappmusic.Model.QuangCao;
import com.daotrung.myappmusic.Model.TheLoai;
import com.daotrung.myappmusic.Model.TheLoaiTrongNgay;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Dataservice {
    @GET("songbanner.php")
    Call<List<QuangCao>> GetDataBanner();

    @GET("playlistforcurrentday.php")
    Call<List<PlayList>> GetPlayListCurrentDay();

    @GET("chudevatheloaitrongngay.php")
    Call<TheLoaiTrongNgay> getCategoryMusic();

    @GET("album.php")
    Call<List<Album>> getCategoryAlbum();

    @GET("baihatduocthich.php")
    Call<List<BaiHat>> getBaiHatHot() ;

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> getDanhSachBaiHatTheoQuangCao(@Field("idquangcao") String idquangcao);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> getDanhSachBaiHatTheoPlayList(@Field("idplaylist") String idPlayList);

    @GET("danhsachcacplaylist.php")
    Call<List<PlayList>> GetDanhsachcacPlaylist() ;

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhsachBaiHatTheoTheLoai(@Field("idtheloai") String idTheLoai);

    @GET("tatcachude.php")
    Call<List<ChuDe>> GetAllChuDe() ;

    @FormUrlEncoded
    @POST("theloaitheochude.php")
    Call<List<TheLoai>> GetTheLoaitheochude(@Field("idchude") String idchude);

    @GET("tatcaalbum.php")
    Call<List<Album>> GetAllAlbum();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhSachBaiHatTheoAlbum(@Field("idalbum") String idalbum);

    @FormUrlEncoded
    @POST("updateluotthich.php")
    Call<String> UpdateLuotThich(@Field("luotthich") String luotthich ,@Field("idbaihat") String idbaihat );

    @FormUrlEncoded
    @POST("searchbaihat.php")
    Call<List<BaiHat>> getSearchBaiHat(@Field("tukhoa") String tuKhoa);



}
