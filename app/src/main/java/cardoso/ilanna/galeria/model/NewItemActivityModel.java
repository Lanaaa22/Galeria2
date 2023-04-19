package cardoso.ilanna.galeria.model;

import android.net.Uri;

import androidx.lifecycle.ViewModel;

public class NewItemActivityModel extends ViewModel {
    Uri selectedPhotoLocation = null; //guardando o URI da foto

    public Uri getSelectedPhotoLocation() { //obtendo a lista de itens
        return selectedPhotoLocation;
    }

    public void setSelectedPhotoLocation(Uri selectedPhotoLocation) { //setando o endereço URI
        this.selectedPhotoLocation = selectedPhotoLocation;
    }
}
