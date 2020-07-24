package bernardo.com.br.blueshoes.view.config.connectiondata

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class ConfigConnectionDataSectionsAdapter(
        val context: Context,
        fm: FragmentManager
    ) : FragmentPagerAdapter(fm) {

    companion object{
        const val TOTAL_PAGES = 2
        const val EMAIL_PAGE_POS = 0
    }

    override fun getItem(position: Int)
        = when( position ){
            EMAIL_PAGE_POS -> ConfigEmailFragment()
            else -> ConfigPasswordFragment()
        }

    override fun getPageTitle(position: Int)
        = context.getString(
            when( position ){
                EMAIL_PAGE_POS -> ConfigEmailFragment.TAB_TITLE
                else -> ConfigPasswordFragment.TAB_TITLE
            }
        )

    override fun getCount(): Int {
        return TOTAL_PAGES
    }
}