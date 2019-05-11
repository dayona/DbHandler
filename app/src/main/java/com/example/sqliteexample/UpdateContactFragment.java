package com.example.sqliteexample;


import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateContactFragment extends Fragment {

    public TextView TvUpddate;
    public EditText EvName, EvId, EvEmail;
    public Button BnUpdate;
    OnDbOpListener dbOpListener;

    public UpdateContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_update_contact, container, false);

        EvId=view.findViewById(R.id.update_contact_id);
        EvName=view.findViewById(R.id.update_contact_name);
        EvEmail=view.findViewById(R.id.update_contact_email);
        BnUpdate=view.findViewById(R.id.bn_update);

        BnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Id= EvId.getText().toString();
                String Name = EvName.getText().toString();
                String Email = EvEmail.getText().toString();

                ContactDbHelper contactDbHelper= new ContactDbHelper(getActivity());
                SQLiteDatabase database = contactDbHelper.getWritableDatabase();
                contactDbHelper.updateContact(Integer.parseInt(Id),Name,Email,database);

                EvId.setText(" ");
                EvName.setText(" ");
                EvEmail.setText(" ");

                dbOpListener.dbOpPerformed(1);


            }
        });


        return view;
    }

    public interface OnDbOpListener{
        public void dbOpPerformed(int method);
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        Activity activity=(Activity) context;
        dbOpListener=(OnDbOpListener) activity;

    }



}
