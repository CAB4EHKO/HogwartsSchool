-- Создаем таблицу "People" для хранения информации о людях
CREATE TABLE People (
                        person_id SERIAL PRIMARY KEY,  -- Уникальный идентификатор человека
                        name VARCHAR(255) NOT NULL,   -- Имя человека
                        age INT NOT NULL,             -- Возраст человека
                        has_license BOOLEAN NOT NULL -- Признак наличия прав (true/false)
);

-- Создаем таблицу "Cars" для хранения информации о машинах
CREATE TABLE Cars (
                      car_id SERIAL PRIMARY KEY,       -- Уникальный идентификатор машины
                      make VARCHAR(255) NOT NULL,      -- Марка машины
                      model VARCHAR(255) NOT NULL,     -- Модель машины
                      price DECIMAL(10, 2) NOT NULL    -- Стоимость машины (десятичное число с двумя знаками после запятой)
);