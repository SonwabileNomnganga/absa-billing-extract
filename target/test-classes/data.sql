DROP TABLE IF EXISTS BILLING;

CREATE TABLE BILLING (
    ID INT AUTO_INCREMENT,
    TRANSACTION_REFERENCE VARCHAR(250),
    CLIENT_SWIFT_ADDRESS VARCHAR(250),
    MESSAGE_SATUS VARCHAR(250),
    CURRENCY VARCHAR(250),
    AMOUNT NUMBER,
    DATE_TIME_CREATED TIMESTAMP,
    PRIMARY KEY (ID)

);

INSERT INTO BILLING(TRANSACTION_REFERENCE, CLIENT_SWIFT_ADDRESS, MESSAGE_SATUS, CURRENCY, AMOUNT, DATE_TIME_CREATED)
    VALUES ('TEST1', 'TEST1', 'REC', 'ZAR', 500, '20191201');
INSERT INTO BILLING(TRANSACTION_REFERENCE, CLIENT_SWIFT_ADDRESS, MESSAGE_SATUS, CURRENCY, AMOUNT, DATE_TIME_CREATED)
    VALUES ('TEST2', 'TEST2', 'PEND', 'GBP', 230, '20001206');
INSERT INTO BILLING(TRANSACTION_REFERENCE, CLIENT_SWIFT_ADDRESS, MESSAGE_SATUS, CURRENCY, AMOUNT, DATE_TIME_CREATED)
    VALUES ('TEST3', 'TEST3', 'AWMA', 'ZAR', 220, '20001109');
INSERT INTO BILLING(TRANSACTION_REFERENCE, CLIENT_SWIFT_ADDRESS, MESSAGE_SATUS, CURRENCY, AMOUNT, DATE_TIME_CREATED)
    VALUES ('TEST4', 'TEST4', 'COMP', 'ZAR', 900, '20001110');
INSERT INTO BILLING(TRANSACTION_REFERENCE, CLIENT_SWIFT_ADDRESS, MESSAGE_SATUS, CURRENCY, AMOUNT, DATE_TIME_CREATED)
    VALUES ('TEST5', 'TEST5', '', 'ZAR', 100, '20001210');