package com.example.exercise;

import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ExerInfoFragment extends Fragment implements OnItemClick{
    public static final String Tag="ExerInfoFragment Tag";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    // 0 == 팔, 1 == 어깨, 2 == 하체, 3 == 가슴, 4 ==등
    public static String selectPart="팔";

    private RecyclerAdapter adapter;
    ListView exerNameListView;  //운동 이름 리스트뷰
    private ListAdapter listViewAdapter;
    ArrayList<ExerInfo> exerInfoArray;
    AlertDialog exerInfoAlertDialog;

    ExerInfoSystem edb;

    ExerInfoSystem exerInfoSystem;
    public ExerInfoFragment() {

    }


    public static ExerInfoFragment newInstance(String param1, String param2) {
        ExerInfoFragment fragment = new ExerInfoFragment();
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
        View v=inflater.inflate(R.layout.fragment_exer_info,container,false);

        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recycleView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        adapter = new RecyclerAdapter(this::onClick);
        recyclerView.setAdapter(adapter);


        getExerList();

        adapter.notifyDataSetChanged();
        exerInfoArray=new ArrayList<>();
        edb=new ExerInfoSystem(getActivity().getApplicationContext());
        SQLiteDatabase db;
        db=edb.getReadableDatabase();
//        edb.onCreate(db);
//        String[] exerNames=edb.callInfoName(db);
//        for(int i=0;i<exerNames.length;i++)
//             .add(new ExerInfo(exerNames[i],edb.callPic(db, exerNames[i]),edb.callHow(db,exerNames[i])));
        String sql="SELECT * FROM exerInfo";
        Cursor cursor=db.rawQuery(sql, null);
        while(cursor.moveToNext()){
            //프래그먼트에 데이터베이스의 운동 정보들을 받아오는 곳!
            exerInfoArray.add(new ExerInfo(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
        }
        db.close();


        exerNameListView=(ListView) v.findViewById(R.id.exerNameListView);
        exerNameListView.setAdapter(listViewAdapter);   //리스트뷰 어댑터에 exerInfo 배열들 저장해서 리스트뷰로 출력해주는 부분
        onClick(selectPart); //처음에 '팔'을 불러서 팔에 대한 리스트뷰를 먼저 출력함
        exerNameListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {  //리스트 뷰를 클릭했을 경우, 상세 정보가 출력됨
                String selectedItem = (String) view.findViewById(R.id.exerName).getTag().toString();
                Toast.makeText(getContext(), "Clicked: " + position +" " + selectedItem, Toast.LENGTH_SHORT).show();

                AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
                builder.setTitle(selectedItem).setMessage(exerInfoArray.get(position).getExerHow());

                exerInfoAlertDialog=builder.create();
                exerInfoAlertDialog.show();

            }
        });

        return v;
    }

    public void getExerList() { //운동 부위 리스트 불러오는 함수
        List<String> exerPartArray = Arrays.asList("팔","어깨","하체","가슴","등");


        List<Integer> listResId = Arrays.asList(
                R.mipmap.arm_round,
                R.mipmap.shoulder_round,
                R.mipmap.lower_round,
                R.mipmap.chest_round,
                R.mipmap.back_round
        );
        for (int i = 0; i < exerPartArray.size(); i++) {
            ExerPart exerPart = new ExerPart(exerPartArray.get(i));
            exerPart.setExerPartName(exerPartArray.get(i));
            exerPart.setResId(listResId.get(i));

            adapter.addItem(exerPart);

        }

        // adapter의 값이 변경되었다는 것을 알려줍니다.
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(String selectPart) {
        this.selectPart=selectPart;
        System.out.println("selectPart="+selectPart);
        ArrayList<ExerInfo> partExer=new ArrayList<>();
        // 0 == 팔, 1 == 어깨, 2 == 하체, 3 == 가슴, 4 ==등
        for(int i=0;i<exerInfoArray.size();i++){
            if(selectPart.equals(exerInfoArray.get(i).getPartName())){
                partExer.add(exerInfoArray.get(i));
            }
        }
        //선택된 운동에 따라서 보여줌

        listViewAdapter=new CustomAdapter(getContext(), partExer);
        exerNameListView.setAdapter(listViewAdapter);
    }
}