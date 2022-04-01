package bernardo.com.br.blueshoes.view.config.deliveryaddress

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import bernardo.com.br.blueshoes.R
import bernardo.com.br.blueshoes.view.config.ConfigFormActivity
import bernardo.com.br.blueshoes.view.config.ConfigSectionsAdapter
import kotlinx.android.synthetic.main.activity_tabs_user_config.*

class DeliveryAddressesActivity : ConfigFormActivity() {

    override fun getSectionsAdapter()
        = ConfigSectionsAdapter(
                this,
            supportFragmentManager,
            DeliveryAddressHostFragment(),
            FormNewDeliveryAddressFragment()
        )

    override fun onOptionsItemSelected( item: MenuItem ): Boolean {
        if ( item.itemId == android.R.id.home ) {
            onBackPressed()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        val fragmentsInStack = supportFragmentManager.backStackEntryCount

        if( fragmentsInStack > 0
            && isNewDeliveryAddressFormNotInScreen() ){

            supportFragmentManager.popBackStack()
        }
        else{
            finish()
        }
    }

    private fun isNewDeliveryAddressFormNotInScreen()
        = view_pager.currentItem != FormNewDeliveryAddressFragment.PAGER_POS

}