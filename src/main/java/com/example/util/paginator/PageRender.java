package com.example.util.paginator;

import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class PageRender<T> {
    private String url;
    private Page<T> page;
    private int totalPaginas;
    private int cantElementosPorPagina;
    private int paginaActual;
    private List<PageItem> pageList;

    public PageRender(String url, Page<T> page) {
        this.url = url;
        this.page = page;
        this.pageList = new ArrayList<>();
        this.cantElementosPorPagina = page.getSize();
        this.totalPaginas = page.getTotalPages();
        this.paginaActual = page.getNumber() + 1;

        int desde;
        int hasta;

        if(totalPaginas <= cantElementosPorPagina){
            desde = 1;
            hasta = totalPaginas;
        }else{
            if(paginaActual <= cantElementosPorPagina / 2){
                desde = 1;
                hasta = cantElementosPorPagina;
            }else if (paginaActual >= totalPaginas - cantElementosPorPagina / 2){
                desde = totalPaginas - cantElementosPorPagina + 1;
                hasta = cantElementosPorPagina;
            }else {
                desde = paginaActual - cantElementosPorPagina / 2;
                hasta = cantElementosPorPagina;
            }
        }

        for (int i = 0; i < hasta; i++) {
            pageList.add(new PageItem(desde + i, paginaActual == desde + 1));
        }
    }

    public String getUrl() {
        return url;
    }

    public int getTotalPaginas() {
        return totalPaginas;
    }

    public int getPaginaActual() {
        return paginaActual;
    }

    public List<PageItem> getPageList() {
        return pageList;
    }

    public boolean isFirst(){
        return page.isFirst();
    }

    public boolean isLast(){
        return page.isLast();
    }

    public boolean hasNext(){
        return page.hasNext();
    }

    public boolean hasPrevious(){
        return page.hasPrevious();
    }
}
