package com.meiliinc.mls.jta;

import javax.transaction.xa.Xid;

public class MyXid implements Xid {
    int formatId;  
    byte globalTransactionId[];  
    byte branchQualifier[];  
  
    public MyXid() {  
  
    }  
  
    public MyXid(int formatId, byte[] globalTransactionId, byte[] branchQualifier) {  
        this.formatId = formatId;  
        this.globalTransactionId = globalTransactionId;  
        this.branchQualifier = branchQualifier;  
    }  
  
    public int getFormatId() {  
        return this.formatId;  
    }  
  
    public void setFormatId(int formatId) {  
        this.formatId = formatId;  
    }  
  
    public byte[] getGlobalTransactionId() {  
        return this.globalTransactionId;  
    }  
  
    public void setGlobalTransactionId(byte[] globalTransactionId) {  
        this.globalTransactionId = globalTransactionId;  
    }  
  
    public byte[] getBranchQualifier() {  
        return this.branchQualifier;  
    }  
  
    public void setBranchQualifier(byte[] branchQualifier) {  
        this.branchQualifier = branchQualifier;  
    }  
}  