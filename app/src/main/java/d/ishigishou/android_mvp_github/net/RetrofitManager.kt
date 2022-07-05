package d.ishigishou.android_mvp_github.net

import d.ishigishou.android_mvp_github.api.ApiService
import d.ishigishou.android_mvp_github.api.UrlConstant
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import okio.Timeout
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

object RetrofitManager {
    val service: ApiService by lazy(LazyThreadSafetyMode.SYNCHRONIZED){
        getRetrofit().create(ApiService::class.java)
    }


    private fun getRetrofit(): Retrofit{

        return Retrofit.Builder()
            .baseUrl(UrlConstant.BASE_URL)
            .client(getOkHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


//    private fun addQueryParameterInterceptor(): Interceptor{
//        return Interceptor { chain ->
//            val originalRequest = chain.request()
//            val request: Request
//
//        }
//
//    }

    /**
     * set header
     */
    private fun addHeaderInterceptor(): Interceptor{
        return Interceptor { chain ->
            val originalRequest = chain.request()
            val requestBuilder = originalRequest.newBuilder()
                // Provide your custom header here
                .method(originalRequest.method(), originalRequest.body())
            val request = requestBuilder.build()
            chain.proceed(request)
        }
    }

    private fun addCacheInterceptor(): Interceptor{
        return Interceptor { chain ->
            val originalRequest = chain.request()
            val requestBuilder = originalRequest.newBuilder()
                .cacheControl(CacheControl.FORCE_CACHE)
                .build()
            val response = chain.proceed(requestBuilder)
            response
        }
    }


    private fun getOkHttpClient(): OkHttpClient {

        //log攔截器
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

//        val cacheFile = File(MyApplication.context.cacheDir, "cache")
//        val cache = Cache(cacheFile, 1024 * 1024 * 50) //50Mb 缓存的大小
        return OkHttpClient.Builder()
            .addInterceptor(addHeaderInterceptor())
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(60L,TimeUnit.SECONDS)
            .readTimeout(60L,TimeUnit.SECONDS)
            .writeTimeout(60L,TimeUnit.SECONDS)
            .build()
    }
}