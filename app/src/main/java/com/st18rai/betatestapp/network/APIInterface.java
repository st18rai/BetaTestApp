package com.st18rai.betatestapp.network;

import com.st18rai.betatestapp.model.ItemData;
import com.st18rai.betatestapp.model.LoginModel;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {

    @POST("api/Authentication/RequestMoblieCabinetApiToken")
    Observable<ResponseBody> login(
            @Body LoginModel loginModel
    );

    @GET("clientmobile/GetAnalyticSignals/{login}")
    Observable<List<ItemData>> getSignalsList(@Path("login") String login,
                                              @Query("tradingsystem") String tradingSystems,
                                              @Query("pairs") String pairs,
                                              @Query("from") String from,
                                              @Query("to") String to,
                                              @Header("passkey") String passKey);

}
