package com.example.testapplication;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class BankFragment extends Fragment {
    public BankFragment(){
        // require a empty public constructor
    }

    TabLayout tabLayout;
    ViewPager viewPager;
    ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bank,container, false);
        imageView = view.findViewById(R.id.back);
        // assign variable
        tabLayout=view.findViewById(R.id.tab_layout);
        viewPager=view.findViewById(R.id.view_pager);
        // Initialize array list
        ArrayList<String> arrayList=new ArrayList<>(0);

        // Add title in array list
        arrayList.add("Accounts");
        arrayList.add("Cards");


        // Setup tab layout
        tabLayout.setupWithViewPager(viewPager);


        // Prepare view pager
        prepareViewPager(viewPager,arrayList);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainViewsActivity.class);
                startActivity(intent);
            }
        });
        return view;

    }
    private void prepareViewPager(ViewPager viewPager, ArrayList<String> arrayList) {
        // Initialize main adapter
        MainAdapter adapter=new MainAdapter(getActivity().getSupportFragmentManager());

        // Initialize main fragment
        MainFragment mainFragment=new MainFragment();

        // Use for loop
        for(int i=0;i<arrayList.size();i++)
        {
            // Initialize bundle
            Bundle bundle=new Bundle();

            // Put title
            bundle.putString("title",arrayList.get(i));

            // set argument
            mainFragment.setArguments(bundle);

            // Add fragment
            adapter.addFragment(mainFragment,arrayList.get(i));
            mainFragment=new MainFragment();
        }
        // set adapter
        viewPager.setAdapter(adapter);
    }
    private class MainAdapter extends FragmentPagerAdapter {
        // Initialize arrayList
        ArrayList<Fragment> fragmentArrayList= new ArrayList<>();
        ArrayList<String> stringArrayList=new ArrayList<>();



        // Create constructor
        public void addFragment(Fragment fragment,String s)
        {
            // Add fragment
            fragmentArrayList.add(fragment);
            // Add title
            stringArrayList.add(s);
        }

        public MainAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            // return fragment position
            return fragmentArrayList.get(position);
        }

        @Override
        public int getCount() {
            // Return fragment array list size
            return fragmentArrayList.size();
        }
        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {

            // Initialize drawable


            // set bound

            // Initialize spannable image
            SpannableString spannableString=new SpannableString(""+stringArrayList.get(position));

            // Initialize image span


            // Set span


            // return spannable string
            return spannableString;
        }
    }



}




