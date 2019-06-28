package ifpr.com.tca.grazi.entidades.bd

import androidx.room.Database
import androidx.room.RoomDatabase
import ifpr.com.tca.grazi.entidades.Pergunta
import ifpr.com.tca.grazi.entidades.bd.dao.PerguntaDao


@Database(entities = arrayOf(Pergunta::class), version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun perguntaDao(): PerguntaDao
}
