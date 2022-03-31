package bernardo.com.br.blueshoes.view.config

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class ConfigSectionsAdapter(
        val context: Context,
        fm: FragmentManager,
        private vararg val fragments : ConfigFormFragment
    ) : FragmentPagerAdapter(fm) {

    companion object{
        const val TOTAL_PAGES = 2
        const val FIRST_PAGE_POS = 0
        const val SECOND_PAGE_POS = 1
    }

    override fun getItem(position: Int)
        = when( position ){
            FIRST_PAGE_POS -> fragments[ FIRST_PAGE_POS ]
            else -> fragments[ SECOND_PAGE_POS ]
        }

    override fun getPageTitle(position: Int)
        = context.getString(
            when( position ){
                FIRST_PAGE_POS -> fragments[ FIRST_PAGE_POS ].title()
                else -> fragments[ SECOND_PAGE_POS ].title()
            }
        )

    override fun getCount()
        = TOTAL_PAGES

}