package bernardo.com.br.blueshoes.view.config.deliveryaddress


import android.os.Bundle
import android.view.View

import bernardo.com.br.blueshoes.R
import bernardo.com.br.blueshoes.util.isValidCNPJ
import bernardo.com.br.blueshoes.util.isValidCPF
import bernardo.com.br.blueshoes.view.FormFragment
import com.santalu.maskedittext.MaskEditText
import kotlinx.android.synthetic.main.fragment_config_new_delivery_address.*


class ConfigNewDeliveryAddressFragment :
    FormFragment(),
    View.OnFocusChangeListener {

    companion object{
        const val TAB_TITLE = R.string.config_delivery_address_tab_new
    }

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
        met_credit_card_number.isEnabled = !status
        sp_credit_card_enterprise.isEnabled = !status
        et_credit_card_owner_name.isEnabled = !status
        met_credit_card_owner_reg.isEnabled = !status
        sp_credit_card_expiry_month.isEnabled = !status
        et_credit_card_expiry_year.isEnabled = !status
        et_credit_card_security_code.isEnabled = !status
        bt_nu_address.isEnabled = !status
    }

    override fun isMainButtonSending(status: Boolean) {
        bt_nu_address.text = if( status )
            getString(R.string.add_delivery_address_going)
        else
            getString(R.string.add_delivery_address)
    }

}
