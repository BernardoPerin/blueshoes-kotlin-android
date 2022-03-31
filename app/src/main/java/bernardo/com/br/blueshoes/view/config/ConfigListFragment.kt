package bernardo.com.br.blueshoes.view.config


import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bernardo.com.br.blueshoes.R
import kotlinx.android.synthetic.main.fragment_config_list.*


abstract class ConfigListFragment :
    ConfigFormFragment() {

    var callbackBlockFields : (Boolean)->Unit = {}
    var callbackMainButtonSending : (Boolean)->Unit = {}
    var callbackMainRemoveItem : (Boolean)->Unit = {}

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        updateFlFormToFullFreeScreen()
        initItems()
    }

    private fun initItems(){
        rv_items.setHasFixedSize( true )

        val layoutManager = LinearLayoutManager(activity)
        rv_items.layoutManager = layoutManager

        val adapter = getRecyclerViewAdapter()

        adapter.registerAdapterDataObserver( RecyclerViewObserver() )
        rv_items.adapter = adapter
    }

    abstract fun getRecyclerViewAdapter() : RecyclerView.Adapter<out RecyclerView.ViewHolder>

    override fun getLayoutResourceID()
        = R.layout.fragment_config_list

    override fun blockFields(status: Boolean) {
        callbackBlockFields( status )
    }

    override fun isMainButtonSending(status: Boolean) {
        callbackMainButtonSending( status )
        callbackMainRemoveItem( status )
    }

    fun callbacksToChangeItem(
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
                if( rv_items.adapter!!.itemCount == 0 )
                    View.VISIBLE
                else
                    View.GONE
        }
    }
}
