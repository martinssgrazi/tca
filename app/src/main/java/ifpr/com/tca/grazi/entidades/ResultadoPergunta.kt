package ifpr.com.tca.grazi.entidades

import com.google.gson.annotations.SerializedName

class ResultadoPergunta (
    @SerializedName("results")
    var perguntas: List<Pergunta>
)