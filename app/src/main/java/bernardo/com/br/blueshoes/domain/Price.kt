package bernardo.com.br.blueshoes.domain

import android.content.Context
import bernardo.com.br.blueshoes.R
import java.util.*

class Price(
    val normal: Float,
    val parcels: Int,
    val hasDiscount: Boolean,
    val withDiscount: Float
) {

    fun getNormalLabel( context: Context)
        = String.format(
            Locale.GERMAN,
            "%s %.2f",
            context.getString( R.string.money_sign ),
            normal
        )

    fun getWithDiscountlLabel( context: Context)
        = String.format(
        Locale.GERMAN,
        "%s %.2f",
        context.getString( R.string.money_sign ),
        withDiscount
    )

}