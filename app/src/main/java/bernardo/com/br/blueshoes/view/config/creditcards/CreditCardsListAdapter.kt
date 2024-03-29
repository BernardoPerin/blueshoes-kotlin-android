package bernardo.com.br.blueshoes.view.config.creditcards

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import bernardo.com.br.blueshoes.R
import bernardo.com.br.blueshoes.domain.CreditCard
import bernardo.com.br.blueshoes.view.config.ConfigListFragment

class CreditCardsListAdapter(
    private val fragment: ConfigListFragment,
    private val items : MutableList<CreditCard>
    )
    : RecyclerView.Adapter<CreditCardsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        type: Int): ViewHolder {

        val layout = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.credit_card_item,
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

        private val tvEnterprise: TextView
        private val tvNumber: TextView
        private val tvOwnerName: TextView
        private val btRemove: Button

        init {
            tvEnterprise = itemView.findViewById(R.id.tv_enterprise)
            tvNumber = itemView.findViewById(R.id.tv_number)
            tvOwnerName = itemView.findViewById(R.id.tv_owner_name)

            btRemove = itemView.findViewById(R.id.bt_remove)
            btRemove.setOnClickListener(this)
        }

        fun setData( item: CreditCard ){
            tvEnterprise.text = item.enterprise
            tvNumber.setText( item.getNumberAsHidden() )
            tvOwnerName.setText( item.getOwnerFullNameAsHidden() )
        }

        override fun onClick(view: View) {
            val selectedItem = adapterPosition

            fragment.callbacksToChangeItem(
                {
                    status -> btRemove.isEnabled = !status
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
                            items.removeAt( selectedItem )
                            notifyItemRemoved( selectedItem )
                        }
                }
            )

            fragment.callPasswordDialog()
        }
    }
}