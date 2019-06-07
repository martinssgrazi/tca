package ifpr.com.tca.grazi

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ifpr.com.tca.grazi.servicos.JogoService
import ifpr.com.tca.grazi.servicos.PerguntaService
import ifpr.com.tca.grazi.ui.QuizAdapter
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {
    lateinit var retrofit: Retrofit
    lateinit var service: PerguntaService
    lateinit var adapter: QuizAdapter
    lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configuraRetrofit()


        btLogin.setOnClickListener(){
            val intentExplicita = Intent( this, OutraActivity::class.java)
            startActivity(intentExplicita)

        }


    }

    fun configuraRetrofit() {

        val retrofitPergunta = Retrofit.Builder()
            .baseUrl("https://opentdb.com/api.php?")
            .build()
        service = retrofitPergunta.create(PerguntaService::class.java)

        val retrofitUsuario = Retrofit.Builder()
            .baseUrl(" https://tads2019-todo-list.herokuapp.com/")
            .build()
        service = retrofitUsuario.create(JogoService::class.java)
    }


}
