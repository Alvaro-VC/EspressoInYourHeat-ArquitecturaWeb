
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (name) VALUES ('ROLE_CONSUMIDOR');
INSERT INTO roles (name) VALUES ('ROLE_PROPIETARIOCAFETERIA');
INSERT INTO users(username, password) VALUES ('admin1','$2a$12$hQMRf17q6.MqeMEBrZebOOJ3rpBYlSZfNFtNB3WZQcGvCoiMi8lbe');
INSERT INTO users(username, password) VALUES ('consumidor1','$2a$12$hQMRf17q6.MqeMEBrZebOOJ3rpBYlSZfNFtNB3WZQcGvCoiMi8lbe');
INSERT INTO users(username, password) VALUES ('consumidor2','$2a$12$hQMRf17q6.MqeMEBrZebOOJ3rpBYlSZfNFtNB3WZQcGvCoiMi8lbe');
INSERT INTO users(username, password) VALUES ('consumidor3','$2a$12$hQMRf17q6.MqeMEBrZebOOJ3rpBYlSZfNFtNB3WZQcGvCoiMi8lbe');
INSERT INTO users(username, password) VALUES ('consumidor4','$2a$12$hQMRf17q6.MqeMEBrZebOOJ3rpBYlSZfNFtNB3WZQcGvCoiMi8lbe');
INSERT INTO users(username, password) VALUES ('propietariocafeteria1','$2a$12$hQMRf17q6.MqeMEBrZebOOJ3rpBYlSZfNFtNB3WZQcGvCoiMi8lbe');
INSERT INTO users(username, password) VALUES ('propietariocafeteria2','$2a$12$hQMRf17q6.MqeMEBrZebOOJ3rpBYlSZfNFtNB3WZQcGvCoiMi8lbe');
INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (2, 2);
INSERT INTO user_roles (user_id, role_id) VALUES (3, 2);
INSERT INTO user_roles (user_id, role_id) VALUES (4, 2);
INSERT INTO user_roles (user_id, role_id) VALUES (5, 2);
INSERT INTO user_roles (user_id, role_id) VALUES (6, 3);
INSERT INTO user_roles (user_id, role_id) VALUES (7, 3);
-- PROPIETARIO CAFETERIA
INSERT INTO propietario_cafeteria (id, name, apellido, email, user_id)
VALUES
    (1, 'Laura', 'Fernández', 'laura.fernandez@mail.com',6),
    (2, 'Carlos', 'Gutiérrez', 'carlos.gutierrez@mail.com',7);
SELECT setval('propietario_cafeteria_id_seq', (SELECT MAX(id) FROM propietario_cafeteria));

-- CAFETERIA
INSERT INTO cafeteria (id, nombre, descripcion, direccion, telefono, horario_atencion, aforo, propietario_cafeteria_id)
VALUES
    (1, 'Café Aroma', 'Ambiente cálido con repostería artesanal.', 'Av. Primavera 123 - Lima', '987654321', 'Lun-Dom 8:00-21:00', 50, 1),
    (2, 'Café Tostado', 'Café de especialidad y charlas culturales.', 'Calle Lima 456 - Miraflores', '912345678', 'Mar-Dom 9:00-22:00', 70, 2);
SELECT setval('cafeteria_id_seq', (SELECT MAX(id) FROM cafeteria));

-- CONSUMIDOR
INSERT INTO consumidor (id, name, apellido, email, sexo, edad, user_id)
VALUES
    (1, 'Ana', 'Pérez', 'ana.perez@mail.com', 'Femenino', 26, 2),
    (2, 'Luis', 'Ramírez', 'luis.ramirez@mail.com', 'Masculino', 30,3),
    (3, 'Camila', 'Soto', 'camila.soto@mail.com', 'Femenino', 27,4),
    (4, 'Diego', 'López', 'diego.lopez@mail.com', 'Masculino', 29,5);

SELECT setval('consumidor_id_seq', (SELECT MAX(id) FROM consumidor));

-- PREFERENCIA CAFE
INSERT INTO preferencia_cafe (id, cafe, intensidad, nivel_azucar, con_leche, acompanamiento, descripcion_detalle, consumidor_id)
VALUES
    (1, 'Latte', 'Suave', 'Media', true, 'Galletas', 'Prefiere sabores dulces y reuniones sociales', 1),
    (2, 'Espresso', 'Fuerte', 'Baja', false, 'Sin acompañamiento', 'Disfruta del café intenso y las charlas tranquilas', 2);
SELECT setval('preferencia_cafe_id_seq', (SELECT MAX(id) FROM preferencia_cafe));
-- EVENTO
INSERT INTO evento (id, titulo, descripcion, estado, fecha_inicio, fecha_fin, cantidad_participantes, cafeteria_id)
VALUES
    (1, 'Tarde de Café Literario', 'Reunión para amantes de la lectura y el café.', 'ACTIVO', '2025-10-25', '2025-10-25', 20, 1),
    (2, 'Cata de Café Peruano', 'Descubre variedades nacionales de café.', 'FINALIZADO', '2025-09-15', '2025-09-15', 35, 2);
SELECT setval('evento_id_seq', (SELECT MAX(id) FROM evento));
-- ASISTENCIA EVENTO
INSERT INTO asistencia_evento (id, fecha_registro, consumidor_id, evento_id)
VALUES
    (1, '2025-10-10', 1, 1),
    (2, '2025-09-10', 2, 2);
SELECT setval('asistencia_evento_id_seq', (SELECT MAX(id) FROM asistencia_evento));
-- CAFETERIA FAVORITA
INSERT INTO cafeteria_favorita (id, favorita, comentario, consumidor_id, cafeteria_id)
VALUES
    (1, true, 'Excelente ambiente y atención.', 1, 1),
    (2, true, 'Me encanta su variedad de cafés.', 2, 2);
SELECT setval('cafeteria_favorita_id_seq', (SELECT MAX(id) FROM cafeteria_favorita));
-- CHAT
INSERT INTO chat (id, fecha_match, participante1_id, participante2_id)
VALUES
    (1, '2025-10-12', 1, 2),
    (2, '2025-10-15', 3, 4);
SELECT setval('chat_id_seq', (SELECT MAX(id) FROM chat));
-- MENSAJE
INSERT INTO mensaje (id, text, fecha_enviado, chat_id, remitente_id)
VALUES
    (1, '¡Hola! ¿Vas al evento del Café Literario?', '2025-10-13', 1, 1),
    (2, 'Sí, nos vemos ahí', '2025-10-14', 1, 2),
    (3, '¿Qué tipo de café te gusta más?', '2025-10-15', 2, 3),
    (4, 'El espresso doble, sin duda 😄', '2025-10-15', 2, 4);

SELECT setval('mensaje_id_seq', (SELECT MAX(id) FROM mensaje));