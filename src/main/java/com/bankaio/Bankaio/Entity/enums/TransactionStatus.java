package com.bankaio.Bankaio.Entity.enums;

public enum TransactionStatus {
    PENDING,     // The transaction is initiated but not yet completed
    COMPLETED,   // The transaction is successfully completed
    FAILED,      // The transaction failed due to some issue (e.g., insufficient funds)
    CANCELLED    // The transaction was cancelled by the user or the system
}
