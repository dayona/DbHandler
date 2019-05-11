package com.example.sqliteexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements ReadContactFragment.OnDbOpListener,AddContactFragment.OnDbOpListener,UpdateContactFragment.OnDbOpListener,
        DeleteContactFragment.OnDbOpListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(findViewById(R.id.fragment_container)!=null){
            if(savedInstanceState!=null){
                return;
            }

            ReadContactFragment readContactFragment= new ReadContactFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,readContactFragment).commit();

        }
    }

    @Override
    public void dbOpPerformed(int method) {
        switch (method){
            case 0:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new AddContactFragment()).addToBackStack(null).commit();
                break;
            case 1:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ReadContactFragment()).addToBackStack(null).commit();
                break;
            case 2:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new UpdateContactFragment()).addToBackStack(null).commit();
                break;
            case 3:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new DeleteContactFragment()).addToBackStack(null).commit();
                break;
        }

    }
}
