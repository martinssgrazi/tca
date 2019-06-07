package ifpr.com.tca.grazi.servicos

import ifpr.com.tca.grazi.entidades.Pergunta
import retrofit2.Call
import retrofit2.http.*

interface PerguntaService {
    @Headers("Accept: application/json")

//    fun buscaTodas(): Call<List<Tarefa>>

    @FormUrlEncoded
    @GET("amount=1")
    fun pergunta(
        @Field("amount") questao: Int,
        @Field("category") categoria: Int,
        @Field("difficulty") dificuldade: String): Call<Pergunta>


    @GET("api_category.php")
    fun categoria(
        @Field("category[id]") id: String,
        @Field("category[name]") nome: String): Call <Pergunta>


}