package com.eiappcompany.datamodule.util

import androidx.annotation.NonNull
import com.eiappcompany.base.util.helper.SharedHelper
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject


class AuthInterceptor @Inject
constructor(
    /**
     * A default constructor that gets dependencies
     */
    internal var sharedHelper: SharedHelper


) : Interceptor {

    /**
     * Adds specific queries to requests
     *
     * @param chain represents [Chain]
     * @return response with headers and others
     * @throws IOException throws
     */
    @NonNull
    @Throws(IOException::class)
    override fun intercept(@NonNull chain: Interceptor.Chain): Response {
//        val token = sharedHelper.getStringData(TOKEN, "")
        val token = sharedHelper.getStringData("deneme", "")
        val newRequest = chain.request().newBuilder()
            //.addHeader("Authorization", "Bearer $token")
            .addHeader("Authorization", "Bearer")
            .method(chain.request().method(), chain.request().body())
            .build()
        return chain.proceed(newRequest)
    }
}
