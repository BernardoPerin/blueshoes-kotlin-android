package bernardo.com.br.blueshoes.view.config.creditcards


import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import bernardo.com.br.blueshoes.R
import bernardo.com.br.blueshoes.data.CreditCardsDataBase
import bernardo.com.br.blueshoes.view.config.ConfigListFragment
import kotlinx.android.synthetic.main.fragment_config_list.*


class CreditCardsListFragment :
    ConfigListFragment() {


    override fun title()
        = R.string.config_credit_cards_tab_list

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        tv_empty_list.text = getString( R.string.credit_card_list_empty )
    }

    override fun getRecyclerViewAdapter(): RecyclerView.Adapter<out RecyclerView.ViewHolder>
        = CreditCardsListAdapter(
            this,
            CreditCardsDataBase.getItems()
        )

    override fun backEndFakeDelay() {
        backEndFakeDelay(
            true,
            getString(R.string.credit_card_removed)
        )
    }

}
