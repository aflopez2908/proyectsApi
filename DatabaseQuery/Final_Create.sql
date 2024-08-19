-- Tabla Roles
CREATE TABLE Roles (
    rol_id INT IDENTITY(1,1) PRIMARY KEY,
    nombre NVARCHAR(50) NOT NULL
);

-- Tabla Permisos
CREATE TABLE Permisos (
    permiso_id INT IDENTITY(1,1) PRIMARY KEY,
    nombre NVARCHAR(50) NOT NULL
);

-- Tabla Roles-Permisos
CREATE TABLE Roles_Permisos (
    rol_id INT,
    permiso_id INT,
    PRIMARY KEY (rol_id, permiso_id),
    FOREIGN KEY (rol_id) REFERENCES Roles(rol_id),
    FOREIGN KEY (permiso_id) REFERENCES Permisos(permiso_id)
);

-- Tabla Usuarios
CREATE TABLE Usuarios (
    usuario_id INT IDENTITY(1,1) PRIMARY KEY,
    nombre NVARCHAR(100) NOT NULL,
    email NVARCHAR(100) NOT NULL UNIQUE,
    contraseña NVARCHAR(255) NOT NULL,
    rol_id INT,
    fecha_creacion DATETIME,
    FOREIGN KEY (rol_id) REFERENCES Roles(rol_id)
);

-- Tabla Países
CREATE TABLE Paises (
    pais_id INT IDENTITY(1,1) PRIMARY KEY,
    nombre NVARCHAR(100) NOT NULL
);

-- Tabla Ciudades
CREATE TABLE Ciudades (
    ciudad_id INT IDENTITY(1,1) PRIMARY KEY,
    nombre NVARCHAR(100) NOT NULL,
    pais_id INT,
    FOREIGN KEY (pais_id) REFERENCES Paises(pais_id)
);

-- Tabla Direcciones
CREATE TABLE Direcciones (
    direccion_id INT IDENTITY(1,1) PRIMARY KEY,
    calle NVARCHAR(255),
    ciudad_id INT,
    FOREIGN KEY (ciudad_id) REFERENCES Ciudades(ciudad_id)
);

-- Tabla Clientes
CREATE TABLE Clientes (
    cliente_id INT IDENTITY(1,1) PRIMARY KEY,
    nombre NVARCHAR(100) NOT NULL,
    email NVARCHAR(100),
    telefono NVARCHAR(20),
    direccion_id INT,
    FOREIGN KEY (direccion_id) REFERENCES Direcciones(direccion_id)
);

-- Tabla Estados de Proyecto
CREATE TABLE Estados_Proyecto (
    estado_id INT IDENTITY(1,1) PRIMARY KEY,
    nombre NVARCHAR(50) NOT NULL
);

-- Tabla Tipos de Proyecto
CREATE TABLE Tipos_Proyecto (
    tipo_id INT IDENTITY(1,1) PRIMARY KEY,
    nombre NVARCHAR(100) NOT NULL
);

-- Tabla Categorías de Proyecto
CREATE TABLE Categorias_Proyecto (
    categoria_id INT IDENTITY(1,1) PRIMARY KEY,
    nombre NVARCHAR(100),
    descripcion NVARCHAR(MAX)
);

-- Tabla Proyectos
CREATE TABLE Proyectos (
    proyecto_id INT IDENTITY(1,1) PRIMARY KEY,
    nombre NVARCHAR(100) NOT NULL,
    descripcion NVARCHAR(MAX),
    cliente_id INT,
    fecha_inicio DATETIME,
    fecha_fin DATETIME,
    estado_id INT,
    FOREIGN KEY (cliente_id) REFERENCES Clientes(cliente_id),
    FOREIGN KEY (estado_id) REFERENCES Estados_Proyecto(estado_id)
);

-- Tabla Proyectos-Categorías
CREATE TABLE Proyectos_Categorias (
    proyecto_id INT,
    categoria_id INT,
    PRIMARY KEY (proyecto_id, categoria_id),
    FOREIGN KEY (proyecto_id) REFERENCES Proyectos(proyecto_id),
    FOREIGN KEY (categoria_id) REFERENCES Categorias_Proyecto(categoria_id)
);

-- Tabla Proyectos-Tipos
CREATE TABLE Proyectos_Tipos (
    proyecto_id INT,
    tipo_id INT,
    PRIMARY KEY (proyecto_id, tipo_id),
    FOREIGN KEY (proyecto_id) REFERENCES Proyectos(proyecto_id),
    FOREIGN KEY (tipo_id) REFERENCES Tipos_Proyecto(tipo_id)
);

-- Tabla Estados de Tarea
CREATE TABLE Estados_Tarea (
    estado_id INT IDENTITY(1,1) PRIMARY KEY,
    nombre NVARCHAR(50) NOT NULL
);

-- Tabla Prioridades de Tarea
CREATE TABLE Prioridades_Tarea (
    prioridad_id INT IDENTITY(1,1) PRIMARY KEY,
    nombre NVARCHAR(50) NOT NULL
);

-- Tabla Etiquetas
CREATE TABLE Etiquetas (
    etiqueta_id INT IDENTITY(1,1) PRIMARY KEY,
    nombre NVARCHAR(50) NOT NULL
);

-- Tabla Tareas
CREATE TABLE Tareas (
    tarea_id INT IDENTITY(1,1) PRIMARY KEY,
    proyecto_id INT,
    nombre NVARCHAR(100) NOT NULL,
    descripcion NVARCHAR(MAX),
    estado_id INT,
    fecha_inicio DATETIME,
    fecha_fin DATETIME,
    asignado_a INT,
    FOREIGN KEY (proyecto_id) REFERENCES Proyectos(proyecto_id),
    FOREIGN KEY (estado_id) REFERENCES Estados_Tarea(estado_id),
    FOREIGN KEY (asignado_a) REFERENCES Usuarios(usuario_id)
);

-- Tabla Tareas-Prioridades
CREATE TABLE Tareas_Prioridades (
    tarea_id INT,
    prioridad_id INT,
    PRIMARY KEY (tarea_id, prioridad_id),
    FOREIGN KEY (tarea_id) REFERENCES Tareas(tarea_id),
    FOREIGN KEY (prioridad_id) REFERENCES Prioridades_Tarea(prioridad_id)
);

-- Tabla Tareas-Etiquetas
CREATE TABLE Tareas_Etiquetas (
    tarea_id INT,
    etiqueta_id INT,
    PRIMARY KEY (tarea_id, etiqueta_id),
    FOREIGN KEY (tarea_id) REFERENCES Tareas(tarea_id),
    FOREIGN KEY (etiqueta_id) REFERENCES Etiquetas(etiqueta_id)
);

-- Tabla Tipos de Recurso
CREATE TABLE Tipos_Recurso (
    tipo_id INT IDENTITY(1,1) PRIMARY KEY,
    nombre NVARCHAR(50) NOT NULL
);

-- Tabla Recursos
CREATE TABLE Recursos (
    recurso_id INT IDENTITY(1,1) PRIMARY KEY,
    nombre NVARCHAR(100) NOT NULL,
    tipo_id INT,
    costo DECIMAL(10, 2),
    proyecto_id INT,
    FOREIGN KEY (tipo_id) REFERENCES Tipos_Recurso(tipo_id),
    FOREIGN KEY (proyecto_id) REFERENCES Proyectos(proyecto_id)
);

-- Tabla Historial de Tareas
CREATE TABLE Historial_Tareas (
    historial_id INT IDENTITY(1,1) PRIMARY KEY,
    tarea_id INT,
    cambio NVARCHAR(MAX),
    fecha_cambio DATETIME,
    usuario_id INT,
    FOREIGN KEY (tarea_id) REFERENCES Tareas(tarea_id),
    FOREIGN KEY (usuario_id) REFERENCES Usuarios(usuario_id)
);

-- Tabla Documentos
CREATE TABLE Documentos (
    documento_id INT IDENTITY(1,1) PRIMARY KEY,
    tarea_id INT,
    nombre_archivo NVARCHAR(255) NOT NULL,
    ruta_archivo NVARCHAR(255) NOT NULL,
    fecha_subida DATETIME,
    subido_por INT,
    FOREIGN KEY (tarea_id) REFERENCES Tareas(tarea_id),
    FOREIGN KEY (subido_por) REFERENCES Usuarios(usuario_id)
);

-- Tabla Login y Auditoría
CREATE TABLE Login_Auditoria (
    login_id INT IDENTITY(1,1) PRIMARY KEY,
    usuario_id INT,
    fecha_login DATETIME,
    exitoso BIT,
    FOREIGN KEY (usuario_id) REFERENCES Usuarios(usuario_id)
);

-- Tabla Bolsa de Horas
CREATE TABLE Bolsa_Horas (
    bolsa_id INT IDENTITY(1,1) PRIMARY KEY,
    usuario_id INT,
    proyecto_id INT,
    horas_totales INT,
    horas_usadas INT,
    horas_restantes INT,
    FOREIGN KEY (usuario_id) REFERENCES Usuarios(usuario_id),
    FOREIGN KEY (proyecto_id) REFERENCES Proyectos(proyecto_id)
);

-- Tabla Comentarios
CREATE TABLE Comentarios (
    comentario_id INT IDENTITY(1,1) PRIMARY KEY,
    tarea_id INT,
    usuario_id INT,
    contenido NVARCHAR(MAX),
    fecha_comentario DATETIME,
    FOREIGN KEY (tarea_id) REFERENCES Tareas(tarea_id),
    FOREIGN KEY (usuario_id) REFERENCES Usuarios(usuario_id)
);



-- Tabla Notificaciones
CREATE TABLE Notificaciones (
    notificacion_id INT IDENTITY(1,1) PRIMARY KEY,
    usuario_id INT,
    mensaje NVARCHAR(MAX),
    fecha_notificacion DATETIME,
    leida BIT,
  --  plantilla_id INT,
    FOREIGN KEY (usuario_id) REFERENCES Usuarios(usuario_id),
  --  FOREIGN KEY (plantilla_id) REFERENCES Plantillas_Correo(plantilla_id)
);

---- Tabla Plantillas de Correo
--CREATE TABLE Plantillas_Correo (
--    plantilla_id INT IDENTITY(1,1) PRIMARY KEY,
--    nombre NVARCHAR(100),
--    asunto NVARCHAR(255),
--    cuerpo NVARCHAR(MAX)
--);

-- Tabla Facturación
CREATE TABLE Facturacion (
    factura_id INT IDENTITY(1,1) PRIMARY KEY,
    proyecto_id INT,
    monto DECIMAL(10, 2),
    fecha_emision DATETIME,
    fecha_vencimiento DATETIME,
    FOREIGN KEY (proyecto_id) REFERENCES Proyectos(proyecto_id)
);

-- Tabla Pagos
CREATE TABLE Pagos (
    pago_id INT IDENTITY(1,1) PRIMARY KEY,
    factura_id INT,
    monto DECIMAL(10, 2),
    fecha_pago DATETIME,
    metodo_pago NVARCHAR(50),
    FOREIGN KEY (factura_id) REFERENCES Facturacion(factura_id)
);

-- Tabla Eventos
CREATE TABLE Eventos (
    evento_id INT IDENTITY(1,1) PRIMARY KEY,
    proyecto_id INT,
    nombre NVARCHAR(100),
    descripcion NVARCHAR(MAX),
    fecha_evento DATETIME,
    FOREIGN KEY (proyecto_id) REFERENCES Proyectos(proyecto_id)
);

-- Tabla Asistentes
CREATE TABLE Asistentes (
    asistente_id INT IDENTITY(1,1) PRIMARY KEY,
    evento_id INT,
    usuario_id INT,
    FOREIGN KEY (evento_id) REFERENCES Eventos(evento_id),
    FOREIGN KEY (usuario_id) REFERENCES Usuarios(usuario_id)
);

-- Tabla Encuestas
CREATE TABLE Encuestas (
    encuesta_id INT IDENTITY(1,1) PRIMARY KEY,
    nombre NVARCHAR(100),
    descripcion NVARCHAR(MAX),
    fecha_creacion DATETIME
);

-- Tabla Preguntas de Encuesta
CREATE TABLE Preguntas_Encuesta (
    pregunta_id INT IDENTITY(1,1) PRIMARY KEY,
    encuesta_id INT,
    pregunta NVARCHAR(MAX),
    FOREIGN KEY (encuesta_id) REFERENCES Encuestas(encuesta_id)
);

-- Tabla Respuestas de Encuesta
CREATE TABLE Respuestas_Encuesta (
    respuesta_id INT IDENTITY(1,1) PRIMARY KEY,
    pregunta_id INT,
    respuesta NVARCHAR(MAX),
    FOREIGN KEY (pregunta_id) REFERENCES Preguntas_Encuesta(pregunta_id)
);

-- Tabla Respuestas de Usuarios
CREATE TABLE Respuestas_Usuarios (
    respuesta_usuario_id INT IDENTITY(1,1) PRIMARY KEY,
    respuesta_id INT,
    usuario_id INT,
    FOREIGN KEY (respuesta_id) REFERENCES Respuestas_Encuesta(respuesta_id),
    FOREIGN KEY (usuario_id) REFERENCES Usuarios(usuario_id)
);

-- Tabla Informes
CREATE TABLE Informes (
    informe_id INT IDENTITY(1,1) PRIMARY KEY,
    proyecto_id INT,
    nombre NVARCHAR(100),
    contenido NVARCHAR(MAX),
    fecha_creacion DATETIME,
    FOREIGN KEY (proyecto_id) REFERENCES Proyectos(proyecto_id)
);

-- Tabla Versiones de Informes
CREATE TABLE Versiones_Informes (
    version_id INT IDENTITY(1,1) PRIMARY KEY,
    informe_id INT,
    version NVARCHAR(10),
    contenido NVARCHAR(MAX),
    fecha_creacion DATETIME,
    FOREIGN KEY (informe_id) REFERENCES Informes(informe_id)
);

-- Tabla Productos
CREATE TABLE Productos (
    producto_id INT IDENTITY(1,1) PRIMARY KEY,
    nombre NVARCHAR(100),
    descripcion NVARCHAR(MAX),
    costo DECIMAL(10, 2)
);

-- Tabla Proyectos-Productos
CREATE TABLE Proyectos_Productos (
    proyecto_id INT,
    producto_id INT,
    PRIMARY KEY (proyecto_id, producto_id),
    FOREIGN KEY (proyecto_id) REFERENCES Proyectos(proyecto_id),
    FOREIGN KEY (producto_id) REFERENCES Productos(producto_id)
);

---- Tabla Configuración
--CREATE TABLE Configuracion (
--    configuracion_id INT IDENTITY(1,1) PRIMARY KEY,
--    clave NVARCHAR(50),
--    valor NVARCHAR(MAX)
--);

-- Tabla Historial de Notificaciones
CREATE TABLE Historial_Notificaciones (
    historial_id INT IDENTITY(1,1) PRIMARY KEY,
    notificacion_id INT,
    usuario_id INT,
    fecha_envio DATETIME,
    FOREIGN KEY (notificacion_id) REFERENCES Notificaciones(notificacion_id),
    FOREIGN KEY (usuario_id) REFERENCES Usuarios(usuario_id)
);

-- Tabla Actividades
CREATE TABLE Actividades (
    actividad_id INT IDENTITY(1,1) PRIMARY KEY,
    proyecto_id INT,
    nombre NVARCHAR(100),
    descripcion NVARCHAR(MAX),
    fecha_actividad DATETIME,
    FOREIGN KEY (proyecto_id) REFERENCES Proyectos(proyecto_id)
);

-- Tabla Evaluaciones de Desempeño
CREATE TABLE Evaluaciones_Desempeno (
    evaluacion_id INT IDENTITY(1,1) PRIMARY KEY,
    usuario_id INT,
    fecha_evaluacion DATETIME,
    resultado NVARCHAR(MAX),
    FOREIGN KEY (usuario_id) REFERENCES Usuarios(usuario_id)
);

-- Tabla Indicadores de Desempeño
CREATE TABLE Indicadores_Desempeno (
    indicador_id INT IDENTITY(1,1) PRIMARY KEY,
    nombre NVARCHAR(100),
    descripcion NVARCHAR(MAX)
);

-- Tabla Evaluaciones-Indicadores
CREATE TABLE Evaluaciones_Indicadores (
    evaluacion_id INT,
    indicador_id INT,
    valor DECIMAL(10, 2),
    PRIMARY KEY (evaluacion_id, indicador_id),
    FOREIGN KEY (evaluacion_id) REFERENCES Evaluaciones_Desempeno(evaluacion_id),
    FOREIGN KEY (indicador_id) REFERENCES Indicadores_Desempeno(indicador_id)
);

-- Tabla Chats
CREATE TABLE Chats (
    chat_id INT IDENTITY(1,1) PRIMARY KEY,
    proyecto_id INT,
    nombre NVARCHAR(100),
    FOREIGN KEY (proyecto_id) REFERENCES Proyectos(proyecto_id)
);

-- Tabla Mensajes
CREATE TABLE Mensajes (
    mensaje_id INT IDENTITY(1,1) PRIMARY KEY,
    chat_id INT,
    usuario_id INT,
    contenido NVARCHAR(MAX),
    fecha_mensaje DATETIME,
    FOREIGN KEY (chat_id) REFERENCES Chats(chat_id),
    FOREIGN KEY (usuario_id) REFERENCES Usuarios(usuario_id)
);


