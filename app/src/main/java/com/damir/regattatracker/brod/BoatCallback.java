package com.damir.regattatracker.brod;

import java.util.ArrayList;

public interface BoatCallback {
    void onGetBoatCallback(ArrayList<Brod> odg, String error);
    void onPostBoatCallback(Brod odg, String error);
}
