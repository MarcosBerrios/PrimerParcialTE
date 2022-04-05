package com.emergente.controlador;

import com.emergente.modelo.productos;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses = request.getSession();
        
        if (ses.getAttribute("listaprod")==null) {
            ArrayList<productos> listaux = new ArrayList<productos>();
            ses.setAttribute("listaprod", listaux);
        }
        
        ArrayList<productos> lista = (ArrayList<productos>) ses.getAttribute("listaprod");

        String op = request.getParameter("op");
        String opcion = (op != null) ? op : "view";

        productos obj1 = new productos();
        int id, pos;

        switch (opcion) {
            case "nuevo":
                request.setAttribute("miProducto", obj1);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                break;
            case "editar":
                id = Integer.parseInt(request.getParameter("id"));
                pos = buscarIndice(request, id);
                obj1 = lista.get(pos);
                request.setAttribute("miProducto", obj1);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                break;
            case "eliminar":
                id = Integer.parseInt(request.getParameter("id"));
                pos = buscarIndice(request, id);
                lista.remove(pos);
                ses.setAttribute("listaprod", lista);
                response.sendRedirect("index.jsp");
                break;
            case "view":
                response.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                HttpSession ses = request.getSession();
        ArrayList<productos> lista = (ArrayList<productos>) ses.getAttribute("listaprod");

        productos obj1 = new productos();

        obj1.setId(Integer.parseInt(request.getParameter("id")));
        obj1.setDescripcion(request.getParameter("descripcion"));
        obj1.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
        obj1.setPrecio(Integer.parseInt(request.getParameter("precio")));
        obj1.setCategoria(request.getParameter("categoria"));

        int idt = obj1.getId();

        if (idt == 0) {
            //nuevo
            int ultID;
            ultID = ultimoId(request);
            obj1.setId(ultID);
            lista.add(obj1);
        } else {
            //edicion
            lista.set(buscarIndice(request, idt), obj1);
        }
        ses.setAttribute("listaprod", lista);
        response.sendRedirect("index.jsp");
    
    }
    private int buscarIndice(HttpServletRequest request, int id) {
        HttpSession ses = request.getSession();
        ArrayList<productos> lista = (ArrayList<productos>) ses.getAttribute("listaprod");

        int i = 0;
        if (lista.size() > 0) {
            while (i < lista.size()) {
                if (lista.get(i).getId() == id) {
                    break;
                } else {
                    i++;
                }
            }
        }
        return i;
    }

    private int ultimoId(HttpServletRequest request) {
        HttpSession ses = request.getSession();
        ArrayList<productos> lista = (ArrayList<productos>) ses.getAttribute("listaprod");

        int idaux = 0;
        for (productos item : lista) {
            idaux = item.getId();
        }
        return idaux + 1;
    }
}
