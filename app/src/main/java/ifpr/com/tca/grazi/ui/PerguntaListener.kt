package ifpr.com.tca.grazi.ui

import ifpr.com.tca.grazi.entidades.Pergunta

interface PerguntaListener {

    fun perguntaInserida(pergunta: Pergunta)
}