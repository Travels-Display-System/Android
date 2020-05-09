package com.zhao.ui_basic.ui.Edit.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zhao.ui_basic.R;
import com.zhao.ui_basic.Utils.Utils;
import com.zhao.ui_basic.ui.Edit.model.WorkModel;

import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.InTheWayHolder> {
    List<WorkModel> intheway;
    private LinearLayoutManager layoutManager;
    public FeedAdapter(List<WorkModel> a){
        this.intheway = a;
    }

    // 利用接口 -> 给RecyclerView设置点击事件
    private ItemClickListener mItemClickListener ;
    public interface ItemClickListener{
        public void onItemClick(int position) ;
        public void delete(int position);
    }
    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.mItemClickListener = itemClickListener ;
    }


    @NonNull
    @Override
    public InTheWayHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutManager = new LinearLayoutManager(parent.getContext());
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new InTheWayHolder(inflater.inflate(R.layout.list_item_way, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InTheWayHolder holder, int position) {
        WorkModel model = intheway.get(position);
        holder.tvName.setText(model.getTitle());
        holder.state.setText(model.getState().toString());
        holder.tvContent.setText(model.getAdvice());

        // 点击事件一般都写在绑定数据这里，当然写到上边的创建布局时候也是可以的
        if (mItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 这里利用回调来给RecyclerView设置点击事件
                    mItemClickListener.onItemClick(position);
                }
            });

            holder.iv_type.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.delete(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return ( Utils.isListEmpty(intheway) ? 0 : intheway.size() );
    }


    class InTheWayHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvContent;
        TextView state;
        ImageView iv_type;
        public InTheWayHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvContent = itemView.findViewById(R.id.tv_content);
            state = itemView.findViewById(R.id.tv_state);
            iv_type = itemView.findViewById(R.id.iv_type);
        }
    }

}

