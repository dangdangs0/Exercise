package com.example.exercise;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder> {

    // adapter에 들어갈 list 입니다.
    private ArrayList<ExerPart> listData = new ArrayList<>();

    private OnItemClick myCallback;

    RecyclerAdapter(OnItemClick listener){
        this.myCallback=listener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate 시킵니다.
        // return 인자는 ViewHolder 입니다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exerpart, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        // Item을 하나, 하나 보여주는(bind 되는) 함수입니다.
        holder.onBind(listData.get(position));
    }

    @Override
    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.
        return listData.size();
    }

    void addItem(ExerPart exerPart) {
        // 외부에서 item을 추가시킬 함수입니다.
        listData.add(exerPart);
    }

    // RecyclerView의 핵심인 ViewHolder 입니다.
    // 여기서 subView를 setting 해줍니다.
    class ItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView exerPartPic;
//        private ExerList exerList;
//        private String[] exerPartName;

        ItemViewHolder(View itemView) {
            super(itemView);

            exerPartPic = itemView.findViewById(R.id.exerPartPic);

//            final RecyclerAdapter.ItemViewHolder holder=(RecyclerView.ViewHolder)
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=getAdapterPosition();
//                    exerPartName[pos]=exerList.getExerPartArray().toString();
                    if(pos!=RecyclerView.NO_POSITION){
                        System.out.println(pos+"선택됨");
                        switch (pos){
                            case 0:
                                myCallback.onClick("팔");
                                break;
                            case 1:
                                myCallback.onClick("어깨");
                                break;
                            case 2:
                                myCallback.onClick("하체");
                                break;
                            case 3:
                                myCallback.onClick("가슴");
                                break;
                            case 4:
                                myCallback.onClick("등");
                                break;
                            default:
                                break;
                        }
                    }
                }
            });
        }

        void onBind(ExerPart exerPart) {
            exerPartPic.setImageResource(exerPart.getResId());
        }
    }

}