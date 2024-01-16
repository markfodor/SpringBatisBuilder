CREATE TABLE employees (
    employee_id INT PRIMARY KEY,
    company_id INT FOREIGN KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    department VARCHAR2(50),
    hire_date DATE,
--  hypothetical 'companies' table to show how to use foreign keys
    CONSTRAINT fk_company_id FOREIGN KEY (company_id) REFERENCES companies(company_id)
);