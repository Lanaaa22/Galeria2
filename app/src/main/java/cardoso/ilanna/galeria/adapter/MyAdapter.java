package cardoso.ilanna.galeria.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cardoso.ilanna.galeria.R;
import cardoso.ilanna.galeria.activity.MainActivity;
import cardoso.ilanna.galeria.model.MyItem;

public class MyAdapter extends RecyclerView.Adapter {
    MainActivity mainActivity;
    List<MyItem> itens;

    public MyAdapter(MainActivity mainActivity, List<MyItem> itens) {
        this.mainActivity = mainActivity;
        this.itens = itens;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mainActivity); //inflador de layouts
        View v = inflater.inflate(R.layout.item_list, parent, false); //criando os itens de interface e guardando no v
        return new MyViewHolder(v);
    }

    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyItem myItem = itens.get(position);
        View v = holder.itemView; //guarda os itens que estão na classe Holder

        ImageView imvfoto = v.findViewById(R.id.imvFoto);
        imvfoto.setImageURI(myItem.photo);

        TextView tvTitle = v.findViewById(R.id.tvTitle);
        tvTitle.setText(myItem.title);

        TextView tvdesc = v.findViewById(R.id.tvDesc);
        tvdesc.setText(myItem.description);
    }

    public int getItemCount() {
        return itens.size();
    }
}
