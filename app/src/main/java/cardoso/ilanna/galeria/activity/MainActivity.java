package cardoso.ilanna.galeria.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import cardoso.ilanna.galeria.R;
import cardoso.ilanna.galeria.adapter.MyAdapter;
import cardoso.ilanna.galeria.model.MainActivityViewModel;
import cardoso.ilanna.galeria.model.MyItem;
import cardoso.ilanna.galeria.model.Util;


public class MainActivity extends AppCompatActivity {
    static int NEW_ITEM_REQUEST = 1; //váriavel inicial 1 para encaminhar a interface para a interface 1
    List<MyItem> itens = new ArrayList<>(); //instanciando uma lista
    MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fabAddItem = findViewById(R.id.fabAddNewItem); //Possuindo o botão na interface
        fabAddItem.setOnClickListener(new View.OnClickListener() { //Adicionando o ouvidor de clicks
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, NewItemActivity.class); //Criação de uma intenção explícita que leva a MainActivity para o NewItemActivity
                startActivityForResult(i, NEW_ITEM_REQUEST); //Iniciando a activity de forma que a activity final irá retornar os dados para a MainActivity

            }
        });
        RecyclerView rvItens = findViewById(R.id.rvItens); //obtendo o recycle
        myAdapter = new MyAdapter(this, itens); //instanciando e criando o MyAdapter
        rvItens.setAdapter(myAdapter); //colocando ele no recycle

        rvItens.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvItens.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvItens.getContext(),DividerItemDecoration.VERTICAL);
        rvItens.addItemDecoration(dividerItemDecoration);


    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_ITEM_REQUEST) {
            if(resultCode == Activity.RESULT_OK) { //verificando se a result code foi um sucesso
                MyItem myItem = new MyItem(); //
                myItem.title = data.getStringExtra("title"); //obtendo os resultados de newItem e realocando ela em myItem
                myItem.description = data.getStringExtra("description");
                Uri selectedPhotoURI = data.getData();

                try {
                    Bitmap photo = Util.getBitmap(MainActivity.this, selectedPhotoURI, 100, 100); //carrega a imagem e a guarda dentro de um bitmap
                    myItem.photo = photo; //colocando a cópia photo dentro do photo do myItem
                } catch (FileNotFoundException e) { //exceção caso a imagem não seja encontrada
                    e.printStackTrace();
                }
                MainActivityViewModel vm = new ViewModelProvider( this ).get(MainActivityViewModel.class);
                List<MyItem> itens = vm.getItens();

                itens.add(myItem);
                myAdapter.notifyItemInserted(itens.size()-1);

            }


        }

    }
}