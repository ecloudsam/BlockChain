package com.company;

import java.util.ArrayList;
import com.google.gson.GsonBuilder;

public class BlockChain{

    public static ArrayList<Block> blockChain = new ArrayList<>();
    public static int zeros = 4;

    public static void main(String[] args) {

        blockChain.add(new Block("This is the block 0", "0"));
        System.out.println("Block 0 mining...");
        blockChain.get(0).blockMine(zeros);

        blockChain.add(new Block("This is the block 1", blockChain.get(blockChain.size()-1).curHash));
        System.out.println("Block 1 mining...");
        blockChain.get(1).blockMine(zeros);

        blockChain.add(new Block("This is the block 2", blockChain.get(blockChain.size()-1).curHash));
        System.out.println("Block 2 mining...");
        blockChain.get(2).blockMine(zeros);

        System.out.println("\nIs Blockchain Valid？ " + isBlockChainValid() + "\n");

        String blockChainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockChain);
        System.out.println(blockChainJson);
    }

    public static Boolean isBlockChainValid() {

        Block curBlock;
        Block preBlock;
        String targetHash = new String(new char[zeros]).replace('\0', '0');

        for(int i=1; i < blockChain.size(); i++) {
            curBlock = blockChain.get(i);
            preBlock = blockChain.get(i-1);
            if(!curBlock.curHash.equals(curBlock.calHash()) ){
                System.out.println("Current Hash Does Not Match");
                return false;
            }
            if(!preBlock.curHash.equals(curBlock.preHash) ) {
                System.out.println("Previous Hash Does Not Match");
                return false;
            }
            if(!curBlock.curHash.substring(0, zeros).equals(targetHash)) {
                System.out.println("This block hasn't been mined");
                return false;
            }
        }
        return true;
    }
}

