package com.sohaibkhan.digitallibrary;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

import static com.sohaibkhan.digitallibrary.BookDetailActivity.BOOK_ID_KEY;

public class AllBookRecViewAdapter extends RecyclerView.Adapter<AllBookRecViewAdapter.ViewHolder> {

    private ArrayList<Book> bookList = new ArrayList<>();
    private Context context;
    private String contextName;

    public AllBookRecViewAdapter(Context context, String contextName) {
        this.context = context;
        this.contextName = contextName;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Glide.with(context)
                .asBitmap()
                .load(bookList.get(position).getImageURL())
                .into(holder.bookCover);

        holder.bookTitle.setText(bookList.get(position).getTitle());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BookDetailActivity.class);
                intent.putExtra(BOOK_ID_KEY, bookList.get(position).getId());
                context.startActivity(intent);
            }
        });

        holder.author.setText(bookList.get(position).getAuthor());
        holder.shortDesc.setText(bookList.get(position).getShortDesc());

        holder.action_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(holder.parent);
                holder.relativeLayoutLower.setVisibility(View.VISIBLE);
                holder.action_down.setVisibility(View.GONE);

                // managing all context except "allBook"
                if (!contextName.equals("allBook")) {
                    holder.delete.setVisibility(View.VISIBLE);
                    holder.delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            builder.setMessage("Are you sure want to remove " + bookList.get(position).getTitle());
                            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (Utils.getInstance(context).delete(bookList.get(position), contextName)) {
                                        notifyDataSetChanged();
                                        Toast.makeText(context, "Removed successfully", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            builder.create().show();
                        }
                    });
                }

            }
        });

        holder.action_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.action_down.setVisibility(View.VISIBLE);
                holder.relativeLayoutLower.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public void setBookList(ArrayList<Book> bookList) {
        this.bookList = bookList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private MaterialCardView parent;
        private ImageView bookCover, action_down, action_up;
        private TextView bookTitle, author, shortDesc, delete;
        private RelativeLayout relativeLayoutLower;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            bookCover = itemView.findViewById(R.id.book_cover);
            bookTitle = itemView.findViewById(R.id.book_title);

            action_down = itemView.findViewById(R.id.action_down);
            action_up = itemView.findViewById(R.id.action_up);
            relativeLayoutLower = itemView.findViewById(R.id.relLayout_lower);
            author = itemView.findViewById(R.id.author);
            shortDesc = itemView.findViewById(R.id.short_desc);

            delete = itemView.findViewById(R.id.delete);

        }
    }
}
