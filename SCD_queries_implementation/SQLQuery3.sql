IF EXISTS (SELECT * FROM sysobjects WHERE name = 'dimproduit_SCD1' AND xtype = 'U')
BEGIN
    DROP TABLE dimproduit_SCD1;
END;

CREATE TABLE dimproduit_SCD1 (
    key_u INT,
    ProductName VARCHAR(18),
    ProductSubCategory VARCHAR(20),
    ProductPrice VARCHAR(12),
    id INT
);

select * from dimproduit_SCD1 ;
UPDATE dimproduit
SET ProductPrice = 0.11
WHERE id = 473;
select * from dimproduit_SCD1 where  ProductPrice =0.11 ;
truncate table dimproduit_SCD1;

select * from dimproduit;


Exec sp_help  'DmCustomer';

IF EXISTS (SELECT * FROM sysobjects WHERE name = 'Customer_SDC2' AND xtype = 'U')
BEGIN
    DROP TABLE Customer_SDC2;
END;
--creation SDC Table pour monitoration 
CREATE TABLE Customer_SDC2 (
    id INT NOT NULL, 
	key_cus int not null,
    CustomerName VARCHAR(24) NOT NULL,
    CustomerEmail VARCHAR(30) NOT NULL,
    CustomerAddress VARCHAR(38) NOT NULL,
    CustomermoreAddress VARCHAR(40) NOT NULL,
    CustomerPhone VARCHAR(40) NOT NULL,
    CustomerSegment VARCHAR(17) NOT NULL,
    StartDate DATE NOT NULL,          
    EndDate DATE,                 
    Current_active Bit NOT NULL          
);

SELECT * FROM DmCustomer;

Update DmCustomer 
set CustomerPhone='111111111111111'
where id=1413989740;


select * from DmCustomer where id=1413989740;

select * from Customer_SDC2 where id=1413989740;





IF EXISTS (SELECT * FROM sysobjects WHERE name = 'dimproduit_SCD3' AND xtype = 'U')
BEGIN
    DROP TABLE dimproduit_SCD3;
END;

CREATE TABLE dimproduit_SCD3 (
    key_Produit INT NOT NULL,
    ProductName VARCHAR(18),
    ProductCategory VARCHAR(20),
    ProductPrice VARCHAR(12),
    id INT,
	PreviousProductName VARCHAR(18),
);
select * from dimproduit_SCD3;
select * from DimProduit;