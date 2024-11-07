package com.bankaio.Bankaio.Model;

import java.util.Date;

public record AccountDto(Long id, Double balance, String Type, String currency, Date createdAt, String Status) {
}
