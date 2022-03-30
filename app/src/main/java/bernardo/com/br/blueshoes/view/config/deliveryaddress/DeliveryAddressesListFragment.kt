package bernardo.com.br.blueshoes.view.config.deliveryaddress


import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bernardo.com.br.blueshoes.R
import bernardo.com.br.blueshoes.data.DeliveryAddressesDataBase
import bernardo.com.br.blueshoes.view.FormFragment
import kotlinx.android.synthetic.main.fragment_config_delivery_addresses_list.*


class DeliveryAddressesListFragment :
    FormFragment() {

    companion object{
        const val TAB_TITLE = R.string.config_delivery_addresses_tab_list
    }

    var callbackBlockFields : (Boolean)->Unit = {}
    var callbackMainButtonSending : (Boolean)->Unit = {}
    var callbackMainRemoveItem : (Boolean)->Unit = {}

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        updateFlFormToFullFreeScreen()
        initItems()
    }

    private fun initItems(){
        rv_delivery_addresses.setHasFixedSize( true )

        val layoutManager = LinearLayoutManager(activity)
        rv_delivery_addresses.layoutManager = layoutManager

        val adapter = DeliveryAddressesListAdapter(
            this,
            DeliveryAddressesDataBase.getItems()
        )

        adapter.registerAdapterDataObserver( RecyclerViewObserver() )
        rv_delivery_addresses.adapter = adapter
    }

    override fun getLayoutResourceID()
        = R.layout.fragment_config_delivery_addresses_list

    override fun backEndFakeDelay() {
        backEndFakeDelay(
            true,
            getString(R.string.delivery_address_removed)
        )
    }

    override fun blockFields(status: Boolean) {
        callbackBlockFields( status )
    }

    override fun isMainButtonSending(status: Boolean) {
        callbackMainButtonSending( status )
        callbackMainRemoveItem( status )
    }

    fun callbacksToRemoveItem(
        blockFields : (Boolean)->Unit,
        mainButtonSending : (Boolean)->Unit,
        removeItem : (Boolean)->Unit
    ){
        callbackBlockFields = blockFields
        callbackMainButtonSending = mainButtonSending
        callbackMainRemoveItem = removeItem
    }

    inner class RecyclerViewObserver : RecyclerView.AdapterDataObserver(){
        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            super.onItemRangeRemoved(positionStart, itemCount)

            tv_empty_list.visibility =
                if( rv_delivery_addresses.adapter!!.itemCount == 0 )
                    View.VISIBLE
                else
                    View.GONE
        }
    }
}
