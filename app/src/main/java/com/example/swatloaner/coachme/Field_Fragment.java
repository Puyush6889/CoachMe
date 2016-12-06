package com.example.swatloaner.coachme;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.ButtonBarLayout;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Field_Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Field_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Field_Fragment extends Fragment implements  View.OnClickListener, View.OnTouchListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    MyCanvas myCanvas;
    TouchHandler touchHandler;
    ImageButton pallet;
    ImageButton draw;
    ImageButton eraser;
    ImageButton clear;
    Context cont;
    RelativeLayout whereThingsGo;
    Field_Fragment thi;
    private ViewGroup mRrootLayout;
    ArrayList<DragObject> players;
    User currentUser;
    UserDataBase UDB;
    RelativeLayout popup;
    ListView myListView;
    Button teamButton;
    Button opponentButton;
    LinearLayout linearLayout;
    List<String> list;

    public boolean isDrawing = false;
    boolean isErasing = false;

    ArrayList<ImageButton> minorButtons;

    private OnFragmentInteractionListener mListener;

    public Field_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Field_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Field_Fragment newInstance(String param1, String param2) {
        Field_Fragment fragment = new Field_Fragment();
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

        View view =  inflater.inflate(R.layout.fragment_field_, container, false);

        myCanvas = (MyCanvas) view.findViewById(R.id.soccerField);
        touchHandler = new TouchHandler(this);
        minorButtons = new ArrayList<>();
        myCanvas.setOnTouchListener(touchHandler);
        listView = (ListView) view.findViewById(R.id.playerList);

        popup = (RelativeLayout) view.findViewById(R.id.popupList);
        pallet = (ImageButton) view.findViewById(R.id.pallet);
        draw = (ImageButton) view.findViewById(R.id.draw);
        eraser = (ImageButton) view.findViewById(R.id.eraser);
        clear = (ImageButton) view.findViewById(R.id.clear);
        thi = this;
        mRrootLayout = (ViewGroup) view.findViewById(R.id.fieldRoot);
        players = new ArrayList<>();

        myListView = (ListView) view.findViewById(R.id.listViewField);
        teamButton = (Button) view.findViewById(R.id.teamPick);
        opponentButton = (Button) view.findViewById(R.id.opponentPick);

        linearLayout = (LinearLayout) view.findViewById(R.id.allLayout);

        UDB = ((SoccerDad) getActivity()).getUserDataBase();
        currentUser = ((SoccerDad) getActivity()).getUser();
        list = UDB.getRoster(currentUser.getCurrentTeam());

        minorButtons.add(draw);
        minorButtons.add(eraser);
        minorButtons.add(clear);

        pallet.setOnClickListener(this);
        draw.setOnClickListener(this);
        eraser.setOnClickListener(this);
        clear.setOnClickListener(this);
        teamButton.setOnClickListener(this);
        opponentButton.setOnClickListener(this);

        cont = view.getContext();
        whereThingsGo = (RelativeLayout) view.findViewById(R.id.whereThingsGo);

        return view;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    public void addNewPath(int id, float x, float y) {
        if (isDrawing) {
            myCanvas.addPath(id, x, y);
            if (eraser.getVisibility() == View.VISIBLE) {
                setMinorIconsVisibleExcept(false, draw);
            }
        }
    }

    public void updatePath(int id, float x, float y) {
        if (isDrawing) {
            myCanvas.updatePath(id, x, y);
        }
    }
    public void undo(){
        myCanvas.undo();
    }

    public void setMinorIconsVisible(boolean vis) {
        for (int i = 0; i < minorButtons.size(); i++) {
            if (vis) {
                minorButtons.get(i).setVisibility(View.VISIBLE);
            } else {
                minorButtons.get(i).setVisibility(View.INVISIBLE);
            }
        }
    }

    public void setMinorIconsVisibleExcept(boolean vis, ImageButton other) {
        for (int i = 0; i < minorButtons.size(); i++) {
            if (!minorButtons.get(i).equals(other)) {
                if (vis) {
                    minorButtons.get(i).setVisibility(View.VISIBLE);
                } else {
                    minorButtons.get(i).setVisibility(View.INVISIBLE);
                }
            }
        }
    }
    //when we change color background make the whole image go green or white we might need to change images completely

    @Override
    public void onClick(View view) {
        if (view.getId() == pallet.getId()) { // open the other icons

            if (isDrawing) {
                if (eraser.getVisibility() == View.VISIBLE) { // if undo is visible and drawing, turn it off except pencil
                    setMinorIconsVisibleExcept(false, draw);
                } else { // if undo is invisible and drawing, turn it all visible
                    setMinorIconsVisible(true);
                }
            } else {
                setMinorIconsVisible(draw.getVisibility() == View.INVISIBLE);
            }

        } else {
            if (view.getId() == draw.getId()) { //make other icons invisible
                isDrawing = !isDrawing;
                //TableRow.LayoutParams params = (TableRow.LayoutParams) draw.getLayoutParams();
                //params.height = 100;
                //params.width = 100;
                if (isDrawing) {
                    //draw.setImageResource(R.drawable.pencil2);
                    setMinorIconsVisibleExcept(false, draw);
                } else {
                    //draw.setImageResource(R.drawable.pencil);
                    setMinorIconsVisible(true);
                }
                //draw.setLayoutParams(params);
            } else if (view.getId() == eraser.getId()) {// if we want to implement undo button we can not set it to whiteevery other time
                undo();
            } else if (view.getId() == clear.getId()) {
                PromptDelete();
            } else if (view.getId() == teamButton.getId()) {
                //pick team
                linearLayout.setVisibility(View.INVISIBLE);
                addPlayerToCanvas(0, setPosX, setPosY);
            } else if (view.getId() == opponentButton.getId()) {
                //pick opponent
                linearLayout.setVisibility(View.INVISIBLE);
                addPlayerToCanvas(1, setPosX, setPosY);
            }

        }
    }

    float setPosX = 50;
    float setPosY = 50;


    public void addPlayerToCanvas(int c, float x, float y) {
        DragObject newObj = new DragObject(draw.getContext());
        newObj.changeText(chosenstring);
        newObj.setColor(c);
        float xy = whereThingsGo.getScaleX() / 2;
        newObj.setScaleX(xy);
        newObj.setScaleY(xy);
        newObj.setX(x - 150);
        newObj.setY(y - 150);
        newObj.setTextSize(20);
        newObj.setOnTouchListener(thi);
        whereThingsGo.addView(newObj);
        players.add(newObj);
    }

    public void PromptDelete() {
        LayoutInflater li = LayoutInflater.from(getContext());
        View promptsView = li.inflate(R.layout.prompt_field, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                getContext());

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);
        alertDialogBuilder
                .setCancelable(true)
                .setPositiveButton("everything",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                //delete everything method
                                whereThingsGo.removeAllViews();
                                players.clear();
                                myCanvas.clear();
                                dialog.cancel();
                            }
                        })
                .setNegativeButton("just drawings",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                //clear method for canvas
                                myCanvas.clear();
                                dialog.cancel();
                            }
                        })
                .setNeutralButton("just players",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                //clear method for canvas
                                whereThingsGo.removeAllViews();
                                players.clear();
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();


    }
    ListView listView;
    private String chosenstring = "n/a";
    private int _yDelta;
    private int _xDelta;

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        final int X = (int) event.getRawX();
        final int Y = (int) event.getRawY();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                _xDelta = X - lParams.leftMargin;
                _yDelta = Y - lParams.topMargin;
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view
                        .getLayoutParams();
                layoutParams.leftMargin = X - _xDelta;
                layoutParams.topMargin = Y - _yDelta;
                int a = 250;
                layoutParams.rightMargin = a;
                layoutParams.bottomMargin = a;
                view.setLayoutParams(layoutParams);
                break;
        }
        mRrootLayout.invalidate();
        return true;
    }

    public void promptAddPlayer(float x, float y)
    {
        setPosX = x;
        setPosY = y;

        //load in options

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(), R.layout.add_player_item, list);
        myListView.setAdapter(adapter);
        chosenstring = "";

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                chosenstring = (String) myListView.getItemAtPosition(i);
                TextView tv = (TextView) myListView.getChildAt(i);
                for (int j = 0; j < list.size(); j++) {
                    TextView tv2 = (TextView) myListView.getChildAt(j);
                    tv2.setBackgroundColor(Color.GRAY);
                }
                tv.setBackgroundColor(Color.DKGRAY);
            }
        });

        popup.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.VISIBLE);

    }

    public void doubleTap(float x, float y) {
        if (!isDrawing && eraser.getVisibility() == View.INVISIBLE) {
            promptAddPlayer(x, y);
        }
    }
}
