package com.st18rai.betatestapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.st18rai.betatestapp.R;
import com.st18rai.betatestapp.model.ItemData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignalsAdapter extends RecyclerView.Adapter<SignalsAdapter.SignalsHolder> {
    private List<ItemData> data;

    public List<ItemData> getData() {
        return data;
    }

    public void setData(List<ItemData> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SignalsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_signal, parent, false);

        return new SignalsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SignalsHolder holder, int position) {

        ItemData itemData = data.get(position);
        Context context = holder.getContext();

        holder.actualTime.setText(context.getString(R.string.actual_time, itemData.getActualTime()));
        holder.comment.setText(context.getString(R.string.comment, itemData.getComment()));
        holder.pair.setText(context.getString(R.string.pair, itemData.getPair()));
        holder.cmd.setText(context.getString(R.string.cmd, itemData.getCmd()));
        holder.tradingSystem.setText(context.getString(R.string.trading_system, itemData.getTradingSystem()));
        holder.period.setText(context.getString(R.string.period, itemData.getPeriod()));
        holder.price.setText(context.getString(R.string.price, itemData.getPrice()));
        holder.sl.setText(context.getString(R.string.sl, itemData.getSl()));
        holder.tp.setText(context.getString(R.string.tp, itemData.getTp()));

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    static class SignalsHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.actual_time)
        TextView actualTime;

        @BindView(R.id.comment)
        TextView comment;

        @BindView(R.id.pair)
        TextView pair;

        @BindView(R.id.cmd)
        TextView cmd;

        @BindView(R.id.trading_system)
        TextView tradingSystem;

        @BindView(R.id.period)
        TextView period;

        @BindView(R.id.price)
        TextView price;

        @BindView(R.id.sl)
        TextView sl;

        @BindView(R.id.tp)
        TextView tp;

        private View layout;

        SignalsHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
            layout = v;
        }

        Context getContext() {
            return layout.getContext();
        }
    }
}
