package kz.lightapp.lightapp.Organization.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kz.lightapp.lightapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyProjects extends Fragment {


    public MyProjects() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_projects, container, false);
    }

}
