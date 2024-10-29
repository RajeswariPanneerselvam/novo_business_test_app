package com.example.testapplication;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapplication.adapter.UserRecyclerAdapter;
import com.example.testapplication.model.UsersModel;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HomeFragment extends Fragment {
    public HomeFragment(){
        // require a empty public constructor
    }

    private  ArrayList<UsersModel> usersList;
    private RecyclerView recyclerView;
    private UserRecyclerAdapter userRecyclerAdapter;
    private ProgressDialog progressdialog;
    LinearLayout bankLinearLayout,bankLinearLayoutDetails,btnBankCard,gpayLinearLayout,gpayBtnCard;
    LinearLayout gpayLinearCard,sbiLinearCard,barodaLinearCard;
    TextView bankNameTxt,accountNameTxt;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout= inflater.inflate(R.layout.fragment_home, container, false);

        gpayLinearCard=layout.findViewById(R.id.gpay_card_linear);
        sbiLinearCard=layout.findViewById(R.id.sbi_card_linear);
        barodaLinearCard=layout.findViewById(R.id.baroda_card_linear);


        bankLinearLayout=layout.findViewById(R.id.bank_card_linear);
        bankLinearLayoutDetails=layout.findViewById(R.id.bank_card_linear_deatils);
        btnBankCard=layout.findViewById(R.id.bank_card_btn);
        bankNameTxt=layout.findViewById(R.id.bank_name_txt);

        gpayLinearLayout=layout.findViewById(R.id.gpay_linear);
        gpayBtnCard=layout.findViewById(R.id.gpay_btn);
        accountNameTxt=layout.findViewById(R.id.gpay_txt);



        recyclerView =layout.findViewById(R.id.usersRecyclerView);
        usersList = new ArrayList<>();
        getAllUsers();
        progressdialog = new ProgressDialog(getActivity());
        progressdialog.setMessage("Please Wait....");
        progressdialog.setCancelable(false);

        gpayLinearCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              gpayBtnCard.setVisibility(View.VISIBLE);
              gpayLinearCard.setVisibility(View.VISIBLE);
              accountNameTxt.setVisibility(View.VISIBLE);
              gpayLinearLayout.setVisibility(View.VISIBLE);
            }
        });
        sbiLinearCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gpayBtnCard.setVisibility(View.GONE);
                gpayLinearCard.setVisibility(View.GONE);
                accountNameTxt.setVisibility(View.GONE);
                bankLinearLayout.setVisibility(View.VISIBLE);
                bankLinearLayoutDetails.setVisibility(View.VISIBLE);
                btnBankCard.setVisibility(View.VISIBLE);
                gpayLinearLayout.setVisibility(View.GONE);
                bankNameTxt.setText("State Bank of India");
            }
        });
        barodaLinearCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gpayBtnCard.setVisibility(View.GONE);
                gpayLinearCard.setVisibility(View.GONE);
                accountNameTxt.setVisibility(View.GONE);
                bankLinearLayout.setVisibility(View.VISIBLE);
                bankLinearLayoutDetails.setVisibility(View.VISIBLE);
                btnBankCard.setVisibility(View.VISIBLE);
                gpayLinearLayout.setVisibility(View.GONE);
                bankNameTxt.setText("State Bank of Baroda");
            }
        });
        return layout;
    }

    private void getAllUsers() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface apiInterface=retrofit.create(ApiInterface.class);
        Call<ArrayList<UsersModel>> call = apiInterface.getAllUsers();
        call.enqueue(new Callback<ArrayList<UsersModel>>() {
            @Override
            public void onResponse(Call<ArrayList<UsersModel>> call, Response<ArrayList<UsersModel>> response) {
             if(response.isSuccessful()){
                 usersList=response.body();
//                 Log.i("Response",usersList.get(0).getLogin().toString());
//                 Toast.makeText(getContext(),response.body().toString(),Toast.LENGTH_LONG).show();
                 for ( int i=0;i<usersList.size();i++){
                     userRecyclerAdapter = new UserRecyclerAdapter(usersList,getActivity());
                     LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,true);
                     recyclerView.setLayoutManager(linearLayoutManager);
                     recyclerView.setAdapter(userRecyclerAdapter);
                     progressdialog.dismiss();
                 }

             }
            }

            @Override
            public void onFailure(Call<ArrayList<UsersModel>> call, Throwable throwable) {
                progressdialog.dismiss();
                Toast.makeText(getContext(),"Recent Transaction not found",Toast.LENGTH_SHORT).show();
            }
        });

    }

}
