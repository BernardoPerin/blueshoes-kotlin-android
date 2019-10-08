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

abstract class FormActivity :
    AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        window.setBackgroundDrawableResource( R.drawable.bg_activity )
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

    abstract fun blockFields( status: Boolean )

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
    }
}
