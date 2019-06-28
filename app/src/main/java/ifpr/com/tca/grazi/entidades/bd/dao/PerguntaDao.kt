package ifpr.com.tca.grazi.entidades.bd.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import ifpr.com.tca.grazi.entidades.Pergunta


@Dao
interface PerguntaDao {

    @Insert
    fun inserir(pergunta: Pergunta): Long

    @Delete
    fun apagar(pergunta: Pergunta)



}