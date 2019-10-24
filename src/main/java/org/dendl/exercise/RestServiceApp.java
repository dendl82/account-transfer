package org.dendl.exercise;

import io.javalin.Javalin;
import org.dendl.exercise.controller.AccountApiController;

import static io.javalin.apibuilder.ApiBuilder.*;

public class RestServiceApp {
    public static void main( String[] args ) {
        Javalin app = Javalin
            .create()
            .routes(() -> {
                path("accounts", () -> {
                    get(AccountApiController::getAllAccounts);
                    post(AccountApiController::createAccount);
                });
                path("transfer", () -> {
                    post(AccountApiController::makeTransfer);
                });
            })
            .start(7000);
    }
}
