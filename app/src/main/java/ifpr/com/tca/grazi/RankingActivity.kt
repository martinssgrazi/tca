package ifpr.com.tca.grazi

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import ifpr.com.tca.grazi.entidades.Ranking
import ifpr.com.tca.grazi.entidades.ResultadoPontuar
import ifpr.com.tca.grazi.servicos.JogoService
import ifpr.com.tca.grazi.ui.RankingAdapter

import kotlinx.android.synthetic.main.activity_ranking.*
import kotlinx.android.synthetic.main.item_ranking.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RankingActivity : AppCompatActivity() {
    lateinit var retrofit: Retrofit
    lateinit var service: JogoService
    lateinit var adapter: RankingAdapter
//    lateinit var pontos: Int
    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)

        configuraRetrofit()
        carregaDados()
//        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
//        pontos = prefs.getInt("pontuacao", pontos)

//        btBusca.setOnClickListener{
//            carregaDados()
//        }


    }

    fun configuraRetrofit() {
        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://tads2019-todo-list.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        service = retrofit.create(JogoService::class.java)
    }

//
//    @SuppressLint("NewApi")
//    fun carregaDados() {
//        val country = resources.configuration.locales[0].country.toLowerCase() // "br" "ar"
//        service.ranking().enqueue(object : Callback<ResultadoPontuar>)
//
//
//    }

    fun carregaDados(){
        service.ranking().enqueue(object : Callback<ResultadoPontuar> {
            override fun onFailure(call: Call<ResultadoPontuar>, t: Throwable) {
                Log.e("ERRO", "OI")
            }

            override fun onResponse(call: Call<ResultadoPontuar>, response: Response<ResultadoPontuar>) {
                val ranking = response.body()?.ranking

                if(ranking!= null) {
                    configuraRecyclerView(ranking)
                }

            }


        })
    }




    fun configuraRecyclerView(ranking: List<Ranking>) {
        adapter = RankingAdapter(ranking)
        listRanking.adapter = adapter

        listRanking.layoutManager = LinearLayoutManager(
            this, RecyclerView.VERTICAL, false
        )
    }

}
