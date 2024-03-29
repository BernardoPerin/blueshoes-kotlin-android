package bernardo.com.br.blueshoes.view.shoes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import bernardo.com.br.blueshoes.R
import bernardo.com.br.blueshoes.domain.CreditCard
import bernardo.com.br.blueshoes.domain.Price
import bernardo.com.br.blueshoes.domain.Rate
import bernardo.com.br.blueshoes.domain.Shoes
import com.squareup.picasso.Picasso

class AllShoesListAdapter(
    private val shoesItems : List<Shoes>
    )
    : RecyclerView.Adapter<AllShoesListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        type: Int): ViewHolder {

        val layout = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.shoes_item,
                parent,
                false
            )

        return ViewHolder(layout)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int) {

        holder.setData( shoesItems[position] )
    }

    override fun getItemCount() = shoesItems.size

    inner class ViewHolder( itemView: View )
        : RecyclerView.ViewHolder( itemView ){

        private val context : Context

        private val ivModel : ImageView
        private val tvModel : TextView

        private val ivBrand : ImageView

        private val llDiscount : LinearLayout
        private val tvDiscount : TextView

        private val tvPriceCurrent : TextView
        private val tvPriceWithoutDiscount : TextView
        private val tvPriceParcels : TextView

        private val ivRateStar1 : ImageView
        private val ivRateStar2 : ImageView
        private val ivRateStar3 : ImageView
        private val ivRateStar4 : ImageView
        private val ivRateStar5 : ImageView
        private val tvNumRates : TextView

        init {
            context = itemView.context

            ivModel = itemView.findViewById( R.id.iv_model )
            tvModel = itemView.findViewById( R.id.tv_model )

            ivBrand = itemView.findViewById( R.id.iv_brand )

            llDiscount = itemView.findViewById( R.id.ll_discount )
            tvDiscount = itemView.findViewById( R.id.tv_discount )
            tvPriceCurrent = itemView.findViewById( R.id.tv_price_current )
            tvPriceWithoutDiscount = itemView.findViewById( R.id.tv_price_without_discount )
            tvPriceParcels = itemView.findViewById( R.id.tv_price_parcels )

            ivRateStar1 = itemView.findViewById( R.id.iv_rate_star_1 )
            ivRateStar2 = itemView.findViewById( R.id.iv_rate_star_2 )
            ivRateStar3 = itemView.findViewById( R.id.iv_rate_star_3 )
            ivRateStar4 = itemView.findViewById( R.id.iv_rate_star_4 )
            ivRateStar5 = itemView.findViewById( R.id.iv_rate_star_5 )

            tvNumRates = itemView.findViewById( R.id.tv_num_rates )
        }

        fun setData( shoes: Shoes ){
            Picasso
                .get()
                .load( shoes.mainImg )
                .into( ivModel )
            ivModel.contentDescription = shoes.model
            tvModel.text = shoes.model

            Picasso
                .get()
                .load( shoes.brand.logo )
                .into( ivBrand )
            ivBrand.contentDescription = shoes.brand.label

            setPrice( shoes.price )

            setRate( shoes.rate )
        }

        private fun setPrice( price: Price ){

            if( price.hasDiscount ){
                llDiscount.visibility = View.VISIBLE
                tvPriceWithoutDiscount.visibility = View.VISIBLE

                tvPriceCurrent.text = price.getWithDiscountlLabel( context )
                tvPriceWithoutDiscount.text = price.getNormalLabel( context )
                tvDiscount.text = price.getPercentDiscountlLabel()
            }
            else{
                llDiscount.visibility = View.GONE
                tvPriceWithoutDiscount.visibility = View.GONE

                tvPriceCurrent.text = price.getNormalLabel( context )
            }

            tvPriceParcels.text = price.getParcelsLabel( context )
        }

        private fun setRate( rate: Rate ){
            tvNumRates.text = rate.getNumCommentsLabel()

            ivRateStar1.setImageResource( rate.getStarResource( 1 ) )
            ivRateStar2.setImageResource( rate.getStarResource( 2 ) )
            ivRateStar3.setImageResource( rate.getStarResource( 3 ) )
            ivRateStar4.setImageResource( rate.getStarResource( 4 ) )
            ivRateStar5.setImageResource( rate.getStarResource( 5 ) )

        }
    }

}