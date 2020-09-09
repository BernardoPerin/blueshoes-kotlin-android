package bernardo.com.br.blueshoes.view.config.deliveryaddress

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import bernardo.com.br.blueshoes.R
import bernardo.com.br.blueshoes.domain.DeliveryAddress

class ConfigDeliveryAddressesListItemsAdapter(
        private val fragment: ConfigDeliveryAddressesListFragment,
        private val items : MutableList<DeliveryAddress>
    )
    : RecyclerView.Adapter<ConfigDeliveryAddressesListItemsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        type: Int): ViewHolder {

        val layout = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.delivery_address_item,
                parent,
                false
            )

        return ViewHolder(layout)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int) {

        holder.setData( items[position] )
    }

    override fun getItemCount() = items.size

    inner class ViewHolder( itemView: View )
        : RecyclerView.ViewHolder( itemView ),
            View.OnClickListener{

        private val tvStreet: TextView
        private val tvNumber: TextView
        private val tvZipCode: TextView
        private val tvNeighborhood: TextView
        private val tvCity: TextView
        private val tvState: TextView
        private val tvComplement: TextView
        private val btUpdate: Button
        private val btRemove: Button

        init {
            tvStreet = itemView.findViewById(R.id.tv_street)
            tvNumber = itemView.findViewById(R.id.tv_number)
            tvZipCode = itemView.findViewById(R.id.tv_zip_code)
            tvNeighborhood = itemView.findViewById(R.id.tv_neighborhood)
            tvCity = itemView.findViewById(R.id.tv_city)
            tvState = itemView.findViewById(R.id.tv_state)
            tvComplement = itemView.findViewById(R.id.tv_complement)

            btUpdate = itemView.findViewById(R.id.bt_update)
            btUpdate.setOnClickListener(this)

            btRemove = itemView.findViewById(R.id.bt_remove)
            btRemove.setOnClickListener(this)
        }

        fun setData( item: DeliveryAddress ){
            tvStreet.text = item.street
            tvNumber.text = item.number.toString()
            tvZipCode.text = item.zipCode
            tvNeighborhood.text = item.neighborhood
            tvCity.text = item.city
            tvState.setText( item.getStateName( fragment.context!! ) )
            tvComplement.text = item.complement
        }

        override fun onClick(view: View) {
            val selectedItem = adapterPosition

            if( view.id == btRemove.id ){
                toRemove( selectedItem )
            }
        }

        private fun toRemove( position: Int ){
            fragment.callbacksToRemoveItem(
                {
                    status ->
                        btUpdate.isEnabled = !status
                        btRemove.isEnabled = !status
                },
                {
                    status ->
                    btRemove.text = if( status )
                            fragment.getString(R.string.remove_item_going)
                        else
                            fragment.getString(R.string.remove_item)
                },
                {
                    status ->
                        if( !status ){
                            items.removeAt( position )
                            notifyItemRemoved( position )
                        }
                }
            )

            fragment.callPasswordDialog()
        }
    }
}