package bernardo.com.br.blueshoes.domain

import android.content.Context
import bernardo.com.br.blueshoes.R

class DeliveryAddress(
    val street: String,
    val number: Int,
    val complement: String,
    val zipCode: String,
    val neighborhood: String,
    val city: String,
    val state: Int
) {
    fun getStateName( context: Context )
        = context
            .resources
            .getStringArray(R.array.states)[ state ]
}