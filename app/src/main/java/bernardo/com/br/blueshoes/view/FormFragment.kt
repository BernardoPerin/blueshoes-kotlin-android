package bernardo.com.br.blueshoes.view


import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.os.SystemClock
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ImageSpan
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import bernardo.com.br.blueshoes.R
import bernardo.com.br.blueshoes.util.isValidPassword
import bernardo.com.br.blueshoes.util.validate
import com.blankj.utilcode.util.ColorUtils
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.proxy_screen.*


abstract class FormFragment :
    Fragment(),
    TextView.OnEditorActionListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val viewContainer = inflater
            .inflate(
                R.layout.fragment_form,
                container,
                false
            )

        View.inflate(
            activity,
            getLayoutResourceID(),
            viewContainer.findViewById(R.id.fl_form)
        )

        return viewContainer
    }

    abstract fun getLayoutResourceID() : Int

    override fun onEditorAction(
        view: TextView,
        actionId: Int,
        event: KeyEvent?): Boolean {

        callPasswordDialog()
        return false
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
                    activity!!,
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

    fun mainAction( view: View? = null ){
        blockFields( true )
        isMainButtonSending( true )
        showProxy( true )
        backEndFakeDelay()
    }

    abstract fun backEndFakeDelay() : Unit

    abstract fun blockFields( status: Boolean )

    abstract fun isMainButtonSending( status: Boolean )

    protected fun backEndFakeDelay(
        statusAction: Boolean,
        feesbackMessage: String
    ){
        Thread{
            run {
                SystemClock.sleep(1000)

                activity!!.runOnUiThread{
                    blockFields( false )
                    isMainButtonSending( false )
                    showProxy( false )

                    val containerView = fl_proxy_container.parent as ViewGroup

                    snackBarFeedback(
                        containerView,
                        statusAction,
                        feesbackMessage
                    )
                }
            }
        }.start()
    }

    protected fun callPasswordDialog(){
        val builder = AlertDialog.Builder( activity!! )
        val inflater = activity!!.layoutInflater

        builder
            .setView( inflater.inflate( R.layout.dialog_password, null ) )
            .setPositiveButton(
                R.string.dialog_password_go,
                { dialog, id -> mainAction()}
            )
            .setNegativeButton(
                R.string.dialog_password_cancel,
                { dialog, id -> dialog.cancel() }
            )
            .setCancelable( false )

        val dialog = builder.create()

        dialog.setOnShowListener(
            object : DialogInterface.OnShowListener{

                override fun onShow( d: DialogInterface? ) {
                    dialog
                        .getButton( AlertDialog.BUTTON_POSITIVE )
                        .setTextColor( ColorUtils.getColor(R.color.colorText) )
                    dialog
                        .getButton( AlertDialog.BUTTON_NEGATIVE )
                        .setTextColor( ColorUtils.getColor(R.color.colorText) )

                    val etPassword = dialog.findViewById<EditText>(R.id.et_password)!!
                    etPassword.validate(
                        { it.isValidPassword() },
                        getString( R.string.invalid_password )
                    )

                    etPassword.setOnEditorActionListener {
                            v, actionId, event ->
                            dialog.cancel()
                            mainAction()
                            false
                    }
                }
            }
        )

        dialog.show()
    }
}
