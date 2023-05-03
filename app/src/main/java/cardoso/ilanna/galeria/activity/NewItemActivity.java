package cardoso.ilanna.galeria.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

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
import cardoso.ilanna.galeria.model.MainActivityViewModel;
import cardoso.ilanna.galeria.model.MyItem;
import cardoso.ilanna.galeria.model.NewItemActivityModel;

public class NewItemActivity extends AppCompatActivity {
    static int PHOTO_PICKER_REQUEST = 1;
    static int NEW_ITEM_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        NewItemActivityModel vm = new ViewModelProvider(this).get(NewItemActivityModel.class);

        Uri selectPhotoLocation = vm.getSelectedPhotoLocation();
        if(selectPhotoLocation != null) {
            ImageView imvfotoPreview = findViewById(R.id.imvPhotoPreview);
            imvfotoPreview.setImageURI(selectPhotoLocation);
        }
        ImageButton imgCI = findViewById(R.id.imbCI); //Capturando a img
        imgCI.setOnClickListener(new View.OnClickListener() { //setando o ouvidor de clicks
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT); //abrindo a galeria com um intent
                photoPickerIntent.setType("image/*"); //inserindo na intent que o unico tipo de arquivo é imagem
                startActivityForResult(photoPickerIntent, PHOTO_PICKER_REQUEST); //iniciando a activity com a imagem que foi selecionada
            }
        });
       Button btnAddItem = findViewById(R.id.btnAddItem); //obtendo o botão
       btnAddItem.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Uri photoSelected = vm.getSelectedPhotoLocation();
               if (photoSelected == null) { //se não tiver selecionado a foto, então aparecerá a mensagem
                   Toast.makeText(NewItemActivity.this, "É necessário selecionar uma imagem!", Toast.LENGTH_LONG).show();
                   return;
               }
               EditText etTitle = findViewById(R.id.etTitle); //obtendo o titulo
               String title = etTitle.getText().toString(); //transformando o text em string
               if (title.isEmpty()) { //se não tiver um titulo, então aparecerá a mensagem
                   Toast.makeText(NewItemActivity.this, "É necessário inserir um título", Toast.LENGTH_LONG).show();
                   return;
               }

               EditText etDesc = findViewById(R.id.etDesc);//obtendo a descrição
               String description = etDesc.getText().toString(); //transformando a descrição em string
               if (description.isEmpty()) { //se não tiver descrição, então aparecerá a mensagem
                   Toast.makeText(NewItemActivity.this, "É necessário inserir uma descrição", Toast.LENGTH_LONG).show();
                   return;
               }

               Intent i = new Intent(); //Criando uma intenção para armazenar os dados
               i.setData(photoSelected); //armazenando a uri
               i.putExtra("title", title); //armazenando o titulo
               i.putExtra("description", description); //armazenando a descrição
               setResult(Activity.RESULT_OK, i); //indica o resultado da activity
               finish(); //fim
           }
       });
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) { //requestCode = resposta da StartActivityForResult  resultCode = resultado da activity data = dados da activity recebida
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_PICKER_REQUEST) { //vemos se request code é igual a interface 1
            if(resultCode == Activity.RESULT_OK) { //verificando a resposta do result code, se estiver ok então...
                Uri photoSelected = data.getData(); //colocando os dados no photoSelected
                ImageView imvfotoPreview = findViewById(R.id.imvPhotoPreview);
                imvfotoPreview.setImageURI(photoSelected);

               NewItemActivityModel vm = new ViewModelProvider( this ).get(NewItemActivityModel.class);
               vm.setSelectedPhotoLocation(photoSelected);
            }


        }


    }
}