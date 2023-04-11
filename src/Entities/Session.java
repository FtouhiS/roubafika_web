/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author moham
 */
public class Session {
    private static Utilisateur currentUser;

    public static void startSession(Utilisateur user) {
        currentUser = user;
    }

    public static void endSession() {
        currentUser = null;
    }

    public static Utilisateur getCurrentUser() {
        return currentUser;
    }

    public static boolean isLoggedIn() {
        return currentUser != null;
    }
}