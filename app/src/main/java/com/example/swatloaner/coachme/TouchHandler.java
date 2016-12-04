package com.example.swatloaner.coachme;

import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
/**
 * Created by Kirk on 12/2/2016.
 */

public class TouchHandler implements View.OnTouchListener {

    Field_Draw activity;
    int num = 1;
    GestureDetectorCompat gestureDetectorCompat;
    boolean isPressing = false;
    boolean reversing = false;

    public TouchHandler(Field_Draw field_draw) {
        this.activity = field_draw;
        gestureDetectorCompat = new GestureDetectorCompat(this.activity, new MyGestureListener());
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int maskedAction = motionEvent.getActionMasked();
        gestureDetectorCompat.onTouchEvent(motionEvent);
        switch (maskedAction) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                if (motionEvent.getPointerCount() < 2) {
                    isPressing = true;
                    activity.addNewPath(num, motionEvent.getX(0), motionEvent.getY(0));
                } else {
                    reversing = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                activity.updatePath(num, motionEvent.getX(0), motionEvent.getY(0));
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL:
                if (motionEvent.getPointerCount() == 1) {
                    if (!reversing) {
                        num++;
                        isPressing = false;
                    } else {
                        reversing = false;
                    }
                } else {
                    reversing = true;
                }
                break;
        }
        return true;
    }

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;
        @Override
        public void onLongPress(MotionEvent e) {
            //bonus
            super.onLongPress(e);
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            //add fragment to choose team members to put on the field
            activity.doubleTap(e.getX(), e.getY());
            return super.onDoubleTap(e);
        }
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            boolean result = false;
            try {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            onSwipeRight();
                        } else {
                            onSwipeLeft();
                        }
                    }
                    result = true;
                }
                else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY > 0) {
                        onSwipeBottom();
                    } else {
                        onSwipeTop();
                    }
                }
                result = true;

            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return result;
        }

        private void onSwipeLeft() {
            //get the drag abble players!!!!

        }

        private void onSwipeBottom() {

        }

        private void onSwipeTop() {

        }

        private void onSwipeRight() {

        }
    }

}
