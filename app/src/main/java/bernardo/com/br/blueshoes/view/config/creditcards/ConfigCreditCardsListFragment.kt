package bernardo.com.br.blueshoes.view.config.creditcards


import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import bernardo.com.br.blueshoes.R
import bernardo.com.br.blueshoes.data.AccountSettingsItemsDataBase
import bernardo.com.br.blueshoes.data.CreditCardsDataBase
import bernardo.com.br.blueshoes.util.isValidEmail
import bernardo.com.br.blueshoes.util.validate
import bernardo.com.br.blueshoes.view.AccountSettingsItemsAdapter
import bernardo.com.br.blueshoes.view.FormFragment
import kotlinx.android.synthetic.main.content_account_settings.*
import kotlinx.android.synthetic.main.fragment_config_credit_cards_list.*
import kotlinx.android.synthetic.main.fragment_config_email.*


class ConfigCreditCardsListFragment :
    FormFragment() {

    companion object{
        const val TAB_TITLE = R.string.config_credit_cards_tab_list
    }

    var callbackBlockFields : (Boolean)->Unit = {}
    var callbackMainButtonSending : (Boolean)->Unit = {}
    var callbackMainRemoveItem : (Boolean)->Unit = {}

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        updateFlFormToFullFreeScreen()
        initItems()
    }

    private fun initItems(){
        rv_credit_cards.setHasFixedSize( true )

        val layoutManager = LinearLayoutManager(activity)
        rv_credit_cards.layoutManager = layoutManager

        rv_credit_cards.adapter = ConfigCreditCardsListItemsAdapter(
            this,
            CreditCardsDataBase.getItems()
        )
    }

    override fun getLayoutResourceID()
        = R.layout.fragment_config_credit_cards_list

    override fun backEndFakeDelay() {
        backEndFakeDelay(
            true,
            getString(R.string.credit_card_removed)
        )
    }

    override fun blockFields(status: Boolean) {
        callbackBlockFields( status )
    }

    override fun isMainButtonSending(status: Boolean) {
        callbackMainButtonSending( status )
        callbackMainRemoveItem( status )
        /*bt_update_email_login.text = if( status )
            getString(R.string.update_email_login_going)
        else
            getString(R.string.update_email_login)
        */
    }

    fun callbackToUpdateItem(
        blockFields : (Boolean)->Unit,
        mainButtonSending : (Boolean)->Unit,
        removeItem : (Boolean)->Unit
    ){
        callbackBlockFields = blockFields
        callbackMainButtonSending = mainButtonSending
        callbackMainRemoveItem = removeItem
    }
}
