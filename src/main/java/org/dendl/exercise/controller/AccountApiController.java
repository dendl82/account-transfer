package org.dendl.exercise.controller;

import io.javalin.http.Context;
import org.dendl.exercise.dao.Account;
import org.dendl.exercise.model.BankServiceContainer;
import org.dendl.exercise.model.BankServiceException;

import java.util.Collection;

public class AccountApiController {
    public static void getAllAccounts(Context ctx) {
        Collection<Account> accounts = BankServiceContainer
                .DEFAULT_BANK
                .getBankService()
                .allAccounts();
        ctx.json(accounts);
    }

    public static void createAccount(Context ctx) {
        CreateAccountDto dto = ctx.bodyAsClass(CreateAccountDto.class);
        Account account = BankServiceContainer
                .DEFAULT_BANK
                .getBankService()
                .createAccount(dto.owner, dto.amount);
        dto.id = account.getId();
        ResponseDto responseDto = new ResponseDto(RequestStatus.SUCCESS, null, dto);
        ctx.json(responseDto);
        ctx.status(201);
    }

    public static void makeTransfer(Context ctx) {
        ResponseDto responseDto;
        MakeTransferDto transferDto = ctx.bodyAsClass(MakeTransferDto.class);
        try {
            BankServiceContainer
                    .DEFAULT_BANK
                    .getBankService()
                    .transfer(transferDto.amount, transferDto.fromAccountId, transferDto.toAccountId);
            responseDto = new ResponseDto(RequestStatus.SUCCESS, null, null);
        } catch (BankServiceException e) {
            responseDto = new ResponseDto(RequestStatus.FAILED, e.getMessage(), e.getCause().getMessage());
            ctx.status(500);
        }
        ctx.json(responseDto);
    }
}
