package com.company;

import java.security.PrivateKey;
import java.security.PublicKey;

public class Transaction {
    public String transactionId; // this is also the hash of the transaction.
    public PublicKey sender; // senders address/public key.
    public PublicKey reciepient; // Recipients address/public key.
    public float value;
    public byte[] signature; // this is to prevent anybody else from spending funds in our wallet.

    public ArrayList<TransactionInput> inputs = new ArrayList<TransactionInput>();
    public ArrayList<TransactionOutput> outputs = new ArrayList<TransactionOutput>();

    private static int sequence = 0; // a rough count of how many transactions have been generated.

    public Transaction(PublicKey from, PublicKey to, float value,  ArrayList<TransactionInput> inputs) {
        this.sender = from;
        this.reciepient = to;
        this.value = value;
        this.inputs = inputs;
    }

    private String calulateHash() {
        sequence++;
        return HashFunc.applySha256(
                SignatureFunc.getStringFromKey(sender) +
                        SignatureFunc.getStringFromKey(reciepient) +
                        Float.toString(value) + sequence
        );
    }

    public void generateSignature(PrivateKey privateKey) {
        String data = SignatureFunc.getStringFromKey(sender) + SignatureFunc.getStringFromKey(reciepient) + Float.toString(value)	;
        signature = SignatureFunc.applyECDSASig(privateKey,data);
    }

    public boolean verifiySignature() {
        String data = SignatureFunc.getStringFromKey(sender) + SignatureFunc.getStringFromKey(reciepient) + Float.toString(value)	;
        return SignatureFunc.verifyECDSASig(sender, data, signature);
    }
}
