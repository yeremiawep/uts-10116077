//02-06-21 / 10116077 / Yeremia Wahyu / akb-ul

package com.example.uts;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the

 * to handle interaction events.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */


public class Daily extends Fragment {
    Button btn;

    public static Daily newInstance() {
        Daily da = new Daily();
        return da;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_daily, container, false);

        btn = (Button) rootView.findViewById(R.id.btnCreate);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(),MainCatatan.class);
            startActivity(intent);
        }
        });
        return rootView;
    }
}



