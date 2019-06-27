package ifpr.com.tca.grazi.servicos

import ifpr.com.tca.grazi.entidades.Pergunta
import ifpr.com.tca.grazi.entidades.ResultadoCategoria
import retrofit2.Call
import retrofit2.http.*

interface PerguntaService {
    @Headers("Accept: application/json")
    @FormUrlEncoded
    @GET("api.php")
    fun buscaPerguntas(
        @Field("difficulty") dificuldade: String,
        @Field("category") categoria: Int? = null,
        @Field("amount") quantidade: Int = 1): Call<Pergunta>

    @Headers("Accept: application/json")
    @GET("api_category.php")
    fun buscaCategorias(): Call <ResultadoCategoria>


}