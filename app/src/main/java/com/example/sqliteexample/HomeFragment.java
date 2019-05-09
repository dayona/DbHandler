package com.example.sqliteexample;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    private Button BnSave,BnView,BnDelete,BnUpdate;
    OnDbOpListener dbOpListener;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bn_add_contact:
                dbOpListener.dbOpPerformed(0);
                break;
            case R.id.bn_view_contact:
                dbOpListener.dbOpPerformed(1);
                break;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        BnSave= view.findViewById(R.id.bn_add_contact);
        BnSave.setOnClickListener(this);
        BnView=view.findViewById(R.id.bn_view_contact);
        BnView.setOnClickListener(this);
        BnUpdate=view.findViewById(R.id.bn_update_contact);
        BnUpdate.setOnClickListener(this);
        BnDelete=view.findViewById(R.id.bn_delete_contact);
        BnDelete.setOnClickListener(this);
        return view;
    }



    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        Activity activity=(Activity) context;
        dbOpListener=(OnDbOpListener) activity;

    }

}
