use [DMA-CSD-V26_10700434]

create table customer(
customerId int,
name varchar(35) not null, 
address varchar(20) not null,
zipCodeCity varchar(20) not null,
phoneNo varchar(15),
email varchar(50),
customerType varchar(7) check(customerType='PRIVATE' or customerType='CLUB') not null,
PRIMARY KEY(customerId),
CONSTRAINT phoneOrEmail check(phoneNo is not null or email is not null)
);

create table product(
productNo int,
name varchar(20) not null,
minStock int check(minStock>=0) not null,
reservedStock int,
productType varchar(20) check(productType='Clothing' or productType='Equipment' or productType='Gun replica'),
PRIMARY KEY(productNo),
CONSTRAINT stockCheck check(minStock<=reservedStock)
);

create table clothing(
size int not null check(size>=0),
colour varchar(20) not null,
productNo int,
PRIMARY KEY (productNo),
CONSTRAINT productEventClothing FOREIGN KEY (productNo) REFERENCES product(productNo) 
	on delete cascade
);

create table equipment(
material varchar(20) not null,
style varchar(20) not null,
productNo int,
PRIMARY KEY (productNo),
CONSTRAINT productEventEquipment FOREIGN KEY (productNo) REFERENCES product(productNo)
	on delete cascade
);

create table GunReplica(
calibre varchar(20) not null,
material varchar(20) not null,
productNo int,
PRIMARY KEY (productNo),
CONSTRAINT productEventGunReplica FOREIGN KEY (productNo) REFERENCES product(productNo)
	on delete cascade
);

create table price(
priceId int,
timestamp date not null,
price decimal(10, 2) not null check(price>=0),
productNo int not null,
PRIMARY KEY ( priceId),
FOREIGN KEY (productNo) REFERENCES product(productNo)
);

create table warehouse(
warehouseNo int,
name varchar(20) not null,
description varchar(30),
PRIMARY KEY(warehouseNo)
);

create table stock(
availableQty int check(availableQty>=0) not null,
productNo int,
warehouseNo int,
PRIMARY KEY(productNo, warehouseNo),
FOREIGN KEY (productNo) REFERENCES product(productNo),
FOREIGN KEY (warehouseNo) REFERENCES warehouse(warehouseNo)
);

create table freight(
freightId int,
method varchar(20),
pickUp varchar(20),
baseCost int check(baseCost >= 0) default 45,
freeThreshold int check(freeThreshold >0),
PRIMARY KEY(freightId)
);

create table invoice(
invoiceId int, 
dueDate datetime not null, 
paymentDate datetime,
PRIMARY KEY(invoiceId)
);

create table saleOrder(
orderNo int, 
date datetime not null default GETDATE(),
deliveryStatus varchar(20),
deliveryDate datetime,
discountGiven int,
customerId int not null,
freightId int,
invoiceId int,
PRIMARY KEY (orderNo),
FOREIGN KEY (customerId) REFERENCES customer(customerId),
FOREIGN KEY (freightId) REFERENCES freight(freightId),
FOREIGN KEY (invoiceId) REFERENCES invoice(invoiceId),
CONSTRAINT deliveryDateCheck check(deliveryDate  >= date)
);

create table orderLineItem(
quantity int check(quantity>0) not null default 1,
orderNo int,
productNo int,
PRIMARY KEY(orderNo, productNo),
CONSTRAINT orderEvent FOREIGN KEY (orderNo) REFERENCES saleOrder(orderNo)
on delete cascade,
FOREIGN KEY (productNo) REFERENCES product(productNo)
);