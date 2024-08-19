-- Consultar todos los proyectos de un cliente
SELECT P.nombre AS Proyecto, P.descripcion, E.nombre AS Estado
FROM Proyectos P
JOIN Clientes C ON P.cliente_id = C.cliente_id
JOIN Estados_Proyecto E ON P.estado_id = E.estado_id
WHERE C.nombre = 'Acme Corp';

-- Consultar todas las tareas asignadas a un desarrollador
SELECT T.nombre AS Tarea, T.descripcion, EP.nombre AS Estado, P.nombre AS Proyecto
FROM Tareas T
JOIN Estados_Tarea EP ON T.estado_id = EP.estado_id
JOIN Proyectos P ON T.proyecto_id = P.proyecto_id
JOIN Usuarios U ON T.asignado_a = U.usuario_id
WHERE U.nombre = 'María López';

-- Consultar el historial de una tarea específica
SELECT HT.cambio, HT.fecha_cambio, U.nombre AS Usuario
FROM Historial_Tareas HT
JOIN Usuarios U ON HT.usuario_id = U.usuario_id
WHERE HT.tarea_id = 1;

-- Consultar los recursos utilizados en un proyecto
SELECT R.nombre, R.costo, TR.nombre AS Tipo
FROM Recursos R
JOIN Tipos_Recurso TR ON R.tipo_id = TR.tipo_id
WHERE R.proyecto_id = 1;

-- Consultar el total de horas usadas por un desarrollador en un proyecto
SELECT U.nombre AS Usuario, P.nombre AS Proyecto, SUM(BH.horas_usadas) AS Horas_Usadas
FROM Bolsa_Horas BH
JOIN Usuarios U ON BH.usuario_id = U.usuario_id
JOIN Proyectos P ON BH.proyecto_id = P.proyecto_id
WHERE U.nombre = 'María López'
GROUP BY U.nombre, P.nombre;

-- Consultar las notificaciones no leídas de un usuario
SELECT N.mensaje, N.fecha_notificacion
FROM Notificaciones N
JOIN Usuarios U ON N.usuario_id = U.usuario_id
WHERE U.nombre = 'María López' AND N.leida = 0;

-- Consultar las actividades realizadas en un proyecto
SELECT A.nombre, A.descripcion, A.fecha_actividad
FROM Actividades A
WHERE A.proyecto_id = 1;

-- Consultar la última evaluación de desempeño de un desarrollador
SELECT E.resultado, E.fecha_evaluacion
FROM Evaluaciones_Desempeno E
JOIN Usuarios U ON E.usuario_id = U.usuario_id
WHERE U.nombre = 'María López'
ORDER BY E.fecha_evaluacion DESC
FETCH FIRST 1 ROWS ONLY;

-- Consultar los mensajes de un chat
SELECT M.contenido, M.fecha_mensaje, U.nombre AS Usuario
FROM Mensajes M
JOIN Usuarios U ON M.usuario_id = U.usuario_id
WHERE M.chat_id = 1
ORDER BY M.fecha_mensaje ASC;
