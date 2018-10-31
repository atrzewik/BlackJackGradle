package com.trzewik.blackjack;

import java.util.ArrayList;
import java.util.Arrays;

public enum MoveType {
    NONE ("n"),
    STAND ("st"),
    HIT ("h"),
    DOUBLE ("d");

    private final String value;

    MoveType(String value){this.value = value;}

    public String getValue(){return this.value;}

    ArrayList<MoveType> listOfKeys = new ArrayList<>();
    ArrayList<String> listOfValues = new ArrayList<>();

    private ArrayList<MoveType> getListOfKeys(){
        this.listOfKeys.addAll(Arrays.asList(MoveType.values()));
        return this.listOfKeys;
    }

    private ArrayList<MoveType> removeKeyFromKeys(MoveType key, ArrayList<MoveType> keys){
        keys.remove(key);
        return keys;
    }

    public ArrayList<MoveType> keysWithoutNone(){
        return removeKeyFromKeys(MoveType.NONE, this.getListOfKeys());
    }

    public ArrayList<MoveType> keysWithoutDouble(){
        return removeKeyFromKeys(MoveType.DOUBLE, this.keysWithoutNone());
    }

    private ArrayList<String> getListOfValues(){
        for(MoveType key : MoveType.values()){
            this.listOfValues.add(key.getValue());
        }return this.listOfValues;
    }

    private ArrayList<String> removeValueFromValues(String value, ArrayList<String> values){
        values.remove(value);
        return values;
    }

    public ArrayList<String> valuesWithoutNone(){
        return this.removeValueFromValues(MoveType.NONE.getValue(), this.getListOfValues());
    }

    public ArrayList<String> valuesWithoutDouble(){
        return this.removeValueFromValues(MoveType.DOUBLE.getValue(), this.valuesWithoutNone());
    }

    private ArrayList<String> getValuesUpAndLow(ArrayList<String> values){
        ArrayList<String> upperCaseValues = new ArrayList<>();
        for (String value : values){
            upperCaseValues.add(value.toUpperCase());
        }
        values.addAll(upperCaseValues);
        return values;
    }

    public ArrayList<String> valuesWithoutNoneUpAndLow(){
        return this.getValuesUpAndLow(this.valuesWithoutNone());
    }

    public ArrayList<String> valuesWithoutDoubleUpAndLow(){
        return this.getValuesUpAndLow(this.valuesWithoutDouble());
    }

    public ArrayList<String> getValuesWithoutDoubleUpAndLow(){
        return this.valuesWithoutDoubleUpAndLow();
    }
}
