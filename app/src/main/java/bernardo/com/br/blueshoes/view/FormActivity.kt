package bernardo.com.br.blueshoes.view

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ImageSpan
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import bernardo.com.br.blueshoes.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.proxy_screen.*

abstract class FormActivity :
    AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate( savedInstanceState )
        setContentView( R.layout.activity_form )
        setSupportActionBar( toolbar )

        supportActionBar?.setDisplayHomeAsUpEnabled( true )
        supportActionBar?.setDisplayShowHomeEnabled( true )

        window.setBackgroundDrawableResource( R.drawable.bg_activity )
    }

    override fun onOptionsItemSelected( item: MenuItem ): Boolean {
        if ( item.itemId == android.R.id.home ) {
            finish()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    protected fun showProxy( status: Boolean ){
        fl_proxy_container.visibility =
            if( status )
                View.VISIBLE
            else
                View.GONE
    }

    protected fun snackBarFeedback(
            viewContainer: View,
            status: Boolean,
            message: String
        ){

        val snackbar = Snackbar
            .make(
                viewContainer,
                message,
                Snackbar.LENGTH_LONG)

        val iconResource =
            if ( status )
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

        val iconColor =
            if ( status )
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

        val textView = snackbar
            .view
            .findViewById<TextView>( com.google.android.material.R.id.snackbar_text )

        textView.setText( spannedTxt, TextView.BufferType.SPANNABLE)

        snackbar.show()
    }

    abstract fun blockFields( status: Boolean )

    abstract fun isMainButtonSending( status: Boolean )

    abstract fun mainAction( view: View? = null )
}
