package bernardo.com.br.blueshoes.view.config.creditcards

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class CreditCardsSectionsAdapter(
        val context: Context,
        fm: FragmentManager
    ) : FragmentPagerAdapter(fm) {

    companion object{
        const val TOTAL_PAGES = 2
        const val CREDIT_CARDS_PAGE_POS = 0
    }

    override fun getItem(position: Int)
        = when( position ){
            CREDIT_CARDS_PAGE_POS -> CreditCardsListFragment()
            else -> FormCreditCardFragment()
        }

    override fun getPageTitle(position: Int)
        = context.getString(
            when( position ){
                CREDIT_CARDS_PAGE_POS -> CreditCardsListFragment.TAB_TITLE
                else -> FormCreditCardFragment.TAB_TITLE
            }
        )

    override fun getCount(): Int {
        return TOTAL_PAGES
    }
}