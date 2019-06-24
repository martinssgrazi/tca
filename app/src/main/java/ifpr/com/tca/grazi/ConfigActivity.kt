package ifpr.com.tca.grazi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import ifpr.com.tca.grazi.servicos.PerguntaService
import ifpr.com.tca.grazi.ui.QuizzerAdapter
import kotlinx.android.synthetic.main.activity_config.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ConfigActivity : AppCompatActivity() {
    lateinit var retrofit: Retrofit
    lateinit var adapter: QuizzerAdapter
    lateinit var perguntaService: PerguntaService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)

        configuraRetrofit()
        configuraRecyclerView(categorias = listCategorias)
//        carregarTarefa()
//        atualizaLista()
    }

    fun configuraRetrofit() {
        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://opentdb.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        perguntaService = retrofit.create(PerguntaService::class.java)
    }


    fun configuraRecyclerView(categorias: RecyclerView) {
        adapter = QuizzerAdapter(categorias)
        listCategorias.adapter = adapter
        listCategorias.layoutManager = LinearLayoutManager(
            this, RecyclerView.VERTICAL, false
        )
    }
}
