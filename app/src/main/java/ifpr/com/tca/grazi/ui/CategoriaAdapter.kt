package ifpr.com.tca.grazi.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ifpr.com.tca.grazi.R
import ifpr.com.tca.grazi.entidades.Categoria
import kotlinx.android.synthetic.main.item_categoria.view.*

class CategoriaAdapter(
    private var categorias: List<Categoria>,
    private var listener: CategoriaListener
) : RecyclerView.Adapter<CategoriaAdapter.QuizViewHolder>() {


    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        holder.preencherView(categorias[position])
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        QuizViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_categoria, parent, false)
        )

    override fun getItemCount() = categorias.size


    inner class QuizViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun preencherView(categoria: Categoria) {
            itemView.txtCategoria.text = categoria.nome

            itemView.setOnClickListener {
                with(this@CategoriaAdapter) {
                    listener.categoriaSelecionada(categoria)




                }

            }
            itemView.txtCategoria.setOnClickListener {
                itemView.txtCategoria.setBackgroundColor(
                ContextCompat.getColor(
                    itemView.context,
                    R.color.btLogin
                ))
            }

        }
    }
}

