package com.example.civicgrievance;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class santiadapter extends FirebaseRecyclerAdapter<model,santiadapter.myviewholder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public santiadapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull model model) {
        holder.prblm.setText(model.getProblem());
        holder.desc.setText(model.getDescription());
        holder.addr.setText(model.getAddress());
        holder.phno.setText(model.getMobile_Number());
        holder.dell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.prblm.getContext());
                builder.setTitle("Is this problem is Solved");
                builder.setMessage("Deleted data can't be undo");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Filter").child("SanitizationProblems").child(getRef(holder.getAdapterPosition()).getKey()).removeValue();


                    }
                });
                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.prblm.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();

                    }
                });
                builder.show();
            }
        });
        Glide.with(holder.img1.getContext()).load(model.getImage()).placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark).error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark).into(holder.img1);
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item7,parent,false);
        return new myviewholder(view);
    }

    public static class myviewholder extends RecyclerView.ViewHolder{
        ImageView img1;
        TextView desc,prblm,addr,phno;
        Button dell;


        public myviewholder(@NonNull View itemView) {
            super(itemView);
            dell=itemView.findViewById((R.id.dell));
            img1=itemView.findViewById(R.id.img1);
            desc=itemView.findViewById(R.id.desc);
            prblm=itemView.findViewById(R.id.prblm);
            addr=itemView.findViewById(R.id.addr);
            phno=itemView.findViewById(R.id.phno);
        }
    }
}
