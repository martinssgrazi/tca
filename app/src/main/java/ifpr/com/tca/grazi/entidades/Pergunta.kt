package ifpr.com.tca.grazi.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

@TypeConverters(Conversor::class)
@Entity(tableName = "question")  data class Pergunta (
    @SerializedName("category")
    var categoria: String,
    @SerializedName("type")
    var tipo: String,
    @SerializedName("difficulty")
    var dificuldade: String,
    @SerializedName("url")
    var url: String,
    @SerializedName("question")
    var questao: String,
    @SerializedName("correct_answer")
    var respostaCorreta: String,
    @SerializedName("incorrect_answers")
    var respostasIncorretas: List<String>

) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

class Conversor {

    @TypeConverter
    fun fromListToString(list: List<String>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromStringToList(string: String): List<String> {
        val list = gson.fromJson(string, Array<String>::class.java) as Array<String>
        return list.asList()
    }

    companion object {
        val gson = Gson()
    }

}

