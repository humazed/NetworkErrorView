package humazed.github.com.networkerrorview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showButton.setOnClickListener {
            networkErrorView.show()
        }

        hideButton.setOnClickListener {
            networkErrorView.hide()
        }

        networkErrorView.setRetryListener {
            Toast.makeText(this, "Retry", Toast.LENGTH_LONG).show()
        }
    }
}
