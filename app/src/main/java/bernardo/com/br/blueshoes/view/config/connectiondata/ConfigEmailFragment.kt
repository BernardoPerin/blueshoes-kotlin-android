package bernardo.com.br.blueshoes.view.config.connectiondata


import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import bernardo.com.br.blueshoes.R
import bernardo.com.br.blueshoes.view.FormFragment
import kotlinx.android.synthetic.main.fragment_config_email.*


class ConfigEmailFragment :
    FormFragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        bt_update_email_login.setOnClickListener {
            callPasswordDialog()
        }
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
