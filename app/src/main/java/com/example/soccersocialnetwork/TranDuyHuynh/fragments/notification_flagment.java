package com.example.soccersocialnetwork.TranDuyHuynh.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.soccersocialnetwork.R;
import com.example.soccersocialnetwork.TranDuyHuynh.adapter.information_notifications_Adapter;
import com.example.soccersocialnetwork.TranDuyHuynh.models.information_notifications;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link notification_flagment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class notification_flagment extends Fragment {

    private ListView listView;
    ArrayList<information_notifications> information_notifications;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public notification_flagment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment notification_flagment.
     */
    // TODO: Rename and change types and number of parameters
    public static notification_flagment newInstance(String param1, String param2) {
        notification_flagment fragment = new notification_flagment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.notification_flagment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        // goi ham tao du lieu cho list view
        createDataForListView();
        information_notifications_Adapter information_notifications_adapter = new information_notifications_Adapter(getContext(),R.layout.list_notification,information_notifications);
        listView = (ListView) view.findViewById(R.id.lstNotification);
        listView.setAdapter(information_notifications_adapter);

    }

    // tao du lieu gia cho list view
    private void createDataForListView(){
        information_notifications = new ArrayList<>();
        information_notifications.add(new information_notifications(R.drawable.img_team1,"Team 1 mời bạn gia nhập vào đội của họ !"));
        information_notifications.add(new information_notifications(R.drawable.img_team2,"Team 2 mời bạn gia nhập vào đội của họ !"));
        information_notifications.add(new information_notifications(R.drawable.img_team3,"Team 3 mời bạn gia nhập vào đội của họ !"));
        information_notifications.add(new information_notifications(R.drawable.img_team4,"Team 4 mời bạn gia nhập vào đội của họ !"));
        information_notifications.add(new information_notifications(R.drawable.img_team5,"Team 5 mời bạn gia nhập vào đội của họ !"));
        information_notifications.add(new information_notifications(R.drawable.img_team6,"Team 6 mời bạn gia nhập vào đội của họ !"));
    }
}