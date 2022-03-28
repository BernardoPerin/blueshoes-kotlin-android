package bernardo.com.br.blueshoes.view.config.deliveryaddress


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import bernardo.com.br.blueshoes.R


class ConfigDeliveryAddressHostFragment :
    Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

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
                    ConfigDeliveryAddressesListFragment()
                )
                .commit()
        }

        return view
    }
}
