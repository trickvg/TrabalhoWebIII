//package ifrs.pw3.trabalhowebiii.view;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Handler;
//import android.support.v7.app.AppCompatActivity;
//
//import ifrs.pw3.trabalhowebiii.R;
//
//public class SplashScreenActivity extends AppCompatActivity {
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash_screen);
//        Handler handle = new Handler();
//        handle.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mostrarLogin();
//            }
//        }, 3000);
//
//    }
//
//    private void mostrarLogin() {
//        Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
//        startActivity(intent);
//        finish();
//    }
//
//}