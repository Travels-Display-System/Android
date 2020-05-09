package com.zhao.ui_basic.ui.Edit.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zhao.ui_basic.R;
import com.zhao.ui_basic.Utils.Utils;
import com.zhao.ui_basic.ui.Edit.model.WorkModel;
import com.zhao.ui_basic.ui.main.Model.MainModel;
import com.zhao.ui_basic.ui.main.TravelDetails;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.AlreadyHolder> {
    List<WorkModel> already;
    public MessageAdapter(List<WorkModel> a){
        this.already = a;
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
    public AlreadyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new AlreadyHolder(inflater.inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AlreadyHolder holder1, int position) {
            if(!Utils.isListEmpty(already)){
                WorkModel model = already.get(position);
                holder1.tvContent.setText(model.getTimestamp());
                holder1.tvName.setText(model.getTitle());

                // 点击事件一般都写在绑定数据这里，当然写到上边的创建布局时候也是可以的
                if (mItemClickListener != null){
                    holder1.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // 这里利用回调来给RecyclerView设置点击事件
                            mItemClickListener.onItemClick(position);
                        }
                    });
                    holder1.iv_type.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mItemClickListener.delete(position);
                        }
                    });
                }

            }
    }

    @Override
    public int getItemCount() {
        return Utils.isListEmpty(already) ? 0 : already.size();
    }



    class AlreadyHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvContent;
        ImageView iv_photo;
        ImageView iv_type;
        public AlreadyHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvContent = itemView.findViewById(R.id.tv_content);
            iv_photo = itemView.findViewById(R.id.iv_photo);
            iv_type = itemView.findViewById(R.id.iv_type);
        }
    }

}

