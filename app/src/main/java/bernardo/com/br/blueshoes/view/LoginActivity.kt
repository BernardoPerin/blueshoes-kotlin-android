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
    AppCompatActivity(),
    TextView.OnEditorActionListener,
    KeyboardUtils.OnSoftInputChangedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        window.setBackgroundDrawableResource( R.drawable.bg_activity )

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

    private fun showProxy( status: Boolean ){
        fl_proxy_container.visibility = if( status )
                View.VISIBLE
            else
                View.GONE
    }

    private fun snackBarFeedback(
            viewContainer: View,
            status: Boolean,
            message: String
        ){

        val snackbar = Snackbar
            .make(
                viewContainer,
                message,
                Snackbar.LENGTH_LONG)

        val iconResource = if ( status )
            R.drawable.ic_check_black_18dp
        else
            R.drawable.ic_close_black_18dp

        val img = ResourcesCompat
            .getDrawable(
                resources,
                iconResource,
                null
            )

        img!!.setBounds(
            0,
            0,
            img.intrinsicWidth,
            img.intrinsicHeight
        )

        val iconColor = if ( status )
            ContextCompat.getColor(
                this,
                R.color.colorNavButton
                )
        else
            Color.RED

        img.setColorFilter( iconColor, PorterDuff.Mode.SRC_ATOP )

        val spannedTxt = SpannableString("     $message")
        spannedTxt.setSpan(
            ImageSpan( img, ImageSpan.ALIGN_BOTTOM),
            0,
            1,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        val snackbarView = snackbar.view
        val textView = snackbarView.findViewById<TextView>( com.google.android.material.R.id.snackbar_text )

        textView.setText( spannedTxt, TextView.BufferType.SPANNABLE)

        snackbar.show()
    }

    private fun blockFields( status: Boolean ){
        et_email.isEnabled = !status
        et_password.isEnabled = !status
        bt_login.isEnabled = !status
    }

    private fun isSignInGoing( status: Boolean ){
        bt_login.text = if( status )
            getString(R.string.sign_in_going)
        else
            getString(R.string.sign_in)
    }

    fun login( view: View? = null ){
        blockFields( true )
        isSignInGoing( true )
        showProxy( true )

        backEndFakeDelay()
    }

    private fun backEndFakeDelay(){
        Thread{
            kotlin.run {
                SystemClock.sleep(1000)

                runOnUiThread{
                    blockFields( false )
                    isSignInGoing( false )
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
            login()
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
