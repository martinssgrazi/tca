package ifpr.com.tca.grazi.ui

import ifpr.com.tca.grazi.entidades.Categoria

interface CategoriaListener {
    fun categoriaSelecionada(categoria: Categoria)
}