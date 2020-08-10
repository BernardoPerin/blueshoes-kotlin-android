package bernardo.com.br.blueshoes.view.config.creditcards


import android.os.Bundle

import bernardo.com.br.blueshoes.R
import bernardo.com.br.blueshoes.util.isValidEmail
import bernardo.com.br.blueshoes.util.validate
import bernardo.com.br.blueshoes.view.FormFragment
import kotlinx.android.synthetic.main.fragment_config_email.*


class ConfigCreditCardsListFragment :
    FormFragment() {

    companion object{
        const val TAB_TITLE = R.string.config_credit_cards_tab_list
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
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
        et_current_email.isEnabled = !status
        et_new_email.isEnabled = !status
        et_new_email_confirm.isEnabled = !status
        bt_update_email_login.isEnabled = !status
    }

    override fun isMainButtonSending(status: Boolean) {
        bt_update_email_login.text = if( status )
            getString(R.string.update_email_login_going)
        else
            getString(R.string.update_email_login)
    }

}
