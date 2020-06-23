package app;

import block.Block;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class Application {
    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static int difficulty = 6;

    public static void main(String[] args) {

//        Block genesisBlock = new Block("Hi im the first block","0");
//        System.out.println("Hash for the 1: " + genesisBlock.hash);
//
//        Block secondBlock = new Block("Yo im the second block",genesisBlock.hash);
//        System.out.println("Hash for the 2: " + secondBlock.hash);
//
//
//        Block thirdBlock = new Block("Hey im the third block",secondBlock.hash);
//        System.out.println("Hash for the 3: " + thirdBlock.hash);

        blockchain.add(new Block("Hi im the first block","0"));
        System.out.println("Trying to Mine Block 1.... ");
        blockchain.get(0).mineBlock(difficulty);

        blockchain.add(new Block("Yo im the second block",blockchain.get(blockchain.size()-1).hash));
        System.out.println("Trying to Mine Block 2.... ");
        blockchain.get(1).mineBlock(difficulty);

        blockchain.add(new Block("Hey im the third block",blockchain.get(blockchain.size()-1).hash));
        System.out.println("Trying to Mine Block 3.... ");
        blockchain.get(2).mineBlock(difficulty);

        System.out.println("\nBlockChain is Valid: " + isChainValid());
        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println(blockchainJson);
    }

    public static Boolean isChainValid(){
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0','0');

        for(int i=1; i<blockchain.size();i++){
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);

            if(!currentBlock.hash.equals(currentBlock.calculateHash())){
                System.out.println("Current Hashes no equal");
                return false;
            }
            if(!previousBlock.hash.equals(currentBlock.previousHash)){
                System.out.println("Previous Hashes not equal");
                return false;
            }
            if(!currentBlock.hash.substring(0,difficulty).equals(hashTarget)){
                System.out.println("This block hasn;t been mined");
                return false;
            }
        }

        return true;
    }
}
