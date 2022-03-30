package bernardo.com.br.blueshoes.view.config.deliveryaddress


import android.os.Bundle
import bernardo.com.br.blueshoes.R
import bernardo.com.br.blueshoes.domain.DeliveryAddress
import kotlinx.android.synthetic.main.fragment_config_new_delivery_address.*


class FormUpdateDeliveryAddressFragment :
    FormNewDeliveryAddressFragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        bt_nu_address.text = getString(R.string.update_delivery_address)

        bt_nu_address.setOnClickListener {
            callPasswordDialog()
        }

        fillForm()
    }

    private fun fillForm(){
        val deliveryAddress = requireArguments().getParcelable<DeliveryAddress>( DeliveryAddress.KEY )
        if (deliveryAddress != null) {
            et_street.setText( deliveryAddress.street )
            et_number.setText( deliveryAddress.number.toString() )
            et_complement.setText( deliveryAddress.complement )
            et_neighborhood.setText( deliveryAddress.neighborhood )
            et_city.setText( deliveryAddress.city )
            et_zip_code.setText( deliveryAddress.zipCode )
            sp_state.setSelection( deliveryAddress.state )
        }
    }

    override fun getLayoutResourceID()
        = R.layout.fragment_config_update_delivery_address

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
            getString(R.string.update_delivery_address_going)
        else
            getString(R.string.update_delivery_address)
    }

}
