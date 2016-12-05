package com.example.swatloaner.coachme;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Field_Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Field_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Field_Fragment extends Fragment implements  View.OnClickListener{
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

        pallet = (ImageButton) view.findViewById(R.id.pallet);
        draw = (ImageButton) view.findViewById(R.id.draw);
        eraser = (ImageButton) view.findViewById(R.id.eraser);
        clear = (ImageButton) view.findViewById(R.id.clear);

        minorButtons.add(draw);
        minorButtons.add(eraser);
        minorButtons.add(clear);

        pallet.setOnClickListener(this);
        draw.setOnClickListener(this);
        eraser.setOnClickListener(this);
        clear.setOnClickListener(this);

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
            }
        }
    }

    public void PromptDelete() {
        LayoutInflater li = LayoutInflater.from(getContext());
        View promptsView = li.inflate(R.layout.prompt_field, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                getContext());

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("everything",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                //delete everything method
                                dialog.cancel();
                            }
                        })
                .setNegativeButton("just Drawings",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                //clear method for canvas
                                myCanvas.clear();
                                dialog.cancel();
                            }
                        });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();


    }
     ListView listView;
    public void promptAddPlayer(float x, float y)
    {
        LayoutInflater li = LayoutInflater.from(getContext());

        View promptsView = li.inflate(R.layout.prompt_field, null);

        String[] team = {"Kirk", "Joe", "Puyush", "Enrique"};
        final ArrayAdapter adapter = new ArrayAdapter<String>(getContext(), R.layout.add_player_item, team);
        final String chosenstring = "";

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //save the name to chosenstring
            }
        });
        //for(int i = 0; i < ListView. )
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                getContext());

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Team",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // put the player on the field
                                dialog.cancel();
                            }
                        })
                .setNegativeButton("Oponent",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // put the player on the field
                                dialog.cancel();
                            }
                        });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();

    }
    public void doubleTap(float x, float y) {
        if (!isDrawing && eraser.getVisibility() == View.INVISIBLE) {
            promptAddPlayer(x, y);

        }
    }
}
