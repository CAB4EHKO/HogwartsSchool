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

-- Создаем таблицу "Ownership" для отслеживания владения машинами
CREATE TABLE Ownership (
                           ownership_id SERIAL PRIMARY KEY,  -- Уникальный идентификатор записи о владении
                           person_id INT NOT NULL,           -- Идентификатор человека
                           car_id INT NOT NULL,              -- Идентификатор машины
                           FOREIGN KEY (person_id) REFERENCES People (person_id),  -- Внешний ключ к таблице People
                           FOREIGN KEY (car_id) REFERENCES Cars (car_id)          -- Внешний ключ к таблице Cars
);
