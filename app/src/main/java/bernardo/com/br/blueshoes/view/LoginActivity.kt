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
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import bernardo.com.br.blueshoes.R
import com.blankj.utilcode.util.KeyboardUtils
import com.blankj.utilcode.util.ScreenUtils
import com.google.android.material.snackbar.Snackbar

import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.content_login.*
import kotlinx.android.synthetic.main.proxy_screen.*
import kotlinx.android.synthetic.main.text_view_privacy_policy_login.*

class LoginActivity :
    FormActivity(),
    TextView.OnEditorActionListener,
    KeyboardUtils.OnSoftInputChangedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        KeyboardUtils.registerSoftInputChangedListener(this,this)

        et_email.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(content: Editable?) {
                val message = getString(R.string.invalid_email)

                if (content != null) {
                    et_email.error = if( content.isNotEmpty()
                        && Patterns.EMAIL_ADDRESS.matcher( content ).matches())
                        null
                    else
                        message
                }
            }

            override fun beforeTextChanged(content: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(content: CharSequence?, start: Int, before: Int, count: Int) {}

        })

        et_password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(content: Editable?) {
                val message = getString(R.string.invalid_password)

                if (content != null) {
                    et_password.error = if( content.length > 5)
                        null
                    else
                        message
                }
            }

            override fun beforeTextChanged(content: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(content: CharSequence?, start: Int, before: Int, count: Int) {}

        })

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

        if( actionId == EditorInfo.IME_ACTION_DONE ){
            closeVirtualKeyBoard( view )
            mainAction()
            return true
        }

        return false
    }

    private fun closeVirtualKeyBoard( view: View ){
        val imm = getSystemService(
            Activity.INPUT_METHOD_SERVICE
        ) as InputMethodManager

        imm.hideSoftInputFromWindow( view.windowToken, 0 )
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
}
