package bernardo.com.br.blueshoes.view

import android.os.Bundle
import bernardo.com.br.blueshoes.R
import bernardo.com.br.blueshoes.domain.User
import bernardo.com.br.blueshoes.util.isValidEmail
import bernardo.com.br.blueshoes.util.validate
import kotlinx.android.synthetic.main.content_config_profile.*
import kotlinx.android.synthetic.main.content_forgot_password.*
import kotlinx.android.synthetic.main.info_block.*

class ConfigProfileActivity :
    FormActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        et_name.validate(
            {
                it.length > 1
            },
            getString(R.string.invalid_name)
        )

        et_name.setOnEditorActionListener( this )

        val user = intent.getParcelableExtra<User>( User.Key )
        et_name.setText( user.name )
    }

    override fun getLayoutResourceID()
            = R.layout.content_config_profile

    override fun backEndFakeDelay(){
        backEndFakeDelay(
            false,
            getString(R.string.invalid_login_email)
        )
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
