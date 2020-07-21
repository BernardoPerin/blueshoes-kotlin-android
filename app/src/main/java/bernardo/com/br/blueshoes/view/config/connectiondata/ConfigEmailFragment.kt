package bernardo.com.br.blueshoes.view.config.connectiondata


import android.os.Bundle

import bernardo.com.br.blueshoes.R
import bernardo.com.br.blueshoes.util.isValidEmail
import bernardo.com.br.blueshoes.util.validate
import bernardo.com.br.blueshoes.view.FormFragment
import kotlinx.android.synthetic.main.fragment_config_email.*


class ConfigEmailFragment :
    FormFragment() {

    companion object{
        const val TAB_TITLE = R.string.config_connection_data_tab_email
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        bt_update_email_login.setOnClickListener {
            callPasswordDialog()
        }

        et_current_email.validate(
            {it.isValidEmail()},
            getString( R.string.invalid_email )
        )

        et_new_email.validate(
            {it.isValidEmail()},
            getString( R.string.invalid_email )
        )

        et_new_email_confirm.validate(
            {
                (et_new_email.text.isNotEmpty()
                    && it.equals(et_new_email.text.toString()))
                    || et_new_email.text.isEmpty()
            },
            getString(R.string.invalid_confirmed_email)
        )

        et_new_email_confirm.setOnEditorActionListener( this )
    }

    override fun getLayoutResourceID()
        = R.layout.fragment_config_email

    override fun backEndFakeDelay() {
        backEndFakeDelay(
            false,
            getString(R.string.invalid_email)
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
            getString(R.string.update_email_login)
        else
            getString(R.string.update_email_login_going)
    }

}
