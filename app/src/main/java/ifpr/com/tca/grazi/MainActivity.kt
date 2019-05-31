package ifpr.com.tca.grazi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ifpr.com.tca.grazi.servicos.PerguntasService
import ifpr.com.tca.grazi.ui.QuizAdapter
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {
    lateinit var retrofit: Retrofit
    lateinit var service: PerguntasService
    lateinit var adapter: QuizAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configuraRetrofit()
    }

    fun configuraRetrofit() {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://opentdb.com")
            .build()
        service = retrofit.create(PerguntasService::class.java)
    }


}
