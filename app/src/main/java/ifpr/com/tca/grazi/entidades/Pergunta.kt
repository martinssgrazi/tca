package ifpr.com.tca.grazi.entidades

import com.google.gson.annotations.SerializedName


class Pergunta (
    @SerializedName("category")
    var categoria: String,
    @SerializedName("type")
    var tipo: String,
    @SerializedName("difficulty")
    var dificuldade: String,
    @SerializedName("url")
    var url: String,
    @SerializedName("question")
    var questao: String,
    @SerializedName("correct_answer")
    var respostaCorreta: String,
    @SerializedName("incorrect_answers")
    var respostasIncorretas: List<String>

)

