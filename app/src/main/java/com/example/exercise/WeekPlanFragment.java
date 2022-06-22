package com.example.exercise;

import static com.example.exercise.ExerInfoFragment.selectPart;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

import android.os.TestLooperManager;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WeekPlanFragment extends ListFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    ListView listView;
    private ListAdapter listViewAdapter;
    ArrayList<Plan> planArray;
    WeekPlanSystem weekPlanSystem;
    SQLiteDatabase db;
    WeekPlanFragment weekPlanFragment;

    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button delbtn;
    Button savebtn;

    //String strText;
    private FragmentManager fragmentManager;

//    TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7;

    private LinearLayout sList, mList, tList, wList, thList, fList, satList;

    String ID;


    //선택된 요일
    private String selectedDay;

    public static WeekPlanFragment newInstance(String param1, String param2) {
        WeekPlanFragment fragment = new WeekPlanFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_weekplan, container, false);

        //로그인된 데이터를 넘겨줌
       SharedPreferences info= getActivity().getSharedPreferences("Info", Context.MODE_PRIVATE);
        ID=info.getString("userID",null);
        System.out.println(ID);

        planArray=new ArrayList<>();
        weekPlanSystem=new WeekPlanSystem(rootView.getContext());
        db=weekPlanSystem.getWritableDatabase();
        weekPlanSystem.onCreate(db);



        button1 = (Button)rootView.findViewById(R.id.sun);
        button2 = (Button)rootView.findViewById(R.id.mon);
        button3 = (Button)rootView.findViewById(R.id.tue);
        button4 = (Button)rootView.findViewById(R.id.wed);
        button5 = (Button)rootView.findViewById(R.id.thu);
        button6 = (Button)rootView.findViewById(R.id.fri);
        button7 = (Button)rootView.findViewById(R.id.sat);
        delbtn = (Button)rootView.findViewById(R.id.del);
        savebtn = (Button)rootView.findViewById(R.id.save);

        sList=(LinearLayout) rootView.findViewById(R.id.sundayList);
        mList=(LinearLayout) rootView.findViewById(R.id.mondayList);
        tList=(LinearLayout) rootView.findViewById(R.id.tuesdayList);
        wList=(LinearLayout) rootView.findViewById(R.id.wednesdayList);
        thList=(LinearLayout) rootView.findViewById(R.id.thursdayList);
        fList=(LinearLayout) rootView.findViewById(R.id.fridayList);
        satList=(LinearLayout) rootView.findViewById(R.id.saturdayList);

        planArray.clear();
        planArray=weekPlanSystem.callPlan(db,ID);

        for(int i=0;i<planArray.size();i++){
            TextView newTextView = new TextView(getContext());

            //텍스트뷰에 들어갈 내용 설정
            newTextView.setText(planArray.get(i).getExerPartArray());

            newTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            newTextView.setBackgroundColor(Color.parseColor("#FF8B3E"));
            newTextView.setTextColor(Color.parseColor("#FFFFFF"));

            switch (planArray.get(i).getWeekly()){
                case "일":
                    sList.addView(newTextView);
                    break;
                case "월":
                    mList.addView(newTextView);
                    break;
                case "화":
                    tList.addView(newTextView);
                    break;
                case "수":
                    wList.addView(newTextView);
                    break;
                case "목":
                    thList.addView(newTextView);
                    break;
                case "금":
                    fList.addView(newTextView);
                    break;
                case "토":
                    satList.addView(newTextView);
                    break;
                default:
                    Toast.makeText(getActivity(), "운동 추가 에러", Toast.LENGTH_SHORT).show();
            }
        }

        //리스트뷰 초기화
        String[] inivalues=new String[] {};
        ArrayAdapter<String> adapter2=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,inivalues);
        setListAdapter(adapter2);


        String[] values = new String[] {"팔", "어깨", "하체", "가슴", "등"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, values);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final String[] items = {"확인", "취소"};
        builder.setTitle("확인을 누르시면 스케쥴 내용이 전체삭제됩니다.");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which ==0){
                    weekPlanSystem.initTable(db, ID);
                    refreshFg();
                }
            }
        });



        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setListAdapter(adapter);
                selectedDay="일";
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setListAdapter(adapter);
                selectedDay="월";
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setListAdapter(adapter);
                selectedDay="화";
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setListAdapter(adapter);
                selectedDay="수";
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setListAdapter(adapter);
                selectedDay="목";
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setListAdapter(adapter);
                selectedDay="금";
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setListAdapter(adapter);
                selectedDay="토";
            }
        });

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weekPlanSystem.initTable(db, ID);
                for(int i=0;i<planArray.size();i++){
//                    System.out.println("요일= "+planArray.get(i).getWeekly()+" 운동부위= "+planArray.get(i).getExerPartArray());
                    weekPlanSystem.addPlan(db,ID,planArray.get(i));
                }
                String[] values=new String[] {};
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,values);
                setListAdapter(adapter);
//                System.out.println("size="+planArray.size());
            }
        });

        delbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.show();
            }
        });

        return rootView;
    }

    //아이템 클릭 이벤트
    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
//        TextView textView1 = (TextView)v.findViewById(R.id.textS);
        String strText = (String) l.getItemAtPosition(position); //이게 운동 목록
////        String test="";
//        Log.d("Fragment: ", position + ": " +strText);
//        Toast.makeText(this.getContext(), "클릭: " + position +" " + strText, Toast.LENGTH_SHORT).show();

        //텍스트뷰 객체 생성
        TextView newTextView = new TextView(getContext());

        //텍스트뷰에 들어갈 내용 설정
        newTextView.setText(strText);

        //텍스트 중앙정렬
        newTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        newTextView.setBackgroundColor(Color.parseColor("#FF8B3E"));
        newTextView.setTextColor(Color.parseColor("#FFFFFF"));

        //생성 및 설정된 텍스트뷰를 레이아웃에 적용
        switch (selectedDay){
            case "일":
                sList.addView(newTextView);
                Plan sunPlan=new Plan("일", strText);
                planArray.add(sunPlan);
                break;
            case "월":
                mList.addView(newTextView);
                Plan monPlan=new Plan("월", strText);
                planArray.add(monPlan);
                break;
            case "화":
                tList.addView(newTextView);
                Plan tuePlan=new Plan("화", strText);
                planArray.add(tuePlan);
                break;
            case "수":
                wList.addView(newTextView);
                Plan wedPlan=new Plan("수", strText);
                planArray.add(wedPlan);
                break;
            case "목":
                thList.addView(newTextView);
                Plan thuPlan=new Plan("목", strText);
                planArray.add(thuPlan);
                break;
            case "금":
                fList.addView(newTextView);
                Plan friPlan=new Plan("금", strText);
                planArray.add(friPlan);
                break;
            case "토":
                satList.addView(newTextView);
                Plan satPlan=new Plan("토", strText);
                planArray.add(satPlan);
                break;
            default:
                Toast.makeText(getActivity(), "운동 추가 에러", Toast.LENGTH_SHORT).show();
        }
    }


    public void refreshFg(){
        FragmentTransaction ft=getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
    }


}