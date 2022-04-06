package bernardo.com.br.blueshoes.domain

class Rate(
    private val stars: Float,
    private val numComments: Int
) {
    fun getNumComments()
        = String.format("(%d)", numComments)
}