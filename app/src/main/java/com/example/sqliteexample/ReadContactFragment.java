package com.example.sqliteexample;


import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReadContactFragment extends Fragment implements View.OnClickListener{
    private Button BnSave,BnView,BnDelete,BnUpdate;
    OnDbOpListener dbOpListener;


    public TextView tvDisplay;

    public ReadContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_read_contact, container, false);
        BnSave= view.findViewById(R.id.bn_add_contact);
        BnSave.setOnClickListener(this);
        BnUpdate=view.findViewById(R.id.bn_update_contact);
        BnUpdate.setOnClickListener(this);
        BnDelete=view.findViewById(R.id.bn_delete_contact);
        BnDelete.setOnClickListener(this);
        tvDisplay=view.findViewById(R.id.tv_read_contact);

        ContactDbHelper contactDbHelper =new ContactDbHelper(getActivity());
        SQLiteDatabase database =contactDbHelper.getReadableDatabase();
        Cursor cursor = contactDbHelper.readContacts(database);
        String  info= "";
        while(cursor.moveToNext()) {
            String id = Integer.toString(cursor.getInt(cursor.getColumnIndex(ContactContract.ContactEntry.CONTACT_ID)));
            String name = cursor.getString(cursor.getColumnIndex(ContactContract.ContactEntry.NAME));
            String email = cursor.getString(cursor.getColumnIndex(ContactContract.ContactEntry.EMAIL));
            info = info + "\n\n" + "id: " + id + "\nname: " + name + "\nemail: " + email;
        }
        tvDisplay.setText(info);
        contactDbHelper.close();
        return view;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bn_add_contact:
                dbOpListener.dbOpPerformed(0);
                break;
//            case R.id.bn_view_contact:
//                dbOpListener.dbOpPerformed(1);
//                break;
            case R.id.bn_update_contact:
                dbOpListener.dbOpPerformed(2);
                break;
            case R.id.bn_delete_contact:
                dbOpListener.dbOpPerformed(3);
                break;
        }
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




