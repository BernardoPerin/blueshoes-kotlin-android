package bernardo.com.br.blueshoes.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import bernardo.com.br.blueshoes.R
import bernardo.com.br.blueshoes.util.isValidEmail
import bernardo.com.br.blueshoes.util.isValidPassword
import bernardo.com.br.blueshoes.util.validate
import com.blankj.utilcode.util.KeyboardUtils
import com.blankj.utilcode.util.ScreenUtils
import kotlinx.android.synthetic.main.content_form.*
import kotlinx.android.synthetic.main.content_sign_up.*
import kotlinx.android.synthetic.main.text_view_privacy_policy_login.*

class SignUpActivity :
    FormActivity(),
    KeyboardUtils.OnSoftInputChangedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        View.inflate(
            this,
            R.layout.content_sign_up,
            fl_form
        )

        KeyboardUtils.registerSoftInputChangedListener(this,this)

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

        et_confirm_password.validate(
            {
                (et_password.text.isNotEmpty()
                    && it.equals(et_password.text.toString()))
                || et_password.text.isEmpty()
            },
            getString(R.string.invalid_confirmed_password)
        )

        et_confirm_password.setOnEditorActionListener( this )
    }

    override fun onDestroy() {
        KeyboardUtils.unregisterSoftInputChangedListener(this)
        super.onDestroy()
    }

    override fun blockFields( status: Boolean ){
        et_email.isEnabled = !status
        et_password.isEnabled = !status
        bt_sign_up.isEnabled = !status
    }

    override fun isMainButtonSending( status: Boolean ){
        bt_sign_up.text = if( status )
            getString(R.string.sign_up_going)
        else
            getString(R.string.sign_up)
    }

    override fun mainAction( view: View? ){
        blockFields( true )
        isMainButtonSending( true )
        showProxy( true )

        backEndFakeDelay(
            false,
            getString(R.string.invalid_login)
        )
    }

    override fun onSoftInputChanged(height: Int) {
        if( ScreenUtils.isPortrait() ){
            changePrivacyPolicyConstraints(
                KeyboardUtils.isSoftInputVisible( this )
            )
        }
    }

    private fun changePrivacyPolicyConstraints(
            isKeyBoardOpened: Boolean
        ){

        val privacyId = tv_privacy_policy.id
        val parent = tv_privacy_policy.parent as ConstraintLayout
        val constraintSet = ConstraintSet()

        constraintSet.constrainWidth(
            privacyId,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        constraintSet.constrainHeight(
            privacyId,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )

        constraintSet.centerHorizontally(
            privacyId,
            ConstraintLayout.LayoutParams.PARENT_ID
        )

        if( isKeyBoardOpened ){
            constraintSet.connect(
                privacyId,
                ConstraintLayout.LayoutParams.TOP,
                tv_sign_up.id,
                ConstraintLayout.LayoutParams.BOTTOM,
                (12 * ScreenUtils.getScreenDensity().toInt())
            )
        }
        else{
            constraintSet.connect(
                privacyId,
                ConstraintLayout.LayoutParams.BOTTOM,
                ConstraintLayout.LayoutParams.PARENT_ID,
                ConstraintLayout.LayoutParams.BOTTOM
            )
        }

        constraintSet.applyTo( parent )
    }

    fun callForgotPasswordActivity( view: View){
        val intent = Intent(
            this,
            ForgotPasswordActivity::class.java
        )

        startActivity( intent )
    }

    fun callSignUpActivity( view: View){
        Toast
            .makeText( this, "TODO callSignUpActivity()", Toast.LENGTH_SHORT)
            .show()
    }

    fun callPrivacyPolicyFragment( view: View){
        val intent = Intent(
            this,
            MainActivity::class.java
        )

        intent.putExtra( MainActivity.FRAGMENT_ID, R.id.item_privacy_policy )
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP

        startActivity( intent )
    }
}