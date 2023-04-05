package cardoso.ilanna.galeria.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaParser;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cardoso.ilanna.galeria.R;
import cardoso.ilanna.galeria.model.MyItem;

public class NewItemActivity extends AppCompatActivity {
    static int PHOTO_PICKER_REQUEST = 1;
    static int NEW_ITEM_REQUEST = 1;
    List<MyItem> itens = new ArrayList<>();
    Uri photoSelected = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        ImageButton imgCI = findViewById(R.id.imbCI);
        imgCI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, PHOTO_PICKER_REQUEST);
            }
        });
       Button btnAddItem = findViewById(R.id.btnAddItem);
       btnAddItem.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if (photoSelected == null) {
                   Toast.makeText(NewItemActivity.this, "É necessário selecionar uma imagem!", Toast.LENGTH_LONG).show();
                   return;
               }
               EditText etTitle = findViewById(R.id.etTitle);
               String title = etTitle.getText().toString();
               if (title.isEmpty()) {
                   Toast.makeText(NewItemActivity.this, "É necessário inserir um título", Toast.LENGTH_LONG).show();
                   return;
               }

               EditText etDesc = findViewById(R.id.etDesc);
               String description = etDesc.getText().toString();
               if (description.isEmpty()) {
                   Toast.makeText(NewItemActivity.this, "É necessário inserir uma descrição", Toast.LENGTH_LONG).show();
                   return;
               }

               Intent i = new Intent();
               i.setData(photoSelected);
               i.putExtra("title", title);
               i.putExtra("description", description);
               setResult(Activity.RESULT_OK, i);
               finish();
           }
       });
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_PICKER_REQUEST) {
            if(resultCode == Activity.RESULT_OK) {
                photoSelected = data.getData();
                ImageView imvfotoPreview = findViewById(R.id.imvPhotoPreview);
                imvfotoPreview.setImageURI(photoSelected);

            }
            if (requestCode == NEW_ITEM_REQUEST) {
                if (requestCode == Activity.RESULT_OK) {
                    MyItem myItem = new MyItem();
                    myItem.title = data.getStringExtra("title");
                    myItem.description = data.getStringExtra("description");
                    myItem.photo = data.getData();
                    itens.add(myItem);
                }
            }
        }


    }
}