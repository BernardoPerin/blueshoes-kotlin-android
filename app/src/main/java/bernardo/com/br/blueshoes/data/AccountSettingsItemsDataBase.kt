package bernardo.com.br.blueshoes.data

import android.content.Context
import bernardo.com.br.blueshoes.R
import bernardo.com.br.blueshoes.domain.AccountSettingItem
import bernardo.com.br.blueshoes.view.config.profile.ProfileActivity
import bernardo.com.br.blueshoes.view.config.connectiondata.ConnectDataActivity
import bernardo.com.br.blueshoes.view.config.creditcards.CreditCardsActivity
import bernardo.com.br.blueshoes.view.config.deliveryaddress.DeliveryAddressesActivity

class AccountSettingsItemsDataBase {

    companion object{

        fun getItems( context: Context )
            = listOf(
                AccountSettingItem(
                    context.getString(R.string.setting_item_profile),
                    context.getString(R.string.setting_item_profile_desc),
                    ProfileActivity::class.java
                ),
                AccountSettingItem(
                    context.getString(R.string.setting_item_login),
                    context.getString(R.string.setting_item_login_desc),
                    ConnectDataActivity::class.java
                ),
                AccountSettingItem(
                    context.getString(R.string.setting_item_address),
                    context.getString(R.string.setting_item_address_desc),
                    DeliveryAddressesActivity::class.java
                ),
                AccountSettingItem(
                    context.getString(R.string.setting_item_credit_cards),
                    context.getString(R.string.setting_item_credit_cards_desc),
                    CreditCardsActivity::class.java
                )
        )
    }
}