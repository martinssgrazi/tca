package ifpr.com.tca.grazi.servicos

import ifpr.com.tca.grazi.entidades.Pergunta
import ifpr.com.tca.grazi.entidades.ResultadoCategoria
import ifpr.com.tca.grazi.entidades.ResultadoPergunta
import retrofit2.Call
import retrofit2.http.*

interface PerguntaService {
    @Headers("Accept: application/json")

    @GET("api.php")
    fun buscaPerguntas(
        @Query("difficulty") dificuldade: String,
        @Query("category") categoria: Int? = null,
        @Query("amount") quantidade: Int = 1): Call<ResultadoPergunta>

    @Headers("Accept: application/json")
    @GET("api_category.php")
    fun buscaCategorias(): Call <ResultadoCategoria>


}