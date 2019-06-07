package ifpr.com.tca.grazi.servicos

import ifpr.com.tca.grazi.entidades.Pergunta
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface PerguntaService {
    @Headers("Accept: application/json")

//    fun buscaTodas(): Call<List<Tarefa>>

    @FormUrlEncoded
    @POST("amount=1")
    fun pergunta(
        @Field("category") email: String,
        @Field("type") tipo: String,
        @Field("difficulty") dificuldade: String,
        @Field("question") questao: String,
        @Field("correct_answer") respostaCorreta: String,
        @Field("incorrect_answers") respostaIncorreta: String): Call<Pergunta>
    @POST("api_category.php")
    fun categoria(
        @Field("category[id]") id: String,
        @Field("category[name]") nome: String): Call <Pergunta>


    @POST("&difficulty")
    fun dificuldade(

    )

}