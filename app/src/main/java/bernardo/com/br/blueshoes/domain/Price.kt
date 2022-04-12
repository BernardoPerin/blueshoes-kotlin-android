package bernardo.com.br.blueshoes.domain

import android.content.Context
import bernardo.com.br.blueshoes.R
import java.util.*

class Price(
    private val normal: Float,
    private val parcels: Int,
    val hasDiscount: Boolean,
    private val withDiscount: Float
) {

    fun getNormalLabel( context: Context )
        = String.format(
            Locale.GERMAN,
            "%s %.2f",
            context.getString( R.string.money_sign ),
            normal
        )

    fun getWithDiscountlLabel( context: Context )
        = String.format(
        Locale.GERMAN,
        "%s %.2f",
        context.getString( R.string.money_sign ),
        withDiscount
    )

    fun getPercentDiscountlLabel() : String{
        val percent = (( normal - withDiscount ) / normal) * 100

        return String.format("-%d%%", percent.toInt())
    }

    fun getParcelsLabel( context: Context ) : String{
        val priceParcel = if( hasDiscount )
                withDiscount / parcels
            else
                normal / parcels

        return String.format(
            Locale.GERMAN,
            "%s %dx %s %s %.2f",
            context.getString( R.string.in_until ),
            parcels,
            context.getString( R.string.of ),
            context.getString( R.string.money_sign ),
            priceParcel
        )
    }
}