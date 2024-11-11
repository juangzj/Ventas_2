<%-- 
    Document   : header
    Created on : 28/10/2024, 8:50:40 p. m.
    Author     : Usuario 1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <style>

            /* CSS adicional para la tabla */
            .custom-table tbody tr:nth-child(even) {
                background-color: #f9f9f9; /* Color de fondo alternativo para filas */
            }

            .custom-btn {
                font-size: 0.9rem;
                background-color: #007bff; /* Color de fondo del botón */
                color: white; /* Color del texto del botón */
                transition: background-color 0.3s, transform 0.3s; /* Efecto de transición */
                text-decoration: none; /* Quitar el subrayado */
            }

            .custom-btn:hover {
                background-color: #0056b3; /* Color de fondo al pasar el mouse */
                transform: scale(1.05); /* Aumentar ligeramente el tamaño al pasar el mouse */
            }
        </style>
    </head>
    <body>
