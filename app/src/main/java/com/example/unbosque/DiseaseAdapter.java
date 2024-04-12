package com.example.unbosque;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unbosque.Model.Disease;

import java.util.List;

public class DiseaseAdapter extends RecyclerView.Adapter<DiseaseAdapter.DiseaseViewHolder> {
    private List<Disease> diseases;
    private Context context;

    public DiseaseAdapter(Context context, List<Disease> diseases) {
        this.context = context;
        this.diseases = diseases;
    }

    @NonNull
    @Override
    public DiseaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new DiseaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiseaseViewHolder holder, int position) {
        Disease disease = diseases.get(position);
        holder.itemView.setOnClickListener(v -> {
            // Aquí manejas el clic en un elemento
            Intent intent = new Intent(context, DiseaseDetailActivity.class);
            intent.putExtra("name", disease.getName());
            intent.putExtra("symptoms", disease.getSymptoms());
            intent.putExtra("treatment", disease.getTreatment());
            context.startActivity(intent);
        });
        holder.nameTextView.setText(disease.getName());
    }

    @Override
    public int getItemCount() {
        return diseases.size();
    }

    public static class DiseaseViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;

        public DiseaseViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(android.R.id.text1);
        }
    }
}