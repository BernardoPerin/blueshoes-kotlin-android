package bernardo.com.br.blueshoes.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import bernardo.com.br.blueshoes.R
import com.blankj.utilcode.util.KeyboardUtils
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

        if( isConstraintToSiblingView( isKeyBoardOpened ) ){
            setConstraintsRelativeToSiblingView( constraintSet, privacyId)
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

    abstract fun isConstraintToSiblingView( isKeyBoardOpened: Boolean ) : Boolean

    abstract fun setConstraintsRelativeToSiblingView(
            constraintSet: ConstraintSet,
            privacyId: Int
        ) : Unit
}
