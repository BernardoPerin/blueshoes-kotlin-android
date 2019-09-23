package bernardo.com.br.blueshoes.view

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import bernardo.com.br.blueshoes.R
import com.google.android.material.snackbar.Snackbar

import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.content_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        window.setBackgroundDrawableResource( R.drawable.bg_activity )

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
    }

    private fun showProxy( status: Boolean ){
        fl_proxy_container.visibility = if( status )
                View.VISIBLE
            else
                View.GONE
    }

    private fun snackBarFeedback(
            view: View,
            status: Boolean,
            message: String
        ){

        val snackbar = Snackbar
            .make(
                view,
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

        val iconColor = if ( status )
            ContextCompat.getColor(
                this,
                R.color.colorNavButton
                )
        else
            Color.RED

        img!!.setColorFilter( iconColor, PorterDuff.Mode.SRC_ATOP )
    }
}
