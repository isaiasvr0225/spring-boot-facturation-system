package com.example.util.paginator;

public class PageItem {
    private int numero;
    private boolean isActualPage;

    public PageItem(int numero, boolean isActualPage) {
        this.numero = numero;
        this.isActualPage = isActualPage;
    }

    public int getNumero() {
        return numero;
    }

    public boolean isActualPage() {
        return isActualPage;
    }
}
