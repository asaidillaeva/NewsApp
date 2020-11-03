package com.example.newsapp.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager
import kotlin.jvm.Throws

object NetworkRetrofit {
    private var retrofit: Retrofit? = null
    private const val BASE_URL = "https://newsapi.org/v2/"

    @JvmStatic
    val retrofitInstance: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(unsafeOkHttpClient.build())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }
            return retrofit
        }

    private val unsafeOkHttpClient:

            OkHttpClient.Builder
        get() = try {

            // Create a trust manager that does not validate certificate chains
            val trustAllCerts = arrayOf<TrustManager>(
                    object : X509TrustManager {
                        @Throws(CertificateException::class)
                        override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
                        }

                        @Throws(CertificateException::class)
                        override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
                        }

                        override fun getAcceptedIssuers(): Array<X509Certificate> {
                            return arrayOf()
                        }
                    }
            )

            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())

            val sslSocketFactory = sslContext.socketFactory
            val builder = OkHttpClient.Builder().cache(null)
            builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            builder.hostnameVerifier { hostname, session -> true }
            builder
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
}