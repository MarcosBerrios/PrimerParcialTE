<%@page import="java.util.ArrayList"%>
<%@page import="com.emergente.modelo.productos"%>
<%
    ArrayList <productos> lista = (ArrayList<productos>) session.getAttribute("listaprod");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table border="2">
            <TH>
                <h1>PRIMER PARCIAL TEM-742</h1>
                <h2>NOMBRE: MARCOS ALEJANDRO BERRIOS PANCATA</h2>
                <h2>CARNET: 9933303 L.P.</h2>
            </TH>
        </table>
        <h1>PRODUCTOS</h1>
        <a href="MainController?op=nuevo">Nuevo registro</a>
        <table border="1">
            <tr>
                <th>Id</th>
                <th>Descripcion</th>
                <th>Cantidad</th>
                <th>Precio</th>
                <th>Categoria</th>
                <th>Editar</th>
                <th>Eliminar</th>
            </tr>
            <%
                if (lista != null) {
                    for (productos item : lista) {
            %>
            <tr>
                <td><%= item.getId()%></td>
                <td><%= item.getDescripcion()%></td>
                <td><%= item.getCantidad()%></td>
                <td><%= item.getPrecio()%></td>
                <td><%= item.getCategoria()%></td>
                <td><a href="MainController?op=editar&id=<%= item.getId()%>">Modificar</a></td>
                <td><a href="MainController?op=eliminar&id=<%= item.getId()%>"
                       onclick="return confirm('Esta seguro de Eliminar el Registro?')">Eliminar</a></td>
            </tr>
            <%
                    }
                }
            %>
        </table>
    </body>
</html>
