package org.dendl.exercise.controller;

import java.math.BigDecimal;

public final class MakeTransferDto {
    public int fromAccountId;
    public int toAccountId;
    public BigDecimal amount;
}
