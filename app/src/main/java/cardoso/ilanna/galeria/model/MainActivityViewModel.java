package cardoso.ilanna.galeria.model;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import cardoso.ilanna.galeria.model.MyItem;

public class MainActivityViewModel extends ViewModel {

    List<MyItem> itens = new ArrayList<>(); //guarda a lista de itens cadastrados

    public List<MyItem> getItens() { //Obtendo a lista de itens
        return itens;
    }
}
