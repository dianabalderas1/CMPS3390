package dbalderas1.a15

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_know_more.*

/**
 * Know More Activity Driver class for A10
 * @author Diana Balderas
 * @version 1.0
 */
class KnowMoreActivity : AppCompatActivity() {
    /**
     * The activity sends the user to a web page when the button is clicked
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_know_more)

        btnLearnMore.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.who.int/emergencies/diseases/novel-coronavirus-2019"))
            startActivity(intent)
        }
    }
}