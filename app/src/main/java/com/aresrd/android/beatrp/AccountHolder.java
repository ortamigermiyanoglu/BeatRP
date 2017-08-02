package com.aresrd.android.beatrp;

import java.math.BigDecimal;

/**
 * Created by Ramadan on 26.12.2016.
 */

public class AccountHolder {
    public long Id;
    public long CompanyId;
    public long OwnerBranchId;
    public int Scope;
    public int AccountHolderType;
    public String AccountHolderCode;
    public int RecordStatus;
    public String SourceSystem;
    public String SourceRecordId;
    public String Name;
    public Float Balance;
    public Float CreditLimit;
    public Float DebitLimit;
    public int CreditLimitOverFlowAction;
    public int DebitLimitOverFlowAction;
}
