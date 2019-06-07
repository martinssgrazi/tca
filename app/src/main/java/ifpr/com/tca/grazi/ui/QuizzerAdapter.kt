package ifpr.com.tca.grazi.ui

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ifpr.com.tca.grazi.R
import ifpr.com.tca.grazi.entidades.Categoria
import java.text.SimpleDateFormat

class QuizzerAdapter (
    private var categorias: MutableList<Categoria>): RecyclerView.Adapter<QuizzerAdapter.NoticiaViewHolder>(){


        override fun onBindViewHolder(holder: QuizzerAdapter.NoticiaViewHolder, position: Int) {
            holder.preencherView(categorias[position])
        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
                NoticiaViewHolder(
                    LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.activity_config, parent, false)
                )

        override fun getItemCount() = categorias.size




        inner class NoticiaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun preencherView(categoria: Categoria) {

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

