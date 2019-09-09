package com.android.kuy.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author Abraham Ginting (abraham.ginting@dana.id)
 * @version BaseRecyclerViewHolder, v 0.1 2019-09-10 03:06 by Abraham Ginting
 */
public abstract class BaseRecyclerViewHolder<T> extends RecyclerView.ViewHolder implements DisposableHandler {

    private final Context context;

    private final View view;

    private CompositeDisposable disposables;

    public BaseRecyclerViewHolder(Context context, int resource, ViewGroup parent) {
        this(context, LayoutInflater.from(context).inflate(resource, parent, false));
    }

    public BaseRecyclerViewHolder(Context context, View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.context = context;
        this.view = itemView;
    }

    /**
     * set {@link OnItemClickListener}
     *
     * @param onItemClickListener {@link OnItemClickListener}
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        if (null != view && null != onItemClickListener) {
            view.setOnClickListener(viewListener -> {
                if (getAdapterPosition() > RecyclerView.NO_POSITION) {
                    onItemClickListener.onItemClick(getAdapterPosition());
                }
            });
        }
    }

    /**
     * get {@link Context}
     *
     * @return Context
     */
    public Context getContext() {
        return context;
    }

    @Override
    public void addDisposable(Disposable disposable) {
        if (disposables != null && !disposables.isDisposed()) {
            disposables.dispose();
        }
    }

    /**
     * bind data
     */
    public abstract void bindData(T data);

    public interface OnItemClickListener {

        /**
         * {@link RecyclerView} child position
         *
         * @param position {@link Integer}
         */
        void onItemClick(int position);
    }
}
