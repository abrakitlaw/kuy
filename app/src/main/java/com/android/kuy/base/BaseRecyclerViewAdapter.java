package com.android.kuy.base;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Abraham Ginting (abraham.ginting@dana.id)
 * @version BaseRecyclerViewAdapter, v 0.1 2019-09-10 03:15 by Abraham Ginting
 */
public abstract class BaseRecyclerViewAdapter<VH extends BaseRecyclerViewHolder<T>, T> extends RecyclerView.Adapter<VH> {

    private List<T> items;

    private BaseRecyclerViewHolder.OnItemClickListener onItemClickListener;

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.bindData(getItem(position));
        holder.setOnItemClickListener(getOnItemClickListener());
    }

    @Override
    public int getItemCount() {
        return getItemsSize();
    }

    @Override
    public void onViewRecycled(@NonNull VH holder) {
        holder.dispose();
    }

    public T getItem(int position) {
        if (position < getItemsSize()) {
            return items.get(position);
        }
        return null;
    }

    public BaseRecyclerViewHolder.OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    private int getItemsSize() {
        if (null == items) {
            return 0;
        }
        return items.size();
    }

    public void removeItem(int position) {
        if (items == null || items.size() <= position) {
            return;
        }

        items.remove(position);
        notifyItemRemoved(position);
    }

    public void removeAllItems() {
        if (items == null || items.isEmpty()) {
            return;
        }

        items.clear();
        notifyDataSetChanged();
    }

    public void appendItem(T additionalItem) {
        if (additionalItem == null) {
            return;
        }

        if (items != null) {
            items.add(additionalItem);
            notifyItemChanged(items.size());
        }
    }

    public void appendItems(List<T> additionalItems) {
        if (additionalItems == null) {
            return;
        }

        if (items == null) {
            setItems(additionalItems);
        } else {
            items.addAll(additionalItems);
        }

        notifyDataSetChanged();
    }

    public void prependItems(List<T> additionalItems) {
        addItemsAt(0, additionalItems);
    }

    public void addItemsAt(int position, List<T> additionalItems) {
        if (additionalItems == null) {
            return;
        }

        if (items == null) {
            setItems(additionalItems);
        } else {
            items.addAll(position, additionalItems);
        }

        notifyItemInserted(position);
    }

    /**
     * getItems from List
     */
    public List<T> getItems() {
        return items;
    }

    /**
     * setItems
     */
    public void setItems(List<T> items) {
        this.items = items;
        notifyDataSetChanged();
    }
}
