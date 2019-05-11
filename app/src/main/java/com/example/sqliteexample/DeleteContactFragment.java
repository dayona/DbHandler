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


/**
 * A simple {@link Fragment} subclass.
 */
public class DeleteContactFragment extends Fragment {

        EditText DeleteContact;
        Button BnDelete;
        OnDbOpListener dbOpListener;

    public DeleteContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_delete_contact, container, false);
        DeleteContact=view.findViewById(R.id.delete_contact_id);
        BnDelete=view.findViewById(R.id.bn_delete_contact);
        BnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Contact_id= DeleteContact.getText().toString();
                ContactDbHelper contactDbHelper = new ContactDbHelper(getActivity());
                SQLiteDatabase database= contactDbHelper.getWritableDatabase();
                contactDbHelper.deleteContact(Integer.parseInt(Contact_id),database);
                DeleteContact.setText("");
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
