package ifpr.com.tca.grazi.entidades

import java.util.*

data class Ranking (
    var nome: String,
    var pontuacao: Int,
    var partidasJogadas: Int,
    var ultimaPartida: Date
    )