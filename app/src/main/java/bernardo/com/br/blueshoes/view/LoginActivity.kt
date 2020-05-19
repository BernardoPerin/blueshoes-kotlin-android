package bernardo.com.br.blueshoes.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import bernardo.com.br.blueshoes.R
import bernardo.com.br.blueshoes.util.isValidEmail
import bernardo.com.br.blueshoes.util.isValidPassword
import bernardo.com.br.blueshoes.util.validate
import com.blankj.utilcode.util.ScreenUtils
import kotlinx.android.synthetic.main.content_login.*

class LoginActivity :
    FormEmailAndPasswordActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        et_email.validate(
            {
                it.isValidEmail()
            },
            getString(R.string.invalid_email)
        )

        et_password.validate(
            {
                it.isValidPassword()
            },
            getString(R.string.invalid_password)
        )

        et_password.setOnEditorActionListener( this )
    }

    override fun getLayoutResourceID()
        = R.layout.content_login

    override fun blockFields( status: Boolean ){
        et_email.isEnabled = !status
        et_password.isEnabled = !status
        bt_login.isEnabled = !status
    }

    override fun isMainButtonSending( status: Boolean ){
        bt_login.text = if( status )
            getString(R.string.sign_in_going)
        else
            getString(R.string.sign_in)
    }

    override fun backEndFakeDelay(){
        backEndFakeDelay(
            false,
            getString(R.string.invalid_login)
        )
    }

    override fun isAbleToCallChangePrivacyPolicyConstraints()
        = ScreenUtils.isPortrait()

    override fun isConstraintToSiblingView( isKeyBoardOpened: Boolean )
        = isKeyBoardOpened

    override fun setConstraintsRelativeToSiblingView(
        constraintSet: ConstraintSet,
        privacyId: Int ){

        constraintSet.connect(
            privacyId,
            ConstraintLayout.LayoutParams.TOP,
            tv_sign_up.id,
            ConstraintLayout.LayoutParams.BOTTOM,
            (12 * ScreenUtils.getScreenDensity().toInt())
        )
    }

    fun callForgotPasswordActivity( view: View){
        val intent = Intent(
            this,
            ForgotPasswordActivity::class.java
        )

        startActivity( intent )
    }

    fun callSignUpActivity( view: View){
        val intent = Intent(
            this,
            SignUpActivity::class.java
        )

        startActivity( intent )
    }
}
