package ifpr.com.tca.grazi.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ifpr.com.tca.grazi.R
import ifpr.com.tca.grazi.entidades.Ranking
import kotlinx.android.synthetic.main.item_ranking.view.*

class RankingAdapter(private var rankings: List<Ranking>) : RecyclerView.Adapter<RankingAdapter.RankingViewHolder>(){




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
       RankingViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_ranking, parent, false)
        )

    override fun getItemCount() = rankings.size

    override fun onBindViewHolder(holder: RankingViewHolder, position: Int) =
        holder.preencherView(rankings[position])

    inner class RankingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun preencherView(ranking: Ranking) {
              itemView.txtNome.text = ranking.nome
              itemView.txtPontuacao.text = ranking.pontuacao.toString()
              itemView.txtPrimeira.text = ranking.partidasJogadas.toString()
              itemView.txtUltima.text = ranking.ultimaPartida.toString()

//
//            itemView..setText(noticia.titulo)
//            itemView.lbDescricao.setText(noticia.descricao)
//            itemView.lbConteudo.setText(noticia.conteudo)
//
//            noticia.titulo = itemView.lbTitulo.text.toString()
//            noticia.descricao = itemView.lbDescricao.text.toString()





        }
    }
}
