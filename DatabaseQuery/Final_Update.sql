-- Actualizar un rol
UPDATE Roles SET nombre = 'Project Manager' WHERE rol_id = 1;

-- Actualizar un usuario
UPDATE Usuarios SET nombre = 'Juan Pérez Actualizado', email = 'juan.perez@updatedfreelance.com' WHERE usuario_id = 1;

-- Actualizar un país
UPDATE Paises SET nombre = 'México' WHERE pais_id = 1;

-- Actualizar una ciudad
UPDATE Ciudades SET nombre = 'Ciudad de México' WHERE ciudad_id = 1;

-- Actualizar una dirección
UPDATE Direcciones SET calle = 'Calle Reforma 123' WHERE direccion_id = 1;

-- Actualizar un cliente
UPDATE Clientes SET nombre = 'Acme Corp Actualizado', telefono = '123456789' WHERE cliente_id = 1;

-- Actualizar el estado de un proyecto
UPDATE Estados_Proyecto SET nombre = 'Completado' WHERE estado_id = 1;

-- Actualizar un proyecto
UPDATE Proyectos SET nombre = 'Proyecto E-commerce Actualizado', descripcion = 'Tienda online actualizada' WHERE proyecto_id = 1;

-- Actualizar el estado de una tarea
UPDATE Estados_Tarea SET nombre = 'En Revisión' WHERE estado_id = 1;

-- Actualizar una tarea
UPDATE Tareas SET nombre = 'Diseño de la interfaz actualizado', descripcion = 'Rediseñar la interfaz de usuario' WHERE tarea_id = 1;

-- Actualizar un recurso
UPDATE Recursos SET nombre = 'Servidor Google Cloud', costo = 150.00 WHERE recurso_id = 1;

-- Actualizar el cambio en el historial de tareas
UPDATE Historial_Tareas SET cambio = 'Cambio de estado a completado' WHERE historial_id = 1;

-- Actualizar un documento
UPDATE Documentos SET nombre_archivo = 'nuevo_documento.pdf', ruta_archivo = '/documentos/nuevo_documento.pdf' WHERE documento_id = 1;

-- Actualizar una bolsa de horas
UPDATE Bolsa_Horas SET horas_usadas = 40, horas_restantes = 60 WHERE bolsa_id = 1;

-- Actualizar un comentario
UPDATE Comentarios SET contenido = 'Comentario actualizado sobre el diseño.' WHERE comentario_id = 1;

-- Actualizar una notificación
UPDATE Notificaciones SET mensaje = 'Tu tarea ha sido actualizada.' WHERE notificacion_id = 1;

-- Actualizar una factura
UPDATE Facturacion SET monto = 1100.00 WHERE factura_id = 1;

-- Actualizar un pago
UPDATE Pagos SET metodo_pago = 'PayPal' WHERE pago_id = 1;

-- Actualizar un evento
UPDATE Eventos SET nombre = 'Kick-off Meeting Actualizado' WHERE evento_id = 1;

-- Actualizar una encuesta
UPDATE Encuestas SET nombre = 'Encuesta de satisfacción de cliente actualizada' WHERE encuesta_id = 1;

-- Actualizar una pregunta de encuesta
UPDATE Preguntas_Encuesta SET pregunta = '¿Cómo calificaría la calidad del trabajo realizado en general?' WHERE pregunta_id = 1;

-- Actualizar una respuesta de encuesta
UPDATE Respuestas_Encuesta SET respuesta = 'Muy buena' WHERE respuesta_id = 1;

-- Actualizar un informe
UPDATE Informes SET nombre = 'Informe de avance actualizado', contenido = 'Detalles del progreso realizado hasta ahora, versión actualizada.' WHERE informe_id = 1;

-- Actualizar una versión de informe
UPDATE Versiones_Informes SET version = 'v1.1', contenido = 'Actualización de la versión del informe.' WHERE version_id = 1;

-- Actualizar un producto
UPDATE Productos SET nombre = 'Producto de software actualizado', costo = 250.00 WHERE producto_id = 1;

-- Actualizar una actividad
UPDATE Actividades SET nombre = 'Revisión de diseño actualizada' WHERE actividad_id = 1;

-- Actualizar una evaluación de desempeño
UPDATE Evaluaciones_Desempeno SET resultado = 'Muy bueno' WHERE evaluacion_id = 1;

-- Actualizar un indicador de desempeño
UPDATE Indicadores_Desempeno SET descripcion = 'Capacidad para entregar tareas en los tiempos acordados, con calidad.' WHERE indicador_id = 1;

-- Actualizar un chat
UPDATE Chats SET nombre = 'Chat del Proyecto E-commerce Actualizado' WHERE chat_id = 1;

-- Actualizar un mensaje
UPDATE Mensajes SET contenido = 'Hola equipo, por favor revisen el diseño actualizado.' WHERE mensaje_id = 1;
