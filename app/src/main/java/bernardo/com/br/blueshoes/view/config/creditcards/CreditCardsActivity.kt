package bernardo.com.br.blueshoes.view.config.creditcards

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import bernardo.com.br.blueshoes.R
import bernardo.com.br.blueshoes.view.config.ConfigFormActivity
import bernardo.com.br.blueshoes.view.config.ConfigSectionsAdapter
import bernardo.com.br.blueshoes.view.config.deliveryaddress.DeliveryAddressHostFragment
import bernardo.com.br.blueshoes.view.config.deliveryaddress.FormNewDeliveryAddressFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_tabs_user_config.*

class CreditCardsActivity : ConfigFormActivity() {

    override fun getSectionsAdapter()
        = ConfigSectionsAdapter(
            this,
            supportFragmentManager,
            CreditCardsListFragment(),
            FormCreditCardFragment()
        )

}