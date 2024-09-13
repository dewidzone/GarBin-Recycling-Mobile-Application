package com.example.garbin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FAQAdapter extends RecyclerView.Adapter<FAQAdapter.FAQViewHolder> {
    private List<FAQItem> faqList;

    public FAQAdapter(List<FAQItem> faqList) {
        this.faqList = faqList;
    }

    @NonNull
    @Override
    public FAQViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.faq_item, parent, false);
        return new FAQViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FAQViewHolder holder, int position) {
        FAQItem faqItem = faqList.get(position);
        holder.faqQuestionTextView.setText(faqItem.getQuestion());
        holder.faqAnswerTextView.setText(faqItem.getAnswer());
    }

    @Override
    public int getItemCount() {
        return faqList.size();
    }

    public static class FAQViewHolder extends RecyclerView.ViewHolder {
        public TextView faqQuestionTextView;
        public TextView faqAnswerTextView;

        public FAQViewHolder(View view) {
            super(view);
            faqQuestionTextView = view.findViewById(R.id.faqQuestionTextView);
            faqAnswerTextView = view.findViewById(R.id.faqAnswerTextView);
        }
    }
}
