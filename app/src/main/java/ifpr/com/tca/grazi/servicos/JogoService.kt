package ifpr.com.tca.grazi.servicos

import ifpr.com.tca.grazi.entidades.ResultadoLogin
import ifpr.com.tca.grazi.entidades.ResultadoPontuar
import ifpr.com.tca.grazi.entidades.ResultadoRegistro
import ifpr.com.tca.grazi.entidades.Usuario
import retrofit2.Call
import retrofit2.http.*

interface JogoService {

    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("usuario/registrar")
    fun cadastrar(
        @Field("nome") nome: String,
        @Field("email") email: String,
        @Field("senha") senha: String): Call<ResultadoRegistro>

    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("usuario/login")
    fun login(
        @Field("email") email: String,
        @Field("senha") senha: String): Call<ResultadoLogin>


    @Headers("Accept: application/json")
    @GET("ranking")
    fun ranking(): Call<ResultadoPontuar>

    @Headers("Accept: application/json")
    @FormUrlEncoded
    @PUT("usuario/pontuacao")
    fun pontuarJogador(
        @Field("email") email: String,
        @Field("senha") senha: String,
        @Field("pontos") pontos: Int): Call <ResultadoPontuar>

}