package dbalderas1.a15;

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_splash_screen.*

/**
 * Splash Screen class for A10
 * @author Diana Balderas
 * @version 1.0
 */
class SplashScreen : AppCompatActivity() {

    /**
     * The splash screen starts the application and directly sends the user to the main activity
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        btnGo.setOnClickListener {
            var intent = Intent(this@SplashScreen,MainActivity::class.java)
            startActivity(intent)
        }
    }
}