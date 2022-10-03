package id.ac.polinema.intentexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.view.View;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    private CircleImageView imgprofile;
    private TextView fullname, email, homepage, about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        fullname = findViewById(R.id.label_fullname);
        email = findViewById(R.id.label_email);
        homepage = findViewById(R.id.label_homepage);
        about = findViewById(R.id.label_about);
        imgprofile = findViewById(R.id.image_profile);

        Bundle extras = getIntent().getExtras();

        Intent intent = getIntent();
        imgprofile.setImageBitmap((Bitmap) intent.getParcelableExtra("bitmapimage"));

        if(extras != null){
            fullname.setText(extras.getString("fullname"));
            email.setText(extras.getString("email"));
            homepage.setText(extras.getString("homepage"));
            about.setText(extras.getString("about"));
        }else{
            Toast.makeText(this, "Intent is missing", Toast.LENGTH_SHORT).show();
        }
    }
    public void handleHomepage (View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://hisyamap.github.io/CV-Hisyam/"));
        startActivity(intent);
    }
}
