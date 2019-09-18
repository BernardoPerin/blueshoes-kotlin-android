package bernardo.com.br.blueshoes.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import bernardo.com.br.blueshoes.R

import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

}
