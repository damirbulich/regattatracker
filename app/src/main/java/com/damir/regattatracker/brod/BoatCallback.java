package com.damir.regattatracker.brod;

import java.util.ArrayList;

public interface BoatCallback {
    void onBoatCallback(ArrayList<Brod> odg, String error);
}
