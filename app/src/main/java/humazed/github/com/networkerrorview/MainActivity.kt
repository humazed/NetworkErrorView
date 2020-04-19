package humazed.github.com.networkerrorview

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showButton.setOnClickListener {
            networkErrorView.show()
            networkErrorView.setVisibility(View.VISIBLE)
        }

        hideButton.setOnClickListener {
            networkErrorView.hide()
        }

        networkErrorView.setRetryListener {
            Toast.makeText(this, "Retry", Toast.LENGTH_LONG).show()
        }
    }
}
