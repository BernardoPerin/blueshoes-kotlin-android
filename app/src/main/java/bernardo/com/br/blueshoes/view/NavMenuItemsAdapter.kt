package bernardo.com.br.blueshoes.view

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.RecyclerView
import bernardo.com.br.blueshoes.R
import bernardo.com.br.blueshoes.domain.NavMenuItem
import bernardo.com.br.blueshoes.util.NavMenuItemDetails

class NavMenuItemsAdapter( val items: List<NavMenuItem> ):
    RecyclerView.Adapter<NavMenuItemsAdapter.ViewHolder>() {

    lateinit var selectionTracker: SelectionTracker<Long>

    override fun onCreateViewHolder(
        parent: ViewGroup,
        type: Int): ViewHolder {

        val layout = LayoutInflater
            .from( parent.context )
            .inflate( R.layout.nav_menu_item, parent, false)
        return ViewHolder( layout )
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int) {

        holder.setData( items[ position ] )
    }

    override fun getItemCount() = items.size

    inner class ViewHolder( itemView: View): RecyclerView.ViewHolder( itemView ){
        private val ivIcon : ImageView
        private val tvLabel : TextView

        val itemDetails : NavMenuItemDetails

        init {
            ivIcon = itemView.findViewById(R.id.iv_icon)
            tvLabel = itemView.findViewById(R.id.tv_label)

            itemDetails = NavMenuItemDetails()
        }

        fun setData( item: NavMenuItem ){
            tvLabel.text = item.label

            if( item.iconId != NavMenuItem.DEFAULT_ICON_ID ){
                ivIcon.setImageResource( item.iconId )
                ivIcon.visibility = View.VISIBLE
            }
            else{
                ivIcon.visibility = View.GONE
            }

            itemDetails.item = item
            itemDetails.adapterPosition = adapterPosition

            if( selectionTracker.isSelected( item.id ) ){
                itemView.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.colorNavItemSelected
                    )
                )
            }
            else{
                itemView.setBackgroundColor( Color.TRANSPARENT )
            }
        }
    }
}