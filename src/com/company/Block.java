package com.company;

import java.util.Date;

public class Block {
    private String time;
    private String data;
    public String preHash;
    public String curHash;
    private int mine;

    public Block(String data, String preHash) {
        this.time = new Date().toString();
        this.data = data;
        this.preHash = preHash;
        this.curHash = calHash();
    }

    public String calHash() {
        return HashFunc.applySha256(time + data + preHash + Integer.toString(mine));
    }

    public void blockMine(int zeros) {
        String target = new String(new char[zeros]).replace('\0', '0');
        while(!curHash.substring(0, zeros).equals(target)) {
            mine ++;
            curHash = calHash();
        }
        System.out.println("Block Mined: " + curHash);
    }
}
