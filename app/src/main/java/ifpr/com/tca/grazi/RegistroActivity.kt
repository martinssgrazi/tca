package ifpr.com.tca.grazi

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ifpr.com.tca.grazi.entidades.ResultadoRegistro
import ifpr.com.tca.grazi.servicos.JogoService
import ifpr.com.tca.grazi.servicos.PerguntaService
import ifpr.com.tca.grazi.ui.QuizzerAdapter
import kotlinx.android.synthetic.main.activity_registro.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RegistroActivity : AppCompatActivity() {

        lateinit var retrofit: Retrofit
        lateinit var perguntaService: PerguntaService
        lateinit var jogoService: JogoService
        lateinit var adapter: QuizzerAdapter
        lateinit var prefs: SharedPreferences

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_registro)

            configuraRetrofit()


            btRegistrar.setOnClickListener {

                jogoService.cadastrar(plainNome.text.toString(), plainRemail.text.toString(), plainRsenha.text.toString())
                    .enqueue(object :
                        Callback<ResultadoRegistro> {
                        override fun onFailure(call: Call<ResultadoRegistro>, t: Throwable) {
                            Log.e("grazi", t.message, t)
                        }

                        override fun onResponse(call: Call<ResultadoRegistro>, response: Response<ResultadoRegistro>) {

                            val resultadoCadastro = response?.body()!!
                            if (resultadoCadastro.sucesso) {
                                val intentExplicita = Intent(this@RegistroActivity, MainActivity::class.java)
                                startActivity(intentExplicita)
                            } else {
                                toast("E-mail ou senha inválido")
                            }
                        }

                    })
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
