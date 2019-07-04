package ifpr.com.tca.grazi.entidades

import com.google.gson.annotations.SerializedName
import kotlin.Int

class Categoria (

    var id: Int,
    @SerializedName("name")
    var nome : String
)