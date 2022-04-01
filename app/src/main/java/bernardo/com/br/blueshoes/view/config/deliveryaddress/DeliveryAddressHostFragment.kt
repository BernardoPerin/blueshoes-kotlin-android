package bernardo.com.br.blueshoes.view.config.deliveryaddress


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bernardo.com.br.blueshoes.R
import bernardo.com.br.blueshoes.view.config.ConfigFormFragment


class DeliveryAddressHostFragment :
    ConfigFormFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(
            R.layout.fragment_config_delivery_address_host,
            container,
            false
        )

        if ( savedInstanceState == null ){
            val transaction = requireActivity()
                .supportFragmentManager
                .beginTransaction()

            transaction
                .replace(
                    R.id.fl_root,
                    DeliveryAddressesListFragment()
                )
                .commit()
        }

        return view
    }

    override fun title()
        = DeliveryAddressesListFragment.TAB_TITLE

    override fun getLayoutResourceID() = 0

    override fun backEndFakeDelay() {}

    override fun blockFields(status: Boolean) {}

    override fun isMainButtonSending(status: Boolean) {}
}
