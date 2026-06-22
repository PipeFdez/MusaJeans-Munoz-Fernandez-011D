-- =========================================
-- MUSA JEANS - POBLAMIENTO MICROSERVICIOS
-- =========================================

SET FOREIGN_KEY_CHECKS = 0;

-- =========================================
-- ADMIN (bd_admin)
-- =========================================
USE bd_admin;

DELETE FROM admin;

INSERT INTO admin (id, nombre, email, password, rol) VALUES
(1, 'Sebastián Muñoz', 's.munoz@musajeans.cl', 'seba2026', 'SUPER_ADMIN'),
(2, 'Felipe Fernández', 'f.fdez@musajeans.cl', 'pipe2026', 'ADMIN_INVENTARIO');

-- =========================================
-- JEAN (bd_jean)
-- =========================================
USE bd_jean;

DELETE FROM jean;
DELETE FROM modelo;
DELETE FROM marca;

INSERT INTO marca (id, nombre) VALUES
(1, 'Musa Original'),
(2, 'Levis'),
(3, 'Wrangler');

INSERT INTO modelo (id, nombre) VALUES
(1, 'Mom Jeans'),
(2, 'Skinny Fit'),
(3, 'Wide Leg'),
(4, 'Slim Fit');

INSERT INTO jean (id, precio, color, talla, tiro, descripcion, marca_id, modelo_id) VALUES
(1, 29990, 'Azul Claro', 'M', 'Alto', 'Jean clásico mom con calce relajado', 1, 1),
(2, 34990, 'Negro Gastado', 'S', 'Medio', 'Jean Skinny elástico tiro medio', 1, 2),
(3, 32990, 'Azul Índigo', 'L', 'Alto', 'Wide leg bota ancha mezclilla rígida', 2, 3),
(4, 27990, 'Blanco', '38', 'Corto', 'Slim fit clásico mezclilla ligera', 3, 4);



-- =========================================
-- INVENTARIO (bd_inventario)
-- =========================================
USE bd_inventario;

DELETE FROM inventario;

INSERT INTO inventario (id, stock, jean_id) VALUES
(1, 45, 1),
(2, 30, 2),
(3, 15, 3),
(4, 50, 4);

-- =========================================
-- CLIENTE (bd_cliente)
-- =========================================
USE bd_cliente;

DELETE FROM cliente;

INSERT INTO cliente (id, rut, nombre, correo, direccion) VALUES
(1, '11.111.111-1', 'Juan Pérez', 'juan.perez@gmail.com', 'Av. Pajaritos 1234, Maipú'),
(2, '22.222.222-2', 'María Loreto', 'm.loreto@live.cl', 'Providencia 456, Santiago'),
(3, '33.333.333-3', 'Carlos Ruiz', 'carlos.ruiz@outlook.com', 'Gran Avenida 7890, San Miguel');

-- =========================================
-- DESEO (bd_deseo)
-- =========================================

USE bd_deseo;

DELETE FROM deseo;

INSERT INTO deseo (id, cliente_id, jean_id, descripcion) VALUES
(1, 1, 2, 'Me gusta para el invierno'),
(2, 1, 3, 'Esperando que baje de precio'),
(3, 2, 1, 'Regalo de cumpleaños');

-- =========================================
-- CARRITO (bd_carrito)
-- =========================================
USE bd_carrito;

DELETE FROM producto_carrito;
DELETE FROM carrito;

INSERT INTO carrito (id, precio_total, fecha, rut_cliente) VALUES
(10, 64980, '2026-06-21 14:00:00', '11.111.111-1'),
(11, 32990, '2026-06-21 18:30:00', '22.222.222-2');

INSERT INTO producto_carrito (id_producto_carrito, cantidad, precio, id_carrito, id_jean) VALUES
(1, 1, 29990, 10, 1),
(2, 1, 34990, 10, 2),
(3, 1, 32990, 11, 3);

-- =========================================
-- VENTA (bd_venta)
-- =========================================
USE bd_venta;

DELETE FROM detalle_venta;
DELETE FROM venta;

INSERT INTO venta (id_venta, fecha, total, rut_cliente) VALUES
(1001, '2026-06-15 11:20:00', 64980, '11.111.111-1'),
(1002, '2026-06-16 16:45:00', 32990, '33.333.333-3');

INSERT INTO detalle_venta (id_detalle_venta, cantidad, sub_total, id_jean, id_venta) VALUES
(1, 1, 29990, 1, 1001),
(2, 1, 34990, 2, 1001),
(3, 1, 32990, 3, 1002);

-- =========================================
-- ENVÍO (bd_envio)
-- =========================================
USE bd_envio;

DELETE FROM envio;

INSERT INTO envio (id, direccion, estado, fecha_entrega) VALUES
(1, 'Av. Pajaritos 1234, Maipú', 'ENTREGADO', '2026-06-18'),
(2, 'Gran Avenida 7890, San Miguel', 'EN_TRANSITO', '2026-06-23');

-- =========================================
-- PAGO (bd_pago)
-- =========================================
USE bd_pago;

DELETE FROM pago;

INSERT INTO pago (id, monto, metodo_pago, estado, venta_id) VALUES
(1, 64980, 'WEBPAY_DEBITO', 'APROBADO', 1001),
(2, 32990, 'TRANSF_BANCARIA', 'APROBADO', 1002);

-- =========================================
-- REPORTE (bd_reporte)
-- =========================================
USE bd_reporte;

DELETE FROM reporte;

INSERT INTO reporte (id_reporte, fecha_generacion, cantidad_total_ventas, monto_total_recaudado) VALUES
(1, '2026-06-20 23:59:59', 2, 97970);

SET FOREIGN_KEY_CHECKS = 1;