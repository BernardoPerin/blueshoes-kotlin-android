package bernardo.com.br.blueshoes.view.config.connectiondata

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class ConnectDataSectionsAdapter(
        val context: Context,
        fm: FragmentManager
    ) : FragmentPagerAdapter(fm) {

    companion object{
        const val TOTAL_PAGES = 2
        const val EMAIL_PAGE_POS = 0
    }

    override fun getItem(position: Int)
        = when( position ){
            EMAIL_PAGE_POS -> FormEmailFragment()
            else -> FormPasswordFragment()
        }

    override fun getPageTitle(position: Int)
        = context.getString(
            when( position ){
                EMAIL_PAGE_POS -> FormEmailFragment.TAB_TITLE
                else -> FormPasswordFragment.TAB_TITLE
            }
        )

    override fun getCount(): Int {
        return TOTAL_PAGES
    }
}