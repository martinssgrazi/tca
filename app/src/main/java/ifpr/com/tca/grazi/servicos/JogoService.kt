package ifpr.com.tca.grazi.servicos

import ifpr.com.tca.grazi.entidades.ResultadoLogin
import ifpr.com.tca.grazi.entidades.ResultadoRegistro
import ifpr.com.tca.grazi.entidades.Usuario
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface JogoService : PerguntaService {

    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("usuario/registrar")
    fun cadastrar(
        @Field("nome") nome: String,
        @Field("email") email: String,
        @Field("senha") senha: String,
        @Field("confirmarsenha") confirmarsenha: String
    ): Call<ResultadoRegistro>

    @Headers("Accept: application/json")


    @FormUrlEncoded
    @POST("usuario/login")
    fun login(
        @Field("email") email: String,
        @Field("senha") senha: String): Call <ResultadoLogin>




}