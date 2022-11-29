package bitflyday.com.mobile.application.rcode.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import bitflyday.com.mobile.application.rcode.R
import bitflyday.com.mobile.application.rcode.databinding.ActivityContentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContentActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityContentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityContentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_content)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_graph
            ), drawerLayout = null
        )

        setupActionBarWithNavController(navController, appBarConfiguration)


        binding.toolbar.setNavigationOnClickListener(View.OnClickListener {
            this.onBackPressed()
        })
    }
}