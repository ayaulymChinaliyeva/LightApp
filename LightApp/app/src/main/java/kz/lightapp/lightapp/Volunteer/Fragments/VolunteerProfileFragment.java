package kz.lightapp.lightapp.Volunteer.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kz.lightapp.lightapp.R;
import kz.lightapp.lightapp.Volunteer.VolunteersLoginActivity;
import kz.lightapp.lightapp.Volunteer.VolunteersSignUpActivity;

public class VolunteerProfileFragment extends Fragment {

    public VolunteerProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_volunteer_profile, container, false);
        Button signUp = v.findViewById(R.id.signUpButton);
        Button signIn = v.findViewById(R.id.signInButton);
        signUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), VolunteersSignUpActivity.class);
                VolunteerProfileFragment.this.startActivity(intent);
            }
        });
        signIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), VolunteersLoginActivity.class);
                VolunteerProfileFragment.this.startActivity(intent);
            }
        });
        return v;
    }

}

