package ifpr.com.tca.grazi

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ifpr.com.tca.grazi.entidades.ResultadoLogin
import ifpr.com.tca.grazi.servicos.JogoService
import ifpr.com.tca.grazi.servicos.PerguntaService
import ifpr.com.tca.grazi.ui.QuizzerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var retrofit: Retrofit
    lateinit var perguntaService: PerguntaService
    lateinit var jogoService: JogoService
    lateinit var adapter: QuizzerAdapter
    lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configuraRetrofit()


        btLogin.setOnClickListener {

            jogoService.login(plainLogin.text.toString(), plainSenha.text.toString()).enqueue(object : Callback<ResultadoLogin> {
                override fun onFailure(call: Call<ResultadoLogin>, t: Throwable) {
                    Log.e("grazi", t.message, t)
                }

                override fun onResponse(call: Call<ResultadoLogin>, response: Response<ResultadoLogin>) {
                    val resultadoLogin = response?.body()!!
                    if (resultadoLogin.sucesso) {
                        val intentExplicita = Intent(this@MainActivity, ConfigActivity::class.java)
                        startActivity(intentExplicita)
                    } else {
                        toast("E-mail ou senha inválido")
                    }
                }
            })
        }

        textView3.setOnClickListener {
            val intentExplicita2 = Intent(this, RegistroActivity::class.java)
            startActivity(intentExplicita2)
        }
    }

    fun configuraRetrofit() {

        val retrofitPergunta = Retrofit.Builder()
            .baseUrl("https://opentdb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        perguntaService = retrofitPergunta.create(PerguntaService::class.java)

        val retrofitUsuario = Retrofit.Builder()
            .baseUrl(" https://tads2019-todo-list.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        jogoService = retrofitUsuario.create(JogoService::class.java)
    }


}
