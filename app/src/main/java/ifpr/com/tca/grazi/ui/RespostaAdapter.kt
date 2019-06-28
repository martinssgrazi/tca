package ifpr.com.tca.grazi.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ifpr.com.tca.grazi.R
import ifpr.com.tca.grazi.entidades.Resposta
import kotlinx.android.synthetic.main.item_categoria.view.*

class RespostaAdapter (
    private var respostas: MutableList<Resposta>,
    private var respostaIncorreta: List<String>,
    private var respostaCorreta: String
    ) : RecyclerView.Adapter<RespostaAdapter.RespostaViewHolder>() {


    override fun onBindViewHolder(holder: RespostaViewHolder, position: Int) {
        holder.preencherView(respostas[position])
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RespostaViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_categoria, parent, false)
        )

    override fun getItemCount() = respostas.size


    inner class RespostaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun preencherView(respostas: Resposta) {
//            itemView.txtResposta1.text = respostaCorreta.nome
//            itemView.txtResposta2.text = respostaIncorreta.




            }

        }

    }
