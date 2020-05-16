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
import com.blankj.utilcode.util.KeyboardUtils
import com.blankj.utilcode.util.ScreenUtils
import kotlinx.android.synthetic.main.content_form.*
import kotlinx.android.synthetic.main.content_login.*
import kotlinx.android.synthetic.main.text_view_privacy_policy_login.*

abstract class FormEmailAndPasswordActivity :
    FormActivity(),
    KeyboardUtils.OnSoftInputChangedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        KeyboardUtils.registerSoftInputChangedListener(this,this)
    }

    override fun onDestroy() {
        KeyboardUtils.unregisterSoftInputChangedListener(this)
        super.onDestroy()
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

    override fun onSoftInputChanged(height: Int) {
        if( isAbleToCallChangePrivacyPolicyConstraints() ){
            changePrivacyPolicyConstraints(
                KeyboardUtils.isSoftInputVisible( this )
            )
        }
    }

    open fun isAbleToCallChangePrivacyPolicyConstraints()
        = true
}
