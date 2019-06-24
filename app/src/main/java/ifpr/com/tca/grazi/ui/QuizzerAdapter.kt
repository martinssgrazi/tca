package ifpr.com.tca.grazi.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.core.view.size
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_config.view.*
import ifpr.com.tca.grazi.R

class QuizzerAdapter(
    private var categorias: RecyclerView
): RecyclerView.Adapter<QuizzerAdapter.QuizViewHolder>(){


        override fun onBindViewHolder(holder: QuizzerAdapter.QuizViewHolder, position: Int) {
            holder.preencherView(categorias[position])
        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
                QuizViewHolder(
                    LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.activity_config, parent, false)
                )

        override fun getItemCount() = categorias.size




        inner class QuizViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun preencherView(categoria: View) {


                  itemView.nome.setText(categoria)
//                itemView.lbTitulo.setText(noticia.titulo)
//                itemView.lbDescricao.setText(noticia.descricao)
//                itemView.lbConteudo.setText(noticia.conteudo)
//                val time = SimpleDateFormat("dd/MM/yyyy hh:mm")
//                itemView.lbDate.text = time.format(noticia.publicado)
//                Picasso.get().load(noticia.urlToImage).into(itemView.imageView)
//                itemView.lbAutor.setText(noticia.autor)
//                noticia.titulo = itemView.lbTitulo.text.toString()
//                noticia.descricao = itemView.lbDescricao.text.toString()
//
//                itemView.setOnClickListener{
//                    val uri = Uri.parse(noticia.url)
//                    val intent = Intent(Intent.ACTION_VIEW, uri)
//                    itemView.context.startActivity(intent)
//                }
//
//                val position = noticias.indexOf(noticia)
//
//
//
//                itemView.lbTitulo.text = noticia.titulo


            }
        }
    }

