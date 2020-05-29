package bernardo.com.br.blueshoes.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import bernardo.com.br.blueshoes.R
import bernardo.com.br.blueshoes.domain.User
import bernardo.com.br.blueshoes.util.isValidEmail
import bernardo.com.br.blueshoes.util.validate
import com.blankj.utilcode.util.KeyboardUtils
import com.blankj.utilcode.util.ScreenUtils
import kotlinx.android.synthetic.main.content_config_profile.*
import kotlinx.android.synthetic.main.content_forgot_password.*
import kotlinx.android.synthetic.main.content_login.*
import kotlinx.android.synthetic.main.info_block.*
import kotlinx.android.synthetic.main.text_view_privacy_policy_login.*

class ConfigProfileActivity :
    FormActivity(),
    KeyboardUtils.OnSoftInputChangedListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        KeyboardUtils.registerSoftInputChangedListener(
            this,
            this
        )

        et_name.validate(
            {
                it.length > 1
            },
            getString(R.string.invalid_name)
        )

        et_name.setOnEditorActionListener( this )

        val user = intent.getParcelableExtra<User>( User.Key )
        et_name.setText( user.name )
    }

    override fun getLayoutResourceID()
            = R.layout.content_config_profile

    override fun backEndFakeDelay(){
        backEndFakeDelay(
            false,
            getString(R.string.invalid_config_profile)
        )
    }

    override fun blockFields( status: Boolean ){
        iv_profile.isEnabled = !status
        et_name.isEnabled = !status
        bt_send_profile.isEnabled = !status
    }

    override fun isMainButtonSending( status: Boolean ){
        bt_send_profile.text =
            if( status )
                getString(R.string.config_profile_going)
            else
                getString(R.string.config_profile)
    }

    fun callGallery( view: View ){
        Toast
            .makeText( this, "TODO", Toast.LENGTH_SHORT)
            .show()
    }

    override fun onDestroy() {
        KeyboardUtils.unregisterSoftInputChangedListener(this)
        super.onDestroy()
    }

    override fun onSoftInputChanged(height: Int) {
        if( isAbleToCallChangeTargetViewConstraints() ){
            changeTargetViewConstraints(
                KeyboardUtils.isSoftInputVisible( this )
            )
        }
    }

    fun isAbleToCallChangeTargetViewConstraints()
            = true

    private fun changeTargetViewConstraints(
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

    fun isConstraintToSiblingView( isKeyBoardOpened: Boolean )
            = isKeyBoardOpened || ScreenUtils.isLandscape()

    fun setConstraintsRelativeToSiblingView(
        constraintSet: ConstraintSet,
        targetViewId: Int
    ){
        constraintSet.connect(
            targetViewId,
            ConstraintLayout.LayoutParams.BOTTOM,
            tv_name.id,
            ConstraintLayout.LayoutParams.TOP,
            (30 * ScreenUtils.getScreenDensity().toInt())
        )
    }
}
