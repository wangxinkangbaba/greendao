package wby.laowang.greendaoxmt.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import wby.laowang.greendaoxmt.DUser;
import wby.laowang.greendaoxmt.R;

public class MyAdapter extends RecyclerView.Adapter<MyHolder> {

    private List<DUser> list;
    private Context context;

    public MyAdapter(List<DUser> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.rec_item, parent, false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        holder.rec_name.setText(list.get(position).getName());
        holder.rec_age.setText(list.get(position).getAge());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
