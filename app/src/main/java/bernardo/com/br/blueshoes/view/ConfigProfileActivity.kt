package bernardo.com.br.blueshoes.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import bernardo.com.br.blueshoes.R
import bernardo.com.br.blueshoes.domain.User
import bernardo.com.br.blueshoes.util.validate
import com.blankj.utilcode.util.KeyboardUtils
import com.blankj.utilcode.util.ScreenUtils
import kotlinx.android.synthetic.main.content_config_profile.*

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

        val photoProfileId = iv_profile.id
        val parent = iv_profile.parent as ConstraintLayout
        val constraintSet = ConstraintSet()
        val size = (108 * ScreenUtils.getScreenDensity().toInt())

        constraintSet.constrainWidth(
            photoProfileId,
            size
        )
        constraintSet.constrainHeight(
            photoProfileId,
            size
        )

        constraintSet.centerHorizontally(
            photoProfileId,
            ConstraintLayout.LayoutParams.PARENT_ID
        )

        if( isConstraintToSiblingView( isKeyBoardOpened ) ){
            setConstraintsRelativeToSiblingView( constraintSet, photoProfileId)
        }
        else{
            constraintSet.connect(
                photoProfileId,
                ConstraintLayout.LayoutParams.TOP,
                ConstraintLayout.LayoutParams.PARENT_ID,
                ConstraintLayout.LayoutParams.TOP
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
