package bernardo.com.br.blueshoes.view

import android.app.Activity
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.os.SystemClock
import android.text.Editable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextWatcher
import android.text.style.ImageSpan
import android.util.Patterns
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import bernardo.com.br.blueshoes.R
import bernardo.com.br.blueshoes.util.isValidEmail
import bernardo.com.br.blueshoes.util.isValidPassword
import bernardo.com.br.blueshoes.util.validate
import com.blankj.utilcode.util.KeyboardUtils
import com.blankj.utilcode.util.ScreenUtils
import com.google.android.material.snackbar.Snackbar

import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.content_form.*
import kotlinx.android.synthetic.main.content_login.*
import kotlinx.android.synthetic.main.proxy_screen.*
import kotlinx.android.synthetic.main.text_view_privacy_policy_login.*

class LoginActivity :
    FormActivity(),
    TextView.OnEditorActionListener,
    KeyboardUtils.OnSoftInputChangedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        View.inflate(
            this,
            R.layout.content_login,
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

        et_password.setOnEditorActionListener( this )
    }

    override fun onDestroy() {
        KeyboardUtils.unregisterSoftInputChangedListener(this)
        super.onDestroy()
    }

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

    override fun mainAction( view: View? ){
        blockFields( true )
        isMainButtonSending( true )
        showProxy( true )

        backEndFakeDelay()
    }

    private fun backEndFakeDelay(){
        Thread{
            kotlin.run {
                SystemClock.sleep(1000)

                runOnUiThread{
                    blockFields( false )
                    isMainButtonSending( false )
                    showProxy( false )

                    snackBarFeedback(
                        fl_form_container,
                        false,
                        getString(R.string.invalid_login)
                    )
                }
            }
        }.start()
    }

    override fun onEditorAction(
        view: TextView,
        actionId: Int,
        event: KeyEvent?): Boolean {

        mainAction()
        return false
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
        Toast
            .makeText( this, "TODO callForgotPasswordActivity()", Toast.LENGTH_SHORT)
            .show()
    }

    fun callSignUpActivity( view: View){
        Toast
            .makeText( this, "TODO callSignUpActivity()", Toast.LENGTH_SHORT)
            .show()
    }

    fun callPrivacyPolicyFragment( view: View){

    }
}
