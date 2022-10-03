package id.ac.polinema.intentexercise;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.EditText;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;


public class RegisterActivity extends AppCompatActivity {

    private Button btn_ok;
    private CircleImageView imgprofile;
    private EditText etfullname, etemail, ethomepage, etabout;

    private static final String FULLNAME_KEY = "fullname";
    private static final String EMAIL_KEY = "email";
    private static final String HOMEPAGE_KEY = "homepage";
    private static final String ABOUT_KEY = "about";

    private static final String TAG = RegisterActivity.class.getCanonicalName();
    private static final int GALLERY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btn_ok = findViewById(R.id.button_ok);
        imgprofile = findViewById(R.id.image_profile);
        etfullname = findViewById(R.id.text_fullname);
        etemail = findViewById(R.id.text_email);
        ethomepage = findViewById(R.id.text_homepage);
        etabout = findViewById(R.id.text_about);

        btn_ok.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String fullname = etfullname.getText().toString();
                String email = etemail.getText().toString();
                String homepage = ethomepage.getText().toString();
                String about = etabout.getText().toString();

                Intent intent = new Intent(RegisterActivity.this, ProfileActivity.class);
                intent.putExtra(FULLNAME_KEY, fullname);
                intent.putExtra(EMAIL_KEY, email);
                intent.putExtra(HOMEPAGE_KEY, homepage);
                intent.putExtra(ABOUT_KEY, about);
                startActivity(intent);
            }
        });
    }
    public void handleChangeAvatar(View view) {
        Intent tambahavatar = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(tambahavatar, GALLERY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_CANCELED){
            return;
        }else if(requestCode == GALLERY_REQUEST_CODE){
            if(data != null){
                try{
                    Uri imageUri = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),imageUri);
                    imgprofile.setImageBitmap(bitmap);

                    Intent intent = new Intent(this, ProfileActivity.class);
                    intent.putExtra("bitmapimage", bitmap);
                }catch (IOException e){
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }
}



