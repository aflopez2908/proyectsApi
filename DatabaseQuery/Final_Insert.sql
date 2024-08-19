-- Insertar roles
INSERT INTO Roles (nombre) VALUES ('Administrador'), ('Desarrollador'), ('Cliente');

-- Insertar permisos
INSERT INTO Permisos (nombre) VALUES ('Ver Proyectos'), ('Editar Proyectos'), ('Eliminar Proyectos');

-- Relacionar roles con permisos
INSERT INTO Roles_Permisos (rol_id, permiso_id) VALUES (1, 1), (1, 2), (1, 3), (2, 1);

-- Insertar usuarios
INSERT INTO Usuarios (nombre, email, contraseña, rol_id, fecha_creacion) 
VALUES ('Juan Pérez', 'juan.perez@freelance.com', 'password123', 1, GETDATE()),
       ('María López', 'maria.lopez@freelance.com', 'password123', 2, GETDATE()),
       ('Carlos Gómez', 'carlos.gomez@cliente.com', 'password123', 3, GETDATE());

-- Insertar países
INSERT INTO Paises (nombre) VALUES ('Colombia'), ('Estados Unidos');

-- Insertar ciudades
INSERT INTO Ciudades (nombre, pais_id) VALUES ('Bogotá', 1), ('Medellín', 1), ('New York', 2);

-- Insertar direcciones
INSERT INTO Direcciones (calle, ciudad_id) VALUES ('Calle 123', 1), ('Carrera 45', 2), ('5th Avenue', 3);

-- Insertar clientes
INSERT INTO Clientes (nombre, email, telefono, direccion_id) 
VALUES ('Acme Corp', 'contacto@acmecorp.com', '321654987', 1),
       ('Tech Solutions', 'info@techsolutions.com', '987654321', 2);

-- Insertar estados de proyecto
INSERT INTO Estados_Proyecto (nombre) VALUES ('En Progreso'), ('Completado');

-- Insertar tipos de proyecto
INSERT INTO Tipos_Proyecto (nombre) VALUES ('Desarrollo Web'), ('Aplicación Móvil');

-- Insertar categorías de proyecto
INSERT INTO Categorias_Proyecto (nombre, descripcion) VALUES ('E-commerce', 'Proyectos de comercio electrónico'), 
                                                            ('FinTech', 'Proyectos financieros');

-- Insertar proyectos
INSERT INTO Proyectos (nombre, descripcion, cliente_id, fecha_inicio, fecha_fin, estado_id) 
VALUES ('Proyecto E-commerce', 'Desarrollo de una tienda online', 1, GETDATE(), DATEADD(MONTH, 6, GETDATE()), 1),
       ('App Financiera', 'Desarrollo de una aplicación móvil para gestión financiera', 2, GETDATE(), DATEADD(MONTH, 8, GETDATE()), 1);

-- Relacionar proyectos con categorías
INSERT INTO Proyectos_Categorias (proyecto_id, categoria_id) VALUES (1, 1), (2, 2);

-- Relacionar proyectos con tipos
INSERT INTO Proyectos_Tipos (proyecto_id, tipo_id) VALUES (1, 1), (2, 2);

-- Insertar estados de tarea
INSERT INTO Estados_Tarea (nombre) VALUES ('Pendiente'), ('En Progreso'), ('Completado');

-- Insertar prioridades de tarea
INSERT INTO Prioridades_Tarea (nombre) VALUES ('Alta'), ('Media'), ('Baja');

-- Insertar etiquetas
INSERT INTO Etiquetas (nombre) VALUES ('Urgente'), ('Revisión');

-- Insertar tareas
INSERT INTO Tareas (proyecto_id, nombre, descripcion, estado_id, fecha_inicio, fecha_fin, asignado_a) 
VALUES (1, 'Diseño de la interfaz', 'Diseñar la interfaz de usuario para la tienda online', 1, GETDATE(), DATEADD(DAY, 10, GETDATE()), 2),
       (2, 'Desarrollo del backend', 'Implementar la lógica del servidor para la app', 1, GETDATE(), DATEADD(DAY, 20, GETDATE()), 2);

-- Relacionar tareas con prioridades
INSERT INTO Tareas_Prioridades (tarea_id, prioridad_id) VALUES (1, 1), (2, 2);

-- Relacionar tareas con etiquetas
INSERT INTO Tareas_Etiquetas (tarea_id, etiqueta_id) VALUES (1, 1), (2, 2);

-- Insertar tipos de recurso
INSERT INTO Tipos_Recurso (nombre) VALUES ('Humano'), ('Tecnológico');

-- Insertar recursos
INSERT INTO Recursos (nombre, tipo_id, costo, proyecto_id) VALUES ('Servidor AWS', 2, 100.00, 1),
                                                                  ('Desarrollador Senior', 1, 500.00, 2);

-- Insertar historial de tareas
INSERT INTO Historial_Tareas (tarea_id, cambio, fecha_cambio, usuario_id) 
VALUES (1, 'Inicio de diseño', GETDATE(), 2),
       (2, 'Inicio de desarrollo', GETDATE(), 2);

-- Insertar documentos
INSERT INTO Documentos (tarea_id, nombre_archivo, ruta_archivo, fecha_subida, subido_por) 
VALUES (1, 'wireframe.pdf', '/documentos/wireframe.pdf', GETDATE(), 2),
       (2, 'backend-architecture.docx', '/documentos/backend-architecture.docx', GETDATE(), 2);

-- Insertar registro de login y auditoría
INSERT INTO Login_Auditoria (usuario_id, fecha_login, exitoso) 
VALUES (1, GETDATE(), 1), (2, GETDATE(), 1);

-- Insertar bolsa de horas
INSERT INTO Bolsa_Horas (usuario_id, proyecto_id, horas_totales, horas_usadas, horas_restantes) 
VALUES (2, 1, 100, 20, 80), (2, 2, 150, 30, 120);

-- Insertar comentarios
INSERT INTO Comentarios (tarea_id, usuario_id, contenido, fecha_comentario) 
VALUES (1, 2, 'Iniciando el diseño de la interfaz.', GETDATE()),
       (2, 2, 'Backend en progreso.', GETDATE());

-- Insertar notificaciones
INSERT INTO Notificaciones (usuario_id, mensaje, fecha_notificacion, leida) 
VALUES (2, 'Tienes una nueva tarea asignada: Diseño de la interfaz', GETDATE(), 0);

-- Insertar facturación
INSERT INTO Facturacion (proyecto_id, monto, fecha_emision, fecha_vencimiento) 
VALUES (1, 1000.00, GETDATE(), DATEADD(MONTH, 1, GETDATE()));

-- Insertar pagos
INSERT INTO Pagos (factura_id, monto, fecha_pago, metodo_pago) 
VALUES (1, 1000.00, GETDATE(), 'Transferencia Bancaria');

-- Insertar eventos
INSERT INTO Eventos (proyecto_id, nombre, descripcion, fecha_evento) 
VALUES (1, 'Kick-off Meeting', 'Reunión inicial del proyecto', GETDATE());

-- Insertar asistentes
INSERT INTO Asistentes (evento_id, usuario_id) VALUES (1, 1), (1, 2);

-- Insertar encuestas
INSERT INTO Encuestas (nombre, descripcion, fecha_creacion) 
VALUES ('Encuesta de satisfacción de cliente', 'Mide la satisfacción del cliente después del proyecto.', GETDATE());

-- Insertar preguntas de encuesta
INSERT INTO Preguntas_Encuesta (encuesta_id, pregunta) 
VALUES (1, '¿Cómo calificaría la calidad del trabajo realizado?');

-- Insertar respuestas de encuesta
INSERT INTO Respuestas_Encuesta (pregunta_id, respuesta) VALUES (1, 'Excelente');

-- Relacionar respuestas con usuarios
INSERT INTO Respuestas_Usuarios (respuesta_id, usuario_id) VALUES (1, 3);

-- Insertar informes
INSERT INTO Informes (proyecto_id, nombre, contenido, fecha_creacion) 
VALUES (1, 'Informe de avance', 'Detalles del progreso realizado hasta ahora.', GETDATE());

-- Insertar versiones de informes
INSERT INTO Versiones_Informes (informe_id, version, contenido, fecha_creacion) 
VALUES (1, 'v1.0', 'Primera versión del informe de avance.', GETDATE());

-- Insertar productos
INSERT INTO Productos (nombre, descripcion, costo) 
VALUES ('Producto de software', 'Licencia de software utilizado en el proyecto', 200.00);

-- Relacionar productos con proyectos
INSERT INTO Proyectos_Productos (proyecto_id, producto_id) VALUES (1, 1);

-- Insertar historial de notificaciones
INSERT INTO Historial_Notificaciones (notificacion_id, usuario_id, fecha_envio) 
VALUES (1, 2, GETDATE());

-- Insertar actividades
INSERT INTO Actividades (proyecto_id, nombre, descripcion, fecha_actividad) 
VALUES (1, 'Revisión de diseño', 'Revisión del diseño de la interfaz por parte del cliente', GETDATE());

-- Insertar evaluaciones de desempeño
INSERT INTO Evaluaciones_Desempeno (usuario_id, fecha_evaluacion, resultado) 
VALUES (2, GETDATE(), 'Excelente rendimiento durante el proyecto');

-- Insertar indicadores de desempeño
INSERT INTO Indicadores_Desempeno (nombre, descripcion) 
VALUES ('Cumplimiento de plazos', 'Capacidad para entregar tareas en los tiempos acordados');

-- Relacionar evaluaciones con indicadores
INSERT INTO Evaluaciones_Indicadores (evaluacion_id, indicador_id, valor) 
VALUES (1, 1, 95.00);

-- Insertar chats
INSERT INTO Chats (proyecto_id, nombre) 
VALUES (1, 'Chat del Proyecto E-commerce');

-- Insertar mensajes
INSERT INTO Mensajes (chat_id, usuario_id, contenido, fecha_mensaje) 
VALUES (1, 2, 'Hola equipo, por favor revisen el diseño de la interfaz.', GETDATE());
