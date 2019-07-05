package ifpr.com.tca.grazi.entidades

data class ResultadoPontuar (
    var sucesso: Boolean,
    var ranking: List<Ranking>)