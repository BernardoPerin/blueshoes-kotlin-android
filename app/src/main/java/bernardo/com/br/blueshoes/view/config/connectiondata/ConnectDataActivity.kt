package bernardo.com.br.blueshoes.view.config.connectiondata

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import bernardo.com.br.blueshoes.R
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_tabs_user_config.*

class ConnectDataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabs_user_config)
        setSupportActionBar( toolbar )

        supportActionBar?.setDisplayHomeAsUpEnabled( true )
        supportActionBar?.setDisplayShowHomeEnabled( true )

        window.setBackgroundDrawableResource( R.drawable.bg_activity )

        val sectionsPagerAdapter =
            ConnectDataSectionsAdapter(
                this,
                supportFragmentManager
            )

        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter

        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
    }

    override fun onOptionsItemSelected( item: MenuItem ): Boolean {
        if ( item.itemId == android.R.id.home ) {
            finish()
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}