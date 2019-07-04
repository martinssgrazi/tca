package ifpr.com.tca.grazi.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import ifpr.com.tca.grazi.R
import ifpr.com.tca.grazi.entidades.Pergunta
import kotlinx.android.synthetic.main.item_resposta.view.*

class RespostaAdapter(
    private val pergunta: Pergunta,
    private var listener: ResponderListener
) : RecyclerView.Adapter<RespostaAdapter.RespostaViewHolder>() {
    var respostas = pergunta.respostasIncorretas.toMutableList()

    init {
        respostas.add(pergunta.respostaCorreta)
        respostas.shuffle()
    }

    override fun onBindViewHolder(holder: RespostaViewHolder, position: kotlin.Int) {
        holder.preencherView(respostas[position])
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: kotlin.Int) =
        RespostaViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_resposta, parent, false)
        )

    override fun getItemCount() = respostas.size


    inner class RespostaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun preencherView(resposta: String) {
            itemView.ListResposta.text = resposta
            itemView.setOnClickListener {
                this@RespostaAdapter.listener.pontuar(resposta)
                if (resposta == pergunta.respostaCorreta)
                    Toast.makeText(itemView.context, "$resposta! Acertou!", Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(itemView.context, "$resposta! Errou!", Toast.LENGTH_SHORT).show()
            }




        }

    }

}