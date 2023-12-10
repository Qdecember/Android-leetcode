package com.example.mydemo.frame

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitTest {

    private fun init() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://www.baidu.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build()


        val request = retrofit.create(ApiInterface::class.java)
        val call:Call<MutableList<String>> = request.listRepos("124")
        call.enqueue(object : Callback<MutableList<String>> {
            override fun onResponse(
                call: Call<MutableList<String>>,
                response: Response<MutableList<String>>
            ) {

            }

            override fun onFailure(call: Call<MutableList<String>>, t: Throwable) {
            }
        })

    }
}