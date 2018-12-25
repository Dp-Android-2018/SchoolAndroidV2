package dp.schoolandroid.di.modules;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dp.schoolandroid.R;
import dp.schoolandroid.service.repository.remotes.ApiInterfaces;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Bahaa Gabal on 24,November,2018
 */

@Module
public class NetworkModule {

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS).build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Context context, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(context.getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    ApiInterfaces getRetrofitApiInterfaces(Retrofit retrofit) {
        return retrofit.create(ApiInterfaces.class);
    }
}
