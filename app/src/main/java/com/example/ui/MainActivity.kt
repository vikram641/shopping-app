package com.example.ui


import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var myAdapters: MyAdapters

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        recyclerView = findViewById(R.id.recyclerview)

        val rectrofitBuilder = RetrofitHelper.getInstance()
        val getproductData = rectrofitBuilder.productData()



        getproductData.enqueue(object : Callback<MyData?> {
            override fun onResponse(p0: Call<MyData?>, responce: Response<MyData?>) {
                var responseBody = responce.body()
                val productList = responseBody?.products!!


                myAdapters = MyAdapters(this@MainActivity, mList = productList)
                recyclerView.adapter = myAdapters
                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                





            }

            override fun onFailure(p0: Call<MyData?>, p1: Throwable) {
                Log.d("Main", "onFailure: callback")
            }
        })


    }
}

object RetrofitHelper {

    val baseUrl = "https://dummyjson.com/"

    fun getInstance(): Myinterface {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            // we need to add converter factory to
            // convert JSON object to Java object
            .build()
            .create(Myinterface::class.java)
    }
}