package ifpr.com.tca.grazi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ifpr.com.tca.grazi.entidades.Categoria
import ifpr.com.tca.grazi.ui.QuizzerAdapter
import kotlinx.android.synthetic.main.activity_config.*

class ConfigActivity : AppCompatActivity() {
    lateinit var adapter: QuizzerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)
    }

    fun configuraRecyclerView(categorias: List<Categoria>) {
        adapter = QuizzerAdapter(categorias.toMutableList())
        listCategorias.adapter = adapter

        listCategorias.layoutManager = LinearLayoutManager(
            this, RecyclerView.VERTICAL, false
        )
    }
}
