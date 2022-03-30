package bernardo.com.br.blueshoes.view.config.deliveryaddress


import android.os.Bundle
import bernardo.com.br.blueshoes.R
import bernardo.com.br.blueshoes.view.config.ConfigFormFragment
import kotlinx.android.synthetic.main.fragment_config_new_delivery_address.*


open class FormNewDeliveryAddressFragment :
    ConfigFormFragment() {

    companion object{
        const val PAGER_POS = 1
    }

    override fun title()
            = R.string.config_delivery_address_tab_new

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        updateFlFormToFullFreeScreen()

        bt_nu_address.setOnClickListener {
            mainAction()
        }
    }

    override fun getLayoutResourceID()
        = R.layout.fragment_config_new_delivery_address

    override fun backEndFakeDelay() {
        backEndFakeDelay(
            false,
            getString(R.string.invalid_delivery_address)
        )
    }

    override fun blockFields(status: Boolean) {
        et_street.isEnabled = !status
        et_number.isEnabled = !status
        et_complement.isEnabled = !status
        et_neighborhood.isEnabled = !status
        et_city.isEnabled = !status
        et_zip_code.isEnabled = !status
        sp_state.isEnabled = !status
        bt_nu_address.isEnabled = !status
    }

    override fun isMainButtonSending(status: Boolean) {
        bt_nu_address.text = if( status )
            getString(R.string.add_delivery_address_going)
        else
            getString(R.string.add_delivery_address)
    }

}
