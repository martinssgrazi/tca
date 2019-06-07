package ifpr.com.tca.grazi.entidades

import com.google.gson.annotations.SerializedName

class ResultadoCategoria (
    @SerializedName("trivia_categories")
    var categorias: List<Categoria>
)