-- ====================================================================
-- 1. CREACIÓN DE CUENTAS (ACCOUNTS)
-- Contraseña para todos: admin123
-- Nota: Si tu Spring Security exige prefijo, cambia 'ADMIN' por 'ROLE_ADMIN'
-- ====================================================================

-- Cuentas de los Administradores
INSERT INTO accounts (email, password, role_type) VALUES ('seba@tikidata.com', '$2a$10$Gxo45LBA5zMi3W449hs8IuWNCsXIggwO.6bz7Vw7KL0tt3ylR0SJ.', 'ADMIN');
INSERT INTO accounts (email, password, role_type) VALUES ('lautaro@tikidata.com', '$2a$10$Gxo45LBA5zMi3W449hs8IuWNCsXIggwO.6bz7Vw7KL0tt3ylR0SJ.', 'ADMIN');
INSERT INTO accounts (email, password, role_type) VALUES ('tobias@tikidata.com', '$2a$10$Gxo45LBA5zMi3W449hs8IuWNCsXIggwO.6bz7Vw7KL0tt3ylR0SJ.', 'ADMIN');
INSERT INTO accounts (email, password, role_type) VALUES ('juan@tikidata.com', '$2a$10$Gxo45LBA5zMi3W449hs8IuWNCsXIggwO.6bz7Vw7KL0tt3ylR0SJ.', 'ADMIN');

INSERT INTO users (first_name, last_name, account_id) VALUES ('Seba', 'Pelletieri', 1);
INSERT INTO users (first_name, last_name, account_id) VALUES ('Lautaro', 'Fernandez', 2);
INSERT INTO users (first_name, last_name, account_id) VALUES ('Tobias', 'Sanchez', 3);
INSERT INTO users (first_name, last_name, account_id) VALUES ('Juan', 'de Siena', 4);


