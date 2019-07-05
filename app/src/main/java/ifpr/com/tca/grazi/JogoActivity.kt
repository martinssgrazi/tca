package ifpr.com.tca.grazi

import android.annotation.SuppressLint
import android.content.Intent
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
import android.text.Html
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import ifpr.com.tca.grazi.entidades.ResultadoPontuar
import ifpr.com.tca.grazi.entidades.bd.AppDatabase
import ifpr.com.tca.grazi.entidades.bd.dao.PerguntaDao
import ifpr.com.tca.grazi.servicos.JogoService
import ifpr.com.tca.grazi.ui.PerguntaListener
import ifpr.com.tca.grazi.ui.ResponderListener
import ifpr.com.tca.grazi.ui.RespostaAdapter


class JogoActivity : AppCompatActivity(), ResponderListener, PerguntaListener {

    lateinit var adapter: RespostaAdapter
    lateinit var perguntaService: PerguntaService
    lateinit var dificuldade: String
    lateinit var login: String
    lateinit var senha: String
    var categoriaId = 0
    lateinit var pergunta: Pergunta
    lateinit var jogoService: JogoService
    lateinit var db: AppDatabase
    lateinit var perguntaDao: PerguntaDao
    lateinit var pontos: ResultadoPontuar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jogo)


        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        dificuldade = prefs.getString("dificuldade", "easy")!!
        categoriaId = prefs.getInt("categoria", 0)
        login = prefs.getString("usuario", "")
        senha = prefs.getString("senha", "")
        configuraRetrofit()
        carregarPerguntas()

//        prefs.edit()
//            .putInt("pontuacao", pontos.ranking)
//            .commit()

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "pergunta.db"
        )
            .allowMainThreadQueries()
            .build()
        perguntaDao= db.perguntaDao()



        btLater.setOnClickListener {
            perguntaInserida(pergunta)


//            pontuar(resposta = String.toString())

        }

        btRanking.setOnClickListener {
            val intentExplicita = Intent(this@JogoActivity, RankingActivity::class.java)
            startActivity(intentExplicita)
        }

        btRecuperar.setOnClickListener{

        }

    }

    @SuppressLint("NewApi")
    private fun carregarPerguntas() {
//        val country = resources.configuration.locales[0].country.toLowerCase() // "br" "ar"

        perguntaService.buscaPerguntas(dificuldade, categoriaId).enqueue(object : Callback<ResultadoPergunta> {
            override fun onFailure(call: Call<ResultadoPergunta>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<ResultadoPergunta>, response: Response<ResultadoPergunta>) {
                pergunta = response.body()?.perguntas!![0]
                txtPergunta.text = Html.fromHtml(pergunta.questao)
                configuraRecyclerView(pergunta)
                tempo()
            }

        })
    }


    fun tempo() {
        val duracao: Long = when (pergunta.dificuldade) {
            "hard" -> 15000
            "medium" -> 30000
            "easy" -> 45000
            else -> 45000
        }

        object : CountDownTimer(duracao, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                tp.text = getString(R.string.time_remaining, millisUntilFinished / 1000)
            }

            override fun onFinish() {
                val intentExplicita = Intent(this@JogoActivity, JogoActivity::class.java)
                startActivity(intentExplicita)


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

        val retrofitUsuario = Retrofit.Builder()
            .baseUrl(" https://tads2019-todo-list.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        jogoService = retrofitUsuario.create(JogoService::class.java)
    }

    fun configuraRecyclerView(pergunta: Pergunta) {
        adapter = RespostaAdapter(pergunta, this)
        ListResposta.adapter = adapter
        ListResposta.layoutManager = LinearLayoutManager(
            this, RecyclerView.VERTICAL, false
        )
    }


    override fun perguntaInserida(pergunta: Pergunta) {
            perguntaDao.inserir(pergunta)
        }




    override fun pontuar(resposta: String) {

        // logica para montar uma variavel com os pontos
        var pontos = 0
        if (resposta == pergunta.respostaCorreta) {
            val intentExplicita = Intent(this@JogoActivity, JogoActivity::class.java)
            startActivity(intentExplicita)

            if (pergunta.dificuldade == "hard") {
                pontos = 10

            } else if (pergunta.dificuldade == "medium") {
                pontos = 8

            } else if (pergunta.dificuldade == "easy") {
                pontos = 5

            }
        } else if (pergunta.respostasIncorretas.contains(resposta)) {
            val intentExplicita = Intent(this@JogoActivity, JogoActivity::class.java)
            startActivity(intentExplicita)
            if (pergunta.dificuldade == "hard") {
                pontos = -10

            } else if (pergunta.dificuldade == "medium") {
                pontos = -8

            } else if (pergunta.dificuldade == "easy") {
                pontos = -5

            }
        }


//        this.pontuacao(anInt)
        jogoService.pontuarJogador(login, senha, pontos).enqueue(object : Callback<ResultadoPontuar> {
            override fun onFailure(call: Call<ResultadoPontuar>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<ResultadoPontuar>, response: Response<ResultadoPontuar>) {
                val pontuacao = response.body()?.ranking

            }
        })
    }


}
