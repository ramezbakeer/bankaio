package com.bankaio.Bankaio.Entity.enums;

public enum AccountStatus {
    ACTIVE,      // The account is active and operational
    INACTIVE,    // The account is inactive and cannot perform transactions
    CLOSED,      // The account is permanently closed
    SUSPENDED    // The account is temporarily suspended (e.g., due to suspicious activity)
}