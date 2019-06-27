package ifpr.com.tca.grazi

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import ifpr.com.tca.grazi.entidades.Categoria
import ifpr.com.tca.grazi.entidades.ResultadoCategoria
import ifpr.com.tca.grazi.servicos.PerguntaService
import ifpr.com.tca.grazi.ui.CategoriaAdapter
import ifpr.com.tca.grazi.ui.CategoriaListener
import kotlinx.android.synthetic.main.activity_config.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.prefs.Preferences

class ConfigActivity : AppCompatActivity(), CategoriaListener {

    lateinit var adapter: CategoriaAdapter
    lateinit var perguntaService: PerguntaService
    var categoria: Categoria? = null

    @SuppressLint("ApplySharedPref")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)

        configuraRetrofit()

        carregarCategorias()
//        atualizaLista()
        btSalvar.setOnClickListener {
//            Log.d("categoria", categoria?.id.toString())
//            Log.d("categoria", categoria?.nome)

            val prefs = PreferenceManager.getDefaultSharedPreferences(this)

//            if (rgDificuldade.checkedRadioButtonId == R.id.rbFacil)

            prefs.edit()
                .putString("dificuldade", "easy")
                .putInt("categoria", 55)
                .commit()



            val intentExplicita = Intent(this@ConfigActivity, JogoActivity::class.java)
            startActivity(intentExplicita)
        }




    }

    private fun carregarCategorias() {
        perguntaService.buscaCategorias().enqueue(object : Callback<ResultadoCategoria>{
            override fun onFailure(call: Call<ResultadoCategoria>, t: Throwable) {

            }

            override fun onResponse(call: Call<ResultadoCategoria>, response: Response<ResultadoCategoria>) {
                val resultado = response.body()!!
                configuraRecyclerView(resultado.categorias)
            }
        })
    }

    fun configuraRetrofit() {
        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://opentdb.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        perguntaService = retrofit.create(PerguntaService::class.java)
    }


    fun configuraRecyclerView(categorias: List<Categoria>) {
        adapter = CategoriaAdapter(categorias, this )
        listCategorias.adapter = adapter
        listCategorias.layoutManager = LinearLayoutManager(
            this, RecyclerView.VERTICAL, false
        )
    }

    override fun categoriaSelecionada(categoria: Categoria) {
        this.categoria = categoria
    }
}
