package bernardo.com.br.blueshoes.view.config.deliveryaddress


import android.os.Bundle
import bernardo.com.br.blueshoes.R
import bernardo.com.br.blueshoes.data.DeliveryAddressesDataBase
import bernardo.com.br.blueshoes.view.config.ConfigListFragment
import kotlinx.android.synthetic.main.fragment_config_list.*


class DeliveryAddressesListFragment :
    ConfigListFragment() {

    companion object{
        const val TAB_TITLE = R.string.config_delivery_addresses_tab_list
    }

    override fun title()
        = TAB_TITLE

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        tv_empty_list.text = getString( R.string.delivery_address_list_empty )

    }

    override fun getRecyclerViewAdapter()
        = DeliveryAddressesListAdapter(
            this,
            DeliveryAddressesDataBase.getItems()
        )

    override fun backEndFakeDelay() {
        backEndFakeDelay(
            true,
            getString(R.string.delivery_address_removed)
        )
    }

}
