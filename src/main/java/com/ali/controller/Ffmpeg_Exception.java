package com.ali.controller;

import java.io.PrintStream;

public class Ffmpeg_Exception extends Exception{
    @Override
    public void printStackTrace(PrintStream s) {
        super.printStackTrace(s);
    }
}
