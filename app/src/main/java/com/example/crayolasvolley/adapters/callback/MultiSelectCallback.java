package com.example.crayolasvolley.adapters.callback;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.example.crayolasvolley.clases.ClsAlumnos;

import java.util.List;

public class MultiSelectCallback   extends DiffUtil.Callback {

    private List<ClsAlumnos> oldList;
    private List<ClsAlumnos> newList;

    public MultiSelectCallback(List<ClsAlumnos> oldList, List<ClsAlumnos> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition) == newList.get(newItemPosition);
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        ClsAlumnos oldItem = oldList.get(oldItemPosition);
        ClsAlumnos newItem = newList.get(newItemPosition);

        return oldItem.getNombre_alumno().equals(newItem.getNombre_alumno());
    }
    @Nullable
    @Override public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
