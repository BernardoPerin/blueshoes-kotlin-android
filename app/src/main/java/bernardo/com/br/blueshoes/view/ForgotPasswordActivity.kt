package bernardo.com.br.blueshoes.view

import android.os.Bundle
import bernardo.com.br.blueshoes.R
import bernardo.com.br.blueshoes.util.isValidEmail
import bernardo.com.br.blueshoes.util.validate
import kotlinx.android.synthetic.main.content_forgot_password.*
import kotlinx.android.synthetic.main.info_block.*

class ForgotPasswordActivity :
    FormActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        et_email.validate(
            {
                it.isValidEmail()
            },
            getString(R.string.invalid_email)
        )

        et_email.setOnEditorActionListener( this )

        tv_info_block.text = getString( R.string.forgot_password_info )
    }

    override fun getLayoutResourceID()
            = R.layout.content_forgot_password

    override fun backEndFakeDelay(){
        backEndFakeDelay(
            false,
            getString(R.string.invalid_login_email)
        )
    }

    override fun onSoftInputChanged(height: Int) {

    }

    override fun blockFields( status: Boolean ){
        et_email.isEnabled = !status
        bt_recover_password.isEnabled = !status
    }

    override fun isMainButtonSending( status: Boolean ){
        bt_recover_password.text = if( status )
            getString(R.string.recover_password_going)
        else
            getString(R.string.recover_password)
    }
}
