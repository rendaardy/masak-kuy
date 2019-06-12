package com.renda.masakkuy;

import android.support.annotation.NonNull;
import android.view.View;
import android.support.v7.widget.RecyclerView;

public class ItemClickSupport {
    private RecyclerView recyclerView;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    private View.OnClickListener onClickListener = v -> {
        if (onItemClickListener != null) {
            RecyclerView.ViewHolder holder = recyclerView.getChildViewHolder(v);
            onItemClickListener.onItemClicked(recyclerView, holder.getAdapterPosition(), v);
        }
    };

    private View.OnLongClickListener onLongClickListener = v -> {
        if (onItemLongClickListener != null) {
            RecyclerView.ViewHolder holder = recyclerView.getChildViewHolder(v);
            return onItemLongClickListener.onItemLongClicked(recyclerView, holder.getAdapterPosition(), v);
        }

        return false;
    };

    private RecyclerView.OnChildAttachStateChangeListener attachListener = new RecyclerView.OnChildAttachStateChangeListener() {
        @Override
        public void onChildViewAttachedToWindow(@NonNull View view) {
            if (onItemClickListener != null) {
                view.setOnClickListener(onClickListener);
            }

            if (onItemLongClickListener != null) {
                view.setOnLongClickListener(onLongClickListener);
            }
        }

        @Override
        public void onChildViewDetachedFromWindow(@NonNull View view) {

        }
    };

    private ItemClickSupport(RecyclerView view) {
        this.recyclerView = view;
        this.recyclerView.setTag(R.id.item_click_support, this);
        this.recyclerView.addOnChildAttachStateChangeListener(attachListener);
    }

    public static ItemClickSupport addTo(RecyclerView view) {
        ItemClickSupport support = (ItemClickSupport) view.getTag(R.id.item_click_support);

        if (support == null) {
            support = new ItemClickSupport(view);
        }

        return support;
    }

    public static ItemClickSupport removeFrom(RecyclerView view) {
        ItemClickSupport support = (ItemClickSupport) view.getTag(R.id.item_click_support);

        if (support != null) {
            support.detach(view);
        }

        return support;
    }

    private void detach(RecyclerView view) {
        view.removeOnChildAttachStateChangeListener(attachListener);
        view.setTag(R.id.item_click_support, null);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        onItemLongClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClicked(RecyclerView recyclerView, int position, View v);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClicked(RecyclerView recyclerView, int position, View v);
    }
}
