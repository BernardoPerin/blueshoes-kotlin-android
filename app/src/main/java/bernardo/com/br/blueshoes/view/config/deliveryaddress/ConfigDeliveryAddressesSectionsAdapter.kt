package bernardo.com.br.blueshoes.view.config.deliveryaddress

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class ConfigDeliveryAddressesSectionsAdapter(
        val context: Context,
        fm: FragmentManager
    ) : FragmentPagerAdapter(fm) {

    companion object{
        const val TOTAL_PAGES = 2
        const val HOST_DELIVERY_ADDRESSES_PAGE_POS  = 0
    }

    override fun getItem(position: Int)
        = when( position ){
        HOST_DELIVERY_ADDRESSES_PAGE_POS  -> ConfigDeliveryAddressHostFragment()
            else -> ConfigNewDeliveryAddressFragment()
        }

    override fun getPageTitle(position: Int)
        = context.getString(
            when( position ){
                HOST_DELIVERY_ADDRESSES_PAGE_POS  -> ConfigDeliveryAddressesListFragment.TAB_TITLE
                else -> ConfigNewDeliveryAddressFragment.TAB_TITLE
            }
        )

    override fun getCount(): Int {
        return TOTAL_PAGES
    }
}