package ifpr.com.tca.grazi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import com.google.gson.GsonBuilder
import ifpr.com.tca.grazi.entidades.Pergunta
import ifpr.com.tca.grazi.entidades.ResultadoPergunta
import ifpr.com.tca.grazi.servicos.PerguntaService
import kotlinx.android.synthetic.main.activity_jogo.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.os.CountDownTimer



class JogoActivity : AppCompatActivity() {

    lateinit var perguntaService: PerguntaService
    lateinit var dificuldade: String
    var categoriaId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jogo)


        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        dificuldade = prefs.getString("dificuldade" , "easy")!!
        categoriaId = prefs.getInt("categoria", 0)

        configuraRetrofit()
        carregarPerguntas()
        tempo()



    }

    private fun carregarPerguntas() {

        perguntaService.buscaPerguntas(dificuldade, categoriaId).enqueue(object : Callback<ResultadoPergunta> {
            override fun onFailure(call: Call<ResultadoPergunta>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<ResultadoPergunta>, response: Response<ResultadoPergunta>) {
                val perguntas = response.body()?.perguntas!![0]
                txtPergunta.text = perguntas.questao
//                perguntas.respostaIncorreta[0]
//                perguntas.respostaIncorreta[1]
//                perguntas.respostaIncorreta[2]
//                perguntas.respostaCorreta
////                perguntas?.respostaCorreta = txtResposta.text.toString()
////                perguntas?.respostaIncorreta = listOf(txtResposta.text.toString())
////                configuraRecyclerView()
////                    var list = listOf<String>(perguntas.respostaIncorreta[0],
////                        perguntas.respostaIncorreta[1],
////                        perguntas.respostaIncorreta[2],
////                        perguntas.respostaCorreta)
////                        .shuffled()
                    btAlternativa1.text = perguntas.respostaCorreta

            }

        })
    }


    fun tempo(){
        object : CountDownTimer(30000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
               tp.setText("seconds remaining: " + millisUntilFinished / 1000)
            }

            override fun onFinish() {
                tp.setText("done!")

            }
        }.start()
    }

    fun configuraRetrofit() {
        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://opentdb.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        perguntaService = retrofit.create(PerguntaService::class.java)
    }

//    fun configuraRecyclerView(categorias: List<Categoria>) {
//        adapter = CategoriaAdapter(categorias, this)
//        listCategorias.adapter = adapter
//        listCategorias.layoutManager = LinearLayoutManager(
//            this, RecyclerView.VERTICAL, false
//        )
//    }





}
