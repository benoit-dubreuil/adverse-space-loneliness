package com.adversespaceloneliness.game.assets;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        System.out.println("Assets' main called");
        System.out.println( Arrays.toString(FileUtils.listFiles(new File("assets/"), null, true).toArray()));
    }
}