create database neuroplay;

use neuroplay;

-- --------------------------------------------------------
-- Tabla de Usuarios (común para pacientes, terapeutas y admins)
-- --------------------------------------------------------
CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipo ENUM('paciente', 'terapeuta', 'admin') NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    fecha_nacimiento DATE,
    genero ENUM('masculino', 'femenino', 'otro'),
    telefono VARCHAR(20),
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    avatar VARCHAR(255) DEFAULT 'default.png',
    estado ENUM('activo', 'inactivo') DEFAULT 'activo'
);

-- --------------------------------------------------------
-- Tabla de Terapeutas (info profesional)
-- --------------------------------------------------------
CREATE TABLE terapeutas (
    usuario_id INT PRIMARY KEY,
    especialidad VARCHAR(100) NOT NULL,
    licencia VARCHAR(50) UNIQUE NOT NULL,
    biografia TEXT,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
);

-- --------------------------------------------------------
-- Tabla de Pacientes (info médica + relación con terapeuta)
-- --------------------------------------------------------
CREATE TABLE pacientes (
    usuario_id INT PRIMARY KEY,
    terapeuta_id INT NOT NULL,
    fecha_acv DATE NOT NULL,
    tipo_acv ENUM('isquémico', 'hemorrágico', 'TIA') NOT NULL,
    antecedentes TEXT,
    medicacion_actual TEXT,
    progreso_total DECIMAL(5,2) DEFAULT 0.00,
    ejercicios_completados INT DEFAULT 0,
    dias_consecutivos INT DEFAULT 0,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE,
    FOREIGN KEY (terapeuta_id) REFERENCES terapeutas(usuario_id)
);

-- --------------------------------------------------------
-- Tabla de Terapias (planes de rehabilitación)
-- --------------------------------------------------------
CREATE TABLE terapias (
    id INT AUTO_INCREMENT PRIMARY KEY,
    paciente_id INT NOT NULL,
    terapeuta_id INT NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE,
    estado ENUM('activa', 'completada', 'pausada') DEFAULT 'activa',
    FOREIGN KEY (paciente_id) REFERENCES pacientes(usuario_id),
    FOREIGN KEY (terapeuta_id) REFERENCES terapeutas(usuario_id)
);

-- --------------------------------------------------------
-- Tabla de Ejercicios (tareas específicas por terapia)
-- --------------------------------------------------------
CREATE TABLE ejercicios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    terapia_id INT NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT NOT NULL,
    tipo ENUM('físico', 'cognitivo', 'lenguaje') NOT NULL,
    repeticiones INT,
    duracion_minutos INT,
    nivel_dificultad ENUM('bajo', 'medio', 'alto') DEFAULT 'medio',
    video_url VARCHAR(255),
    imagen_url VARCHAR(255),
    FOREIGN KEY (terapia_id) REFERENCES terapias(id) ON DELETE CASCADE
);

-- --------------------------------------------------------
-- Tabla de Sesiones (registro de actividades completadas)
-- --------------------------------------------------------
CREATE TABLE sesiones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    ejercicio_id INT NOT NULL,
    paciente_id INT NOT NULL,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    duracion_minutos INT NOT NULL,
    rendimiento ENUM('bajo', 'medio', 'alto'),
    completado BOOLEAN DEFAULT FALSE,
    activacion_muscular DECIMAL(5,2),
    pico_activacion DECIMAL(5,2),
    repeticiones INT,
    observaciones TEXT,
    FOREIGN KEY (ejercicio_id) REFERENCES ejercicios(id),
    FOREIGN KEY (paciente_id) REFERENCES pacientes(usuario_id)
);

-- --------------------------------------------------------
-- Tabla de Progreso (métricas detalladas)
-- --------------------------------------------------------
CREATE TABLE progreso (
    id INT AUTO_INCREMENT PRIMARY KEY,
    paciente_id INT NOT NULL,
    fecha DATE NOT NULL,
    fuerza DECIMAL(5,2),
    movilidad DECIMAL(5,2),
    detalle DECIMAL(5,2),
    notas TEXT,
    FOREIGN KEY (paciente_id) REFERENCES pacientes(usuario_id)
);

-- --------------------------------------------------------
-- Tabla de Recomendaciones (del terapeuta al paciente)
-- --------------------------------------------------------
CREATE TABLE recomendaciones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    terapia_id INT NOT NULL,
    descripcion TEXT NOT NULL,
    completada BOOLEAN DEFAULT FALSE,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (terapia_id) REFERENCES terapias(id)
);

-- --------------------------------------------------------
-- Tabla de Dispositivos EMG (monitoreo en tiempo real)
-- --------------------------------------------------------
CREATE TABLE dispositivos_emg (
    id INT AUTO_INCREMENT PRIMARY KEY,
    paciente_id INT NOT NULL,
    nombre_sensor VARCHAR(50) NOT NULL,
    calidad_senal ENUM('baja', 'media', 'alta') DEFAULT 'media',
    ultima_activacion DECIMAL(5,2),
    fecha_conexion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (paciente_id) REFERENCES pacientes(usuario_id)
);

-- --------------------------------------------------------
-- Tabla de Citas (próximas sesiones terapéuticas)
-- --------------------------------------------------------
CREATE TABLE citas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    paciente_id INT NOT NULL,
    terapeuta_id INT NOT NULL,
    titulo VARCHAR(100) NOT NULL,
    fecha_hora DATETIME NOT NULL,
    tipo ENUM('presencial', 'virtual') NOT NULL,
    FOREIGN KEY (paciente_id) REFERENCES pacientes(usuario_id),
    FOREIGN KEY (terapeuta_id) REFERENCES terapeutas(usuario_id)
);

-- --------------------------------------------------------
-- Tabla de Logros (gamificación)
-- --------------------------------------------------------
CREATE TABLE logros (
    id INT AUTO_INCREMENT PRIMARY KEY,
    paciente_id INT NOT NULL,
    tipo ENUM('terapia_completada', 'ejercicio_consecutivo', 'progreso_rapido') NOT NULL,
    fecha DATE NOT NULL,
    icono VARCHAR(255),
    descripcion VARCHAR(200),
    FOREIGN KEY (paciente_id) REFERENCES pacientes(usuario_id)
);