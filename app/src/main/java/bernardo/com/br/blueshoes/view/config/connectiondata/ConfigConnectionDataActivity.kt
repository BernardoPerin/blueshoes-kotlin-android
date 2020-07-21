package bernardo.com.br.blueshoes.view.config.connectiondata

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import bernardo.com.br.blueshoes.R
import bernardo.com.br.blueshoes.view.config.connectiondata.ui.main.ConfigConnectionDataSectionsAdapter

class ConfigConnectionDataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config_connection_data)

        supportActionBar?.setDisplayHomeAsUpEnabled( true )
        supportActionBar?.setDisplayShowHomeEnabled( true )

        window.setBackgroundDrawableResource( R.drawable.bg_activity )

        val sectionsPagerAdapter = ConfigConnectionDataSectionsAdapter(this, supportFragmentManager)

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