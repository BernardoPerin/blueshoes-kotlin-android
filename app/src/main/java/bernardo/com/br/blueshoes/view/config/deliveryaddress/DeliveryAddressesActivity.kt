package bernardo.com.br.blueshoes.view.config.deliveryaddress

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import bernardo.com.br.blueshoes.R
import kotlinx.android.synthetic.main.activity_tabs_user_config.*

class DeliveryAddressesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabs_user_config)
        setSupportActionBar( toolbar )

        supportActionBar?.setDisplayHomeAsUpEnabled( true )
        supportActionBar?.setDisplayShowHomeEnabled( true )

        window.setBackgroundDrawableResource( R.drawable.bg_activity )

        val sectionsPagerAdapter =
            DeliveryAddressesSectionsAdapter(
                this,
                supportFragmentManager
            )

        view_pager.adapter = sectionsPagerAdapter

        tabs.setupWithViewPager(view_pager)
    }

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