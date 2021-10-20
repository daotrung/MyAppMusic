package com.daotrung.myappmusic.Service;

public class APIService {
    private static String base_url = "https://appandroid3108.000webhostapp.com/Server/";

    public static Dataservice getService() {
        return APIRetrofitClient.getClient(base_url).create(Dataservice.class);
    }
}
