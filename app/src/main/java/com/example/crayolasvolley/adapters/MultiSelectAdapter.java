package com.example.crayolasvolley.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crayolasvolley.R;
import com.example.crayolasvolley.adapters.callback.MultiSelectCallback;
import com.example.crayolasvolley.clases.ClsAlumnos;
import com.example.crayolasvolley.clases.ClsProfesores;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.refactor.library.SmoothCheckBox;

public class MultiSelectAdapter extends RecyclerView.Adapter<MultiSelectAdapter.MultiSelectViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<ClsAlumnos> personItemList;
    private Set<Integer> selectedItemsList;
    private CallbackManager callbackManager;


    public MultiSelectAdapter(ArrayList<ClsAlumnos> personItemList,Context context, CallbackManager callbackManager) {
        this.context = context;
        this.callbackManager = callbackManager;
        inflater = LayoutInflater.from(context);
      this.personItemList = personItemList;
        selectedItemsList = new HashSet<>();
    }


    public void notifyAdapter(List<ClsAlumnos> newList) {
        MultiSelectCallback callback = new MultiSelectCallback(personItemList, newList);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(callback);

        this.personItemList.clear();
        this.personItemList.addAll(newList);
        diffResult.dispatchUpdatesTo(this);
    }

    public interface CallbackManager {
        void onSelectOrDeSelectAll(boolean isAllSelected, boolean isFromAdapter);
    }

    @NonNull
    @Override
    public MultiSelectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_multi_select, parent, false);
        return new MultiSelectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MultiSelectViewHolder holder, int position) {
        ClsAlumnos item = personItemList.get(position);
        holder.bindDataToView(item, position);
    }

    @Override
    public int getItemCount() {
        return personItemList.size();
    }

    public void selectAllItems() {
        if (personItemList == null) return;

        for (int i = 0, personItemListSize = personItemList.size(); i < personItemListSize; i++) {
            ClsAlumnos item = personItemList.get(i);

            if (!item.isSelected()) item.setSelected(true);

            if (!selectedItemsList.contains(item.getId_alumno())) {
                selectedItemsList.add(item.getId_alumno());
            }
        }
        notifyDataSetChanged();
    }



    public class MultiSelectViewHolder extends RecyclerView.ViewHolder {

        //@BindView(R.id.tv_person_name)
        TextView tvPersonName;
        //@BindView(R.id.tv_person_mobile)
        TextView tvPersonMobile;
      //  @BindView(R.id.cb_person)
        CheckBox cbPerson;
        SmoothCheckBox  scb;


        public MultiSelectViewHolder(@NonNull View itemView) {
            super(itemView);

            tvPersonName=(TextView)itemView.findViewById(R.id.tv_person_name);
            tvPersonMobile=(TextView)itemView.findViewById(R.id.tv_person_mobile);
           // cbPerson=(CheckBox)itemView.findViewById(R.id.cb_person);
            scb= (SmoothCheckBox) itemView.findViewById(R.id.cb_person);
        }

        public void bindDataToView(final ClsAlumnos item, final int position) {
           tvPersonName.setText(item.getNombre_alumno());
            tvPersonMobile.setText(item.getApellido_alumno());

            scb.setChecked(item.isSelected());
            scb.setTag(position);
            scb.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View view) {

                    CheckBox checkBox = (CheckBox) view;

                    SmoothCheckBox che1=(SmoothCheckBox)view;

                    int clickedPosition = (int) che1.getTag();
                    boolean isChecked = che1.isChecked();

                    if (isChecked) {
                        if (!selectedItemsList.contains(item.getId_alumno())) {
                            selectedItemsList.add(item.getId_alumno());
                        }
                    } else {
                        if (selectedItemsList.contains(item.getId_alumno())) {
                            selectedItemsList.remove(item.getId_alumno());
                        }
                    }

                    personItemList.get(clickedPosition).setSelected(isChecked ? true : false);

                    if (personItemList.size() == selectedItemsList.size()) {
                        if (callbackManager != null) callbackManager.onSelectOrDeSelectAll(true, true);
                    } else {
                        if (callbackManager != null) callbackManager.onSelectOrDeSelectAll(false, true);
                    }
                }
            });
        }

    }
}
