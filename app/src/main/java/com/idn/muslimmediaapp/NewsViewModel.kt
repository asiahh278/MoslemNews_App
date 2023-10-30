package com.idn.muslimmediaapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.idn.muslimmediaapp.model.network.ApiClient
import com.idn.muslimmediaapp.model.network.NewsRespose
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel: ViewModel() {
    private var _commonMuslimNews = MutableLiveData<NewsRespose>()
    val commonMuslimNews get() = _commonMuslimNews as LiveData<NewsRespose>

    private var _aboutAlQuranNews = MutableLiveData<NewsRespose>()
    val aboutAlquranNews get() = _aboutAlQuranNews as LiveData<NewsRespose>

    private var _alJazeeraNews = MutableLiveData<NewsRespose>()
    val alJazeeraNews get() = _alJazeeraNews as LiveData<NewsRespose>

    private var _warningForMuslimNews = MutableLiveData<NewsRespose>()
    val warningForMuslim get() = _warningForMuslimNews as LiveData<NewsRespose>

    private var _searchNews = MutableLiveData<NewsRespose>()
    val searchNews get() = _searchNews as LiveData<NewsRespose>

    fun commonMuslimNews(){
        ApiClient.provideApiService().getCommonMuslimNews()
            .enqueue(object : Callback<NewsRespose> {
                override fun onResponse(
                    call: Call<NewsRespose>,
                    response: Response<NewsRespose>
                ) {
                    if (response.isSuccessful){
                        Log.i(
                            "ViewModel",
                            "onResponse: Call success with HTTP status code ${response.body()}"
                        )
                    } else Log.e(
                        "ViewModel",
                        "onResponse: Call error with HTTP status code " + response.code()

                    )
                }

                override fun onFailure(call: retrofit2.Call<NewsRespose>, t: Throwable) {
                    Log.e(
                        "ViewModel",
                        "onFailure" + t.localizedMessage
                    )
                }
            })
    }

    fun aboutAlQuranNews() {
        ApiClient.provideApiService().getAlQuranNews()
            .enqueue(object : Callback<NewsRespose> {
                override fun onResponse(
                    call: Call<NewsRespose>,
                    response: Response<NewsRespose>
                ) {
                    if (response.isSuccessful) {
                        Log.i(
                            "ViewModel",
                            "onResponse: Call success with HTTP status code ${response.body()}"
                        )
                    } else Log.e(
                        "ViewModel",
                        "onResponse: Call error with HTTP status code " + response.code()
                    )
                }

                override fun onFailure(call: Call<NewsRespose>, t: Throwable) {
                    Log.e(
                        "ViewModel",
                        "onFailure: " + t.localizedMessage
                    )
                }
            })
    }

    fun alJazeeraNews() {
        ApiClient.provideApiService().getAlJazeeraNews()
            .enqueue(object : Callback<NewsRespose> {
                override fun onResponse(
                    call: Call<NewsRespose>,
                    response: Response<NewsRespose>
                ) {
                    if (response.isSuccessful) {
                        Log.i(
                            "ViewModel",
                            "onResponse: ${response.body()}"
                        )
                        _alJazeeraNews.postValue(response.body())
                    } else Log.e(
                        "ViewModel",
                        "onResponse: Call error with HTTP status code" + response.body()
                    )
                }

                override fun onFailure(call: Call<NewsRespose>, t: Throwable) {
                    Log.e(
                        "ViewModel",
                        "onFailure" + t.localizedMessage
                    )
                }
            })
    }

    fun warningForMuslimNews() {
        ApiClient.provideApiService().getWarningForMuslimNews()
            .enqueue(object : Callback<NewsRespose> {
                override fun onResponse(
                    call: Call<NewsRespose>,
                    response: Response<NewsRespose>
                ) {
                    if (response.isSuccessful) {
                        Log.i(
                            "ViewModel",
                            "onResponse: ${response.body()}"
                        )
                    } else Log.e(
                        "ViewModel",
                        "onResponse: Call error with HTTP status code " + response.code()
                    )
                }

                override fun onFailure(call: Call<NewsRespose>, t: Throwable) {
                    Log.e(
                        "ViewModel",
                        "onFailure: " + t.localizedMessage
                    )
                }
            })
    }

fun searchNews(q: String) {
    ApiClient.provideApiService().getSearchNews(q)
        .enqueue(object : Callback<NewsRespose> {
            override fun onResponse(
                call: Call<NewsRespose>,
                response: Response<NewsRespose>
            ) {
                if (response.isSuccessful) {
                    Log.i(
                        "ViewModel",
                        "onResponse ${response.body()}"
                    )
                } else Log.e(
                    "ViewModel",
                    "onResponse: Call error with HTTP status code" + response.code()
                )
            }

            override fun onFailure(call: Call<NewsRespose>, t: Throwable) {
                Log.e(
                    "ViewModel",
                    "onFailure: " + t.localizedMessage
                )
            }
        })
}