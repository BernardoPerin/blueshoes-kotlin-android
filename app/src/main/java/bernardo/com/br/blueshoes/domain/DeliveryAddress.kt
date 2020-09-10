package bernardo.com.br.blueshoes.domain

import android.content.Context
import android.os.Parcelable
import bernardo.com.br.blueshoes.R
import kotlinx.android.parcel.Parcelize

@Parcelize
class DeliveryAddress(
        val street: String,
        val number: Int,
        val complement: String,
        val zipCode: String,
        val neighborhood: String,
        val city: String,
        val state: Int
    ) : Parcelable {

    companion object{
        const val KEY = "delivery-address-key"
    }

    fun getStateName( context: Context )
        = context
            .resources
            .getStringArray(R.array.states)[ state ]
}