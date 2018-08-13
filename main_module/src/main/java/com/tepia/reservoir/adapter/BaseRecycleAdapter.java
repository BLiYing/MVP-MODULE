package com.tepia.reservoir.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * recycleview是适配器基类
 * Created by my dell on 2016/12/23.
 */
public abstract class BaseRecycleAdapter<T> extends RecyclerView.Adapter<BaseRecycleAdapter.BaseViewHolder> {
    protected List<T> list;
    private int viewTypes;
    private OnItemClickListener mOnItemClickListener;

    /**
     * @param list      列表
     * @param viewTypes item类型（如果只有一种类型就传：1，两种类型传：2，三种类型传：3）
     */
    public BaseRecycleAdapter(List<T> list, int viewTypes) {
        this.list = list;
        this.viewTypes = viewTypes;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(viewType), parent, false);
        return new BaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseRecycleAdapter.BaseViewHolder holder, int position) {
        bindData(holder, position);
        setOnItemListener(holder);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * 根据索引值，返回不同的布局类型：
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        // 根据索引获取当前View的类型，以达到复用的目的
        // 根据位置的索引，获取当前position的类型
        if (viewTypes == 2) {
            return position;
        } else if (viewTypes == 3) {
            if (position % 3 == 0)
                return 3;
            if (position % 3 == 2)
                return 2;
            else
                return 1;
        } else {
            return 0;
        }
    }

    /**
     * 封装ViewHolder ,子类可以直接使用
     */
    public class BaseViewHolder extends RecyclerView.ViewHolder {
        private Map<Integer, View> mViewMap;

        public BaseViewHolder(View itemView) {
            super(itemView);
            mViewMap = new HashMap<>();
        }

        /**
         * 获取设置的view
         *
         * @param id
         * @return ViewHolder中的控件
         */
        public View getView(int id) {
            View view = mViewMap.get(id);
            if (view == null) {
                view = itemView.findViewById(id);
                mViewMap.put(id, view);
            }
            return view;
        }

        /**
         * 设置文本属性
         *
         * @param view
         * @param text
         */
        public void setItemText(View view, String text) {
            if (view instanceof TextView) {
                ((TextView) view).setText(text);
            }
        }
    }

    /**
     * 获取子item
     *
     * @return item布局
     */
    public abstract int getLayoutId(int ViewType);

    /**
     * 绑定数据
     *
     * @param holder   具体的ViewHolder
     * @param position 对应的索引
     */
    protected abstract void bindData(BaseViewHolder holder, int position);

    protected void setOnItemListener(final RecyclerView.ViewHolder holder) {
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutPosition = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, layoutPosition);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int layoutPosition = holder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(holder.itemView, layoutPosition);
                    return true;
                }
            });
        }
    }

    //定义接口
    public interface OnItemClickListener {
        void onItemClick(View v, int position);

        void onItemLongClick(View v, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    //添加方法
    public void addData(List<T> list) {
        for (T t : list) {
            this.list.add(t);
        }
        this.notifyDataSetChanged();
    }

    //全部移除方法
    public void removeAll() {
        list.clear();
        this.notifyDataSetChanged();
    }
}
