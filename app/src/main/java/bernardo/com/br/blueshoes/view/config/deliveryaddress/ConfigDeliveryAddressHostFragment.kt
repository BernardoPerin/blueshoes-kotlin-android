package bernardo.com.br.blueshoes.view.config.deliveryaddress


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bernardo.com.br.blueshoes.R
import bernardo.com.br.blueshoes.data.CreditCardsDataBase
import bernardo.com.br.blueshoes.data.DeliveryAddressesDataBase
import bernardo.com.br.blueshoes.view.FormFragment
import kotlinx.android.synthetic.main.fragment_config_delivery_addresses_list.*


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
            val transaction = activity!!
                .supportFragmentManager
                .beginTransaction()

            transaction
                .replace(
                    R.id.fl_form,
                    ConfigDeliveryAddressesListFragment()
                )
                .commit()
        }

        return view
    }
}
