CREATE TABLE IF NOT EXISTS CATEGORY
(
    ID          INTEGER NOT NULL PRIMARY KEY,
    NAME        VARCHAR(255),
    DESCRIPTION VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS PRODUCT
(
    ID                 INTEGER          NOT NULL PRIMARY KEY,
    NAME               VARCHAR(255),
    PRICE              NUMERIC(38, 2),
    DESCRIPTION        VARCHAR(255),
    AVAILABLE_QUANTITY DOUBLE PRECISION NOT NULL,
    CATEGORY_ID        INTEGER,

    CONSTRAINT FK_CATEGORY_ID FOREIGN KEY (CATEGORY_ID) REFERENCES CATEGORY (ID)
);

CREATE SEQUENCE IF NOT EXISTS CATEGORY_SEQ INCREMENT BY 50;
CREATE SEQUENCE IF NOT EXISTS PRODUCT_SEQ INCREMENT BY 50;