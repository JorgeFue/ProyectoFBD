drop database alquilerdeautos;
create database AlquilerDeAutos;
use AlquilerDeAutos;
create table Aseguradora (
	CveAseg varchar(12) not null,
    Nombre varchar (25),
    Telefono1 varchar (12),
    Telefono2 varchar(12),
    constraint aseguradoraPK primary key (CveAseg));

create table Cobertura(
	CveCobertura int not null,
    Descripcion varchar(50),
    constraint coberturaPK primary key (CveCobertura));

create table Seguro(
	NumeroPoliza varchar(15) not null,
    Deducibles decimal(5,2),
    CveAseg varchar(12) not null,
    constraint seguroPK primary key (NumeroPoliza),
    constraint seguroFK1 foreign key (CveAseg) references Aseguradora (CveAseg));


create table CoberturaAmparada(
	NumeroPoliza varchar(15) not null,
    CveCobertura int not null,
    constraint cubrePK primary key (NumeroPoliza, CveCobertura),
    constraint cubreFK1 foreign key (NumeroPoliza) references Seguro(NumeroPoliza),
    constraint cubreFK2 foreign key (CveCobertura) references Cobertura(CveCobertura));


create table Marca(
	NombreMarca varchar (15) not null,
    constraint marcaPK primary key (NombreMarca));


create table Modelo(
	NombreModelo varchar (25) not null,
    NombreMarca varchar(15) not null,
    constraint modeloPK primary key (NombreModelo, NombreMarca),
    constraint modeloFK foreign key (NombreMarca) references Marca(NombreMarca));


create table TipoVehiculo(
	CveTipo int not null,
    Tipo varchar(20),
    constraint tipoVPK primary key (CveTipo));


create table Vehiculo(
	Matricula varchar (7) not null,
    Transmision varchar(10), 
    Año int,
    Disponible varchar(2),
    GPS varchar(2),
    CostoDia decimal (5,2),
    PrecioArre decimal(16,2),
    NumeroPoliza varchar(15),
    NombreModelo varchar (25) not null,
    NombreMarca varchar(15) not null,
    CveTipo int not null,
    constraint autoPK primary key (Matricula),
    constraint autoFK1 foreign key ( NumeroPoliza) references Seguro (NumeroPoliza),
    constraint autoFK2 foreign key (NombreModelo, NombreMarca) references modelo (NombreModelo, NombreMarca),
    constraint autoFK3 foreign key (CveTipo) references TipoVehiculo (CveTipo));


create table Estado (
	NombreEstado varchar(20),
    constraint estadoPK primary key (NombreEstado));

create table AlquilerAutos (
	CveAgencia int not null,
    Direccion varchar (100),
    Telefono varchar(10),
    constraint agenciaPK primary key (CveAgencia));


create table Dispone (
	CveAgencia int not null,
    Matricula varchar(7) not null,
    constraint disponePK primary key (CveAgencia, Matricula),
    constraint disponeFK1 foreign key (CveAgencia) references AlquilerAutos (CveAgencia),
    constraint disponeFK2 foreign key (Matricula) references Vehiculo (Matricula));


create table Distribuidor(
	CveAgencia int not null,
    NombreEstado varchar(20) not null,
    constraint distribuidorPK primary key (CveAgencia, NombreEstado),
    constraint distribuidorFK1 foreign key (CveAgencia) references AlquilerAutos (CveAgencia),
    constraint distribuidorFK2 foreign key (NombreEstado) references Estado (NombreEstado));

create table Puesto (
	CvePuesto int not null,
    Sueldo decimal (16,2),
    Cargo varchar(50),
    constraint puestoPK primary key (CvePuesto));


create table Empleado(
	CveEmpleado varchar(5) not null, 
    Nombre varchar (100),
    CURP varchar (20),
    Direccion varchar (100),
    Telefono varchar(15),
    CvePuesto int,
    CveAgencia int not null,
    Ciudad varchar(25),
    NombreEstado varchar(20) not null,
    constraint empleadoPK primary key (CveEmpleado),
    constraint empleadoFK1 foreign key (CvePuesto) references Puesto (CvePuesto),
    constraint empleadoFK2 foreign key (CveAgencia) references AlquilerAutos (CveAgencia),
    constraint empleadoFK3 foreign key (NombreEstado) references Estado (NombreEstado));


create table Cliente(
	RFC varchar(15) not null,
    Nombre varchar(100),
    Telefono varchar(15),
    Direccion varchar(100),
    Ciudad varchar(25),
    NombreEstado varchar(20) not null,
    constraint clientePK primary key (RFC),
    constraint clienteFK foreign key (NombreEstado) references Estado (NombreEstado));


create table Datos(
	CveDatos int not null,
    RFC varchar(15),
    NumCheque int,
    Referencia varchar(15),
    TitularTarjeta varchar(100),
    CodigoSeg int,
    NumTarjeta int,
    NumeroCuenta int,
    Banco varchar(25),
    constraint datosPK primary key (CveDatos, RFC),
    constraint datosFK foreign key (RFC) references Cliente(RFC));


create table Oferta(
	Nombre varchar(25),
    Descuento decimal(2,2),
    constraint ofertaPK primary key (Nombre));


create table FormaPago(
	TipoPago varchar(25),
    constraint pagoPK primary key (TipoPago));


create table Alquiler(
	CveAlquiler int not null,
    CveAgenciaRenta int not null,
    CveAgenciaDev int not null,
    Matricula varchar(7) not null,
    RFC varchar(15) not null,
    TipoPago varchar(25),
    FechaRenta date,
    FechaDev date,
    HoraRenta varchar(5),
    HoraDev varchar(5),
    CostoTotal decimal(16,2),
    constraint alquilerPK primary key (CveAlquiler),
    constraint alquilerFK1 foreign key (CveAgenciaRenta) references alquilerautos (CveAgencia),
	constraint alquilerFK2 foreign key (CveAgenciaDev) references alquilerautos (CveAgencia),
	constraint alquilerFK3 foreign key (Matricula) references vehiculo (Matricula),
	constraint alquilerFK4 foreign key (RFC) references Cliente (RFC),
	constraint alquilerFK5 foreign key (TipoPago) references FormaPago (TipoPago));


create table Promocion(
	CveAlquiler int not null,
    Nombre varchar(25),
    constraint pormoPK primary key (CveAlquiler, Nombre),
    constraint promoFK1 foreign key (CveAlquiler) references Alquiler(CveAlquiler),
    constraint promoFK2 foreign key (Nombre) references Oferta(Nombre));


insert into aseguradora (CveAseg, Nombre, Telefono1, Telefono2) values 
	('HSE701218532','HDI Seguros','8000000434','8007246434'),
    ('GNP9211254P0','GNP Seguros',' 5552279000','52279000'),
    ('AIG799123542','AIG Seguros','8000011300','5554884700'),
    ('ASE931116231','AXA Seguros','8009001292','8009119999');


insert into Cobertura (CveCobertura, Descripcion) values
	(1,'Transporte adicional'),
    (2,'Servicios de carretera '),
    (3,'Accidentes '),
    (4,'Robo total del vehículo'),
    (5,'Daños causados durante la conducción'),
    (6,'Explosión externa o incendio'),
    (7,'Robo de piezas y vandalismo');


insert into seguro (NumeroPoliza,Deducibles, CveAseg) values
	('1994205ASG',0.60,'HSE701218532'),
    ('3549274RFP',0.50,'HSE701218532'),
    ('5720371XYZ',0.45,'HSE701218532'),
    ('826846HEI',0.45,'HSE701218532'),
    ('1936482HSP',0.45,'HSE701218532'),
    ('1010192WHD',0.45,'HSE701218532'),
    ('2038451JDI',0.45,'HSE701218532'),
    ('1092743BDY',0.45,'HSE701218532'),
    ('8273823BDE',0.45,'HSE701218532'),
    ('9127372BDD',0.45,'HSE701218532'),
    ('5482921SBC',0.75,'GNP9211254P0'),
    ('1730057PRA',0.65,'GNP9211254P0'),
    ('1234567ANN',0.50,'GNP9211254P0'),
    ('7638472BCW',0.50,'GNP9211254P0'),
    ('9246283BCN',0.50,'GNP9211254P0'),
    ('2273728MSO',0.50,'GNP9211254P0'),
    ('9237462BDJ',0.50,'GNP9211254P0'),
    ('1301823QOI',0.50,'GNP9211254P0'),
    ('1874727DBY',0.50,'GNP9211254P0'),
    ('7382819BYB',0.55,'AIG799123542'),
    ('1765432DEA',0.55,'AIG799123542'),
    ('1173384BCO',0.55,'AIG799123542'),
    ('1826382BQQ',0.55,'AIG799123542'),
    ('1994782SEC',0.55,'AIG799123542'),
    ('8844663UCJ',0.55,'AIG799123542'),
    ('6767472BCD',0.55,'AIG799123542'),
    ('3476478VCD',0.55,'AIG799123542'),
    ('4768243BEE',0.55,'AIG799123542'),
    ('6663666DEO',0.540,'AIG799123542'),
    ('5355567ALO',0.80,'ASE931116231'),
    ('4535732PRF',0.69,'ASE931116231'),
    ('1995225GSG',0.51,'ASE931116231'),
    ('7576478GHB',0.80,'ASE931116231'),
    ('2376239SEE',0.80,'ASE931116231'),
    ('2327628DCB',0.80,'ASE931116231'),
    ('1982376VNQ',0.80,'ASE931116231'),
    ('1216328NSX',0.80,'ASE931116231'),
    ('2376129XNU',0.80,'ASE931116231'),
    ('2387328CEB',0.80,'ASE931116231');


insert into coberturaamparada (NumeroPoliza, CveCobertura) values
	('1994205ASG',1),
    ('1994205ASG',2),
    ('3549274RFP',4),
	('3549274RFP',3),
    ('5720371XYZ',2),
    ('5720371XYZ',5),
    ('826846HEI',1),
    ('826846HEI',3),
    ('1936482HSP',2),
    ('1936482HSP',3),
    ('1010192WHD',1),
    ('1010192WHD',3),
    ('2038451JDI',7),
    ('2038451JDI',2),
    ('1092743BDY',2),
    ('1092743BDY',3),
    ('8273823BDE',4),
    ('8273823BDE',3),
    ('9127372BDD',3),
	('9127372BDD',1),
    ('5482921SBC',3),
    ('5482921SBC',5),
    ('1730057PRA',3),
    ('1730057PRA',2),
    ('1234567ANN',3),
    ('7638472BCW',2),
    ('7638472BCW',3),
    ('9246283BCN',2),
    ('9246283BCN',5),
    ('2273728MSO',3),
    ('2273728MSO',5),
    ('9237462BDJ',2),
	('9237462BDJ',3),
    ('1301823QOI',1),
    ('1301823QOI',2),
    ('1874727DBY',3),
    ('7382819BYB',2),
    ('7382819BYB',3),
    ('7382819BYB',5),
    ('1765432DEA',4),
    ('1765432DEA',7),
    ('1173384BCO',1),
    ('1173384BCO',2),
    ('1826382BQQ',3),
    ('1994782SEC',3),
    ('8844663UCJ',1),
    ('8844663UCJ',2),
    ('6767472BCD',4),
    ('6767472BCD',3),
    ('3476478VCD',7),
    ('3476478VCD',4),
    ('4768243BEE',2),
    ('4768243BEE',4),
    ('6663666DEO',5),
    ('6663666DEO',4),
    ('5355567ALO',2),
    ('5355567ALO',4),
    ('4535732PRF',6),
    ('4535732PRF',3),
    ('1995225GSG',6),
    ('1995225GSG',4),
    ('7576478GHB',3),
    ('2376239SEE',3),
    ('2327628DCB',2),
    ('2327628DCB',4),
    ('1982376VNQ',4),
    ('1216328NSX',2),
    ('2376129XNU',2),
    ('2387328CEB',3);


insert into marca (NombreMarca) values
	('KIA'),
    ('MAZDA'),
    ('HONDA'),
    ('NISSAN'),
    ('CHEVROLET'),
    ('FORD'),
    ('FIAT'),
    ('JEEP'),
    ('TOYOTA'),
    ('VOLKSWAGEN');


insert into modelo (NombreModelo, NombreMarca) values
	('Sportage','KIA'),
    ('Soul','KIA'),
    ('Niro','KIA'),
    ('Sorento','KIA'),
    ('Kia Optima','KIA'),
    ('Mazda 2','MAZDA'),
    ('Mazda MX-5','MAZDA'),
    ('Mazda 3 Sedan','MAZDA'),
    ('Mazda 3 HatchBack','MAZDA'),
    ('Mazda 6','MAZDA'),
    ('CR-V','HONDA'),
    ('HR-V','HONDA'),
    ('Civic','HONDA'),
    ('Fit','HONDA'),
    ('City','HONDA'),
    ('GT-R','NISSAN'),
    ('Altima','NISSAN'),
    ('Sentra','NISSAN'),
    ('March','NISSAN'),
    ('Tsuru','NISSAN'),
    ('Beat','CHEVROLET'),
    ('Spark','CHEVROLET'),
    ('Aveo','CHEVROLET'),
    ('Sonic','CHEVROLET'),
    ('Camaro','CHEVROLET'),
    ('Figo Aspire','FORD'),
    ('Figo Sedan','FORD'),
    ('Fiesta','FORD'),
    ('Focus','FORD'),
    ('Mustang','FORD'),
    ('Mobi','FIAT'),
    ('Uno','FIAT'),
    ('Palio Sporting','FIAT'),
    ('FIAT 500X','FIAT'),
    ('Abarth','FIAT'),
    ('Renegade','JEEP'),
    ('Compass','JEEP'),
    ('Cherokee','JEEP'),
    ('Wrangler','JEEP'),
    ('Jeep','JEEP'),
    ('Avanza','TOYOTA'),
    ('Yaris 17','TOYOTA'),
    ('Prius','TOYOTA'),
    ('Sienna','TOYOTA'),
    ('Rava','TOYOTA'),
    ('Beetle','VOLKSWAGEN'),
    ('CrossFox','VOLKSWAGEN'),
    ('Gol','VOLKSWAGEN'),
    ('Polo','VOLKSWAGEN'),
    ('Jetta','VOLKSWAGEN');


insert into tipovehiculo (CveTipo, Tipo) values 
	(1,'Económico'),
    (2,'Compacto'),
    (3,'Grande'),
    (4,'Mediano'),
    (5,'Especial'),
    (6,'De Lujo'),
    (7,'Descapotable'),
    (8,'Minivan'),
    (9,'Todo Terreno'),
    (10,'Deportivo');


insert into vehiculo (Matricula, Transmision, Año, Disponible, GPS, CostoDia, PrecioArre, NumeroPoliza, NombreModelo, NombreMarca, CveTipo) values
	('GAC3466',	'Estandar'	,2015,'SI','SI',350.00,	15599.00, null,'Sportage','KIA',1),
   	('HRU5937',	'Automatico',2017,'NO','SI',390.00,	16599.00,'1994205ASG','Sportage','KIA',2),
    ('OFT2496',	'Estandar'	,2009,'NO','NO',250.00,9200.00,'3549274RFP', 'Soul','KIA',2),
    ('QRT0668','Hibrido',2018,'SI','SI',750.00,35599.00, null, 'Soul','KIA',1),
    ('VGY7509',	'Estandar'	,2015,'NO','SI',350.00,13400.00,	'5720371XYZ','Kia Optima','KIA',2),
    ('AYE7293',	'Hibrido',2017,'NO',	'SI',700.00,21500.00,'826846HEI', 'Kia Optima','KIA',3),
    ('CNF7437',	'Automatico',2017,'NO','SI',590.00,	16750.00,'1936482HSP','Mazda 3 Sedan','MAZDA',	4),
    ('LDL3990',	'Estandar'	,2017,'SI','SI',550.00,12599.00, null,'Mazda 3 HatchBack','MAZDA',10),
    ('LOL3900',	'Automatico'	,2017,'SI','SI',610.00,15599.00, null,'Mazda 3 HatchBack','MAZDA',10),
    ('GIF9047',	'Estandar'	,2017,'NO','SI',350.00,13400.00, '1010192WHD', 'Mazda 6','MAZDA',10),
    ('PER3940',	'Estandar'	,2017,'SI','SI',870.00,	21500.00, '2038451JDI','Mazda 6','MAZDA',10),
    ('MCM2848','Automatico',2011,'NO','NO',590.00,16750.00, '1092743BDY',	'Civic','HONDA',3),
    ('NON3846','Estandar',2016,'SI','SI',550.00,12599.00, null, 'Fit','HONDA',4),
    ('PLA8374',	'Automatico',2016,'SI','SI',610.00,15599.00,'8273823BDE','Fit','HONDA',4),
    ('PTA6394',	'Estandar'	,2015,'NO','SI',350.00,13400.00,'9127372BDD', 'City','HONDA',10),
    ('PME8384',	'Hibrido',2017,	'SI',	'SI',870.00,	21500.00, null, 'City','HONDA',10),
    ('ARR9385',	'Automatico',2017,'NO','SI',590.00,16750.00,'5482921SBC','Sentra','NISSAN',1),
    ('FLA4934',	'Estandar'	,2016,'NO','SI',550.00,12599.00,'1730057PRA', 'Sentra','NISSAN',2),
    ('SUP3048',	'Automatico',2016,'SI','SI',610.00,15599.00,'1234567ANN', 'March','NISSAN',2),
    ('WIL3948',	'Estandar'	,2015,'NO','SI',350.00,13400.00, null, 'Tsuru','NISSAN',3),
    ('BLA3482',	'Hibrido',2017,'SI','SI',870.00,21500.00, null,	'Tsuru','NISSAN',2),
    ('BAT8420',	'Estandar'	,2015,'SI','SI',350.00,	15599.00,'7638472BCW','Beat','CHEVROLET',4),
   	('SUP3482',	'Automatico',2017,'NO','SI',390.00,	16599.00,'9246283BCN',	'Beat','CHEVROLET',	3),
    ('WON5930','Estandar',2012,'SI','NO',250.00,9200.00, '2273728MSO', 'Spark','CHEVROLET',2),
    ('FLA8934',	'Hibrido',2018,	'SI',	'SI',950.00,	35599.00, null, 'Spark','CHEVROLET',2),
    ('AQU3849','Automatico',2017,'NO','SI',590.00,17000.00,'9237462BDJ','Aveo','CHEVROLET',	3),
    ('ARS3492',	'Automatico',2017,'NO','SI',590.00,16750.00,'1301823QOI', 'Fiesta','FORD',4),
    ('SPE3347',	'Estandar'	,2012,'SI','NO',550.00,12599.00,	'1874727DBY',	'Focus','FORD',2),
    ('BLU3920',	'Automatico'	,2016,'SI','SI',610.00,	15599.00,	'7382819BYB',	'Focus','FORD',2),
    ('WGI3845','Estandar'	,2015,'NO','SI',350.00,13400.00,	'1765432DEA',	'Mustang','FORD',1),
    ('ATO3842','Hibrido',2017,'SI','SI',870.00,21500.00, '1173384BCO', 'Mustang','FORD',1),
    ('JOK3849',	'Estandar'	,2013,'SI','NO',350.00,15599.00, null,'Mobi','FIAT',1),
   	('LEX8345',	'Automatico'	,2017,'NO','SI',390.00,16599.00, '1826382BQQ','Mobi','FIAT',1),
    ('CHI1039','Estandar',2013,	'SI',	'NO',250.00,9200.00, null, 'Uno','FIAT',3),
    ('ZOO4829','Hibrido',2018,	'SI',	'SI',950.00,35599.00, '1994782SEC','Uno','FIAT',4),
    ('REV3829',	'Automatico',2018,'NO','SI',590.00,17000.00,	'8844663UCJ', 'Palio Sporting','FIAT',4),
    ('UNA8328','Automatico',2017,'NO','SI',590.00,16750.00, '6767472BCD', 'Cherokee','JEEP',	5),
    ('TOL2854',	'Estandar'	,2016,'NO','SI',550.00,12599.00,	'3476478VCD','Wrangler','JEEP',4),
    ('TIJ2438',	'Automatico',2016,'NO','SI',610.00,15599.00,'4768243BEE','Wrangler','JEEP',	4),
    ('SAN3483','Estandar',2015,'NO','SI',350.00,	13400.00,'6663666DEO','Jeep','JEEP',2),
    ('QRO2203','Hibrido',2017,'NO',	'SI',870.00,	21500.00,'5355567ALO','Jeep','JEEP',3),
    ('LEO3849',	'Estandar'	,2015,'NO','SI',350.00,15599.00, '4535732PRF',	'Avanza','TOYOTA',1),
   	('PUE8293',	'Automatico'	,2017,'NO','SI',390.00,16599.00,	'1995225GSG',	'Avanza','TOYOTA',	1),
    ('PAC8392',	'Estandar'	,2012,'NO','NO',250.00,9200.00, '7576478GHB', 'Yaris 17','TOYOTA',2),
    ('TIG8302',	'Hibrido',2014,	'SI',	'SI',950.00,	35599.00,'2376239SEE',	'Yaris 17','TOYOTA',2),
    ('MON1972','Automatico',2017,'NO','SI',590.00,17000.00, '2327628DCB', 'Prius','TOYOTA',	2),
    ('ARN2487','Automatico',2017,	'NO','SI'	,590.00,16750.00,	'1982376VNQ','Gol','VOLKSWAGEN',2),
    ('BUR4344',	'Estandar'	,2016,'SI','SI',550.00,12599.00, '1216328NSX', 'Polo','VOLKSWAGEN',3),
    ('NJS3429',	'Automatico',2017,'NO','SI',590.00,	16750.00,	'2376129XNU','Gol','VOLKSWAGEN',2),
    ('QWP3419','Estandar'	,2016,'SI','SI',550.00,12599.00, '2387328CEB', 'Polo','VOLKSWAGEN',4);


insert into estado(NombreEstado) values
	('Aguascalientes'),
    ('Baja California'),
    ('Baja California Sur'),
    ('Campeche'),
    ('Chiapas'),
    ('Chihuahua'),
    ('Ciudad de Mexico'),
    ('Coahuila'),
    ('Colima'),
    ('Durango'),
    ('Guanajuato'),
    ('Guerrero'),
    ('Hidalgo'),
    ('Jalisco'),
    ('Mexico'),
    ('Michoacan'),
    ('Morelos'),
    ('Nayarit'),
    ('Nuevo Leon'),
    ('Oaxaca'),
    ('Puebla'),
    ('Queretaro'),
    ('Quintana Roo'),
    ('San Luis Potosi'),
    ('Sinaloa'),
    ('Sonora'),
    ('Tabasco'),
    ('Tamaulipas'),
    ('Tlaxcala'),
    ('Veracruz'),
    ('Yucatana'),
    ('Zacatecas');


insert into AlquilerAutos(CveAgencia,Direccion, Telefono) values
	(1,'Fco Mendoza #130 Col. Centro','4613749265'),
    (2,'Garcia Cubas #1293 Col. Fco Villa','8462846372'),
    (3,'Av Lazaro Cardenas #3000 Col. Centro','2745337567'),
    (4,'Maestros #856 Col. Profesiones','4628462846'),
    (5,'Laurel #2888 Col. Los Pinos','2735482946'),
    (6,'Si Nos Dejan #5729 Col. Centro','5739463729'),
    (7,'Amador Salazar #100 Col. Bosques de la Alameda','4920564825'),
    (8,'Caudillos del Sur #623 Col. Del Bosque','5284659364'),
    (9,'Pipila #3764 Col. Centro','5285036573'),
    (10,'Sor Juana Ines de la Cruz S/N Col. Revolucion','6598394536');


insert into distribuidor (CveAgencia, NombreEstado) values 
	(1,'Durango'),
    (2,'Guanajuato'),
    (3,'Aguascalientes'),
    (4,'Hidalgo'),
    (5,'Jalisco'),
    (6,'Mexico'),
    (7,'Queretaro'),
    (8,'Morelos'),
    (9,'Ciudad de Mexico'),
    (10,'Oaxaca');


insert into Puesto (CvePuesto, Sueldo, Cargo) values 
	(1,25000.00, 'Gerente'),
    (2,17500.00, 'Contador'),
    (3, 10000.00, 'Atencion A Clientes'),
    (4, 4500.00, 'Intendencia'),
    (5, 8600.00, 'Recursos Humanos');


insert into empleado (CveEmpleado, Nombre, CURP, Direccion,Telefono, CvePuesto, CveAgencia, Ciudad, NombreEstado) values
    ('AHFT4','Estefania Gachuz Chavez','FTBU768B87BI','La Media Vuelta #293 Col. El Cantar','4617349372', 1, 2, 'Guanajuato','Guanajuato'),
    ('FTV6V','Ana Gonzalez Romero','AHD83957HDNE','Abasolo #442 Col. Las Americas','4612859384', 3, 2, 'Guanajuato','Guanajuato'),
    ('GEUT5','Albertano Perez Gomez','HG453995UNH','Emiliano Zapata #767 Col. Revolucion','7629364856', 4, 2, 'Guanajuato','Guanajuato'),
    ('DMEL4','Victor Perez Machuca','ABSB756B87DS','20 de Noviembre #293 Col. Paseo del Ferrocaril','5472946509', 1, 1, 'Victoria de Durango','Durango'),
    ('FAOE3','Emilio GonzalezTorres','BCTA735B87SD','5 de Mayo #293 Col. Los Naranjos','2648354677', 2, 1, 'Victoria de Durango','Durango'),
    ('34NNI','Alejandra Vazquez Lopez','CDUZ778B87SD','Plan de Iguala #293 Col. Independencia','9745362865', 3, 3, 'Aguascalientes','Aguascalientes'),
    ('SPO34','Alejandra Godinez Chavez','DEVY775B87HF','Liberta #293 Col. Centro','5274539564', 1, 3, 'Aguascalientes','Aguascalientes'),
    ('CMW34','Giovani Montoya Torres','EFXX734B87SF','Adolfo Lopez #293 Col. Centro','9265845322', 3, 4, 'Pachuca','Hidalgo'),
    ('EKJP3','Laura Jauregui Perez','FGYV735B87DS','Maria Enriqueta #293 Col. Inseurgentnes','7452845376', 1, 4, 'Pachuca','Hidalgo'),
    ('KJAO3','Ricardo Santoyo Lara','GHZU358B87JR','Antonio Nantes #293 Col. Constituyentes','1234532418', 3, 5, 'Guadalajara','Jalisco'),
    ('239IN','Axel Morales Flores','HIBT7128B87DG','Juan Diego #293 Col. Romeral','9363002766', 1, 5, 'Guadalajara','Jalisco'),
    ('MSEJ3','Guadalupe Flores Reyes','IJBD328B87AF','Josefa Ortiz #293 Col. Centro','6664566636', 3, 6, 'Toluca','Mexico'),
    ('SSPAE','Mariana Hurtado Palacio','JKCR138B87HG','Miguel Hidalgo #293 Col. Centro','7654321098', 1, 6, 'Toluca','Mexico'),
    ('XNJN3','Bruno Diaz Vazquez','KLDQ768B87DG','Jose Morelos #293 Col. Progreso','1029384756', 3, 7, 'Queretaro','Queretaro'),
    ('APIOD','Bartolome Reyna Moreno','LMEP738B87DG','Venustiano Carranza #293 Col. Latino','2837465018', 1, 7, 'Queretaro','Queretaro'),
    ('23982','Blanca Jaramillo Gonzalez','MNFO658B87RJ','Juan Pablo #293 Col. Gobernadores','3746564738', 3, 8, 'Cuernavaca','Morelos'),
    ('CMKI3','Roberto Diaz Mendez','NOGN248B87EE','Amador Salazar #293 Col. Centro','5647382910', 1, 8, 'Cuernavaca','Morelos'),
    ('SNJ30','Gloria Martinez Escobedo','OPHM258B87FF','Moctezuma #293 Col. Laureles','7584930194', 3, 9, 'Tlalpan','Ciudad de Mexico'),
    ('W2PO3','Melissa Romero Ferrel','PQIL528B87GG','Tenochtitlan #293 Col. Centro','1992288347', 1, 10, 'Oaxaca','Oaxaca'),
    ('EBKJ3','Anabel Hernandez Perez','QRJK258B87HH','Aztecas #293 Col. Fco Juarez','6655778844', 3,10, 'Oaxaca','Oaxaca');


insert into cliente (RFC , Nombre, Telefono, Direccion, Ciudad, NombreEstado) values
    ('FHSURYEH1724TRT','Roberto Perez Manriquez','5284628375','20 de Noviembre #100 Col. Alameda','Victoria de Durango','Durango'),
    ('HYRMI8467VGHETY','Miguel Galindo Gimenez','3759672465','Aztecas #200 Col. Cultura','Victoria de Durango','Durango'),
    ('JGEWODSF73660SS','Juan Barrios Fernandez','82716300197','Victoria #107 Col. Puerto','Victoria de Durango','Durango'),
    ('ASDADWDJCHS323R','Francisco Ortiz Perez','7263840173','Carrizo #35 Col. La Venada','Guanajuato','Guanajuato'),
    ('NJDLSO88263DKW8','Manuel Martinez Hernandez','1935243749','Norte America #320 Col. La Hacienda','Guanajuato','Guanajuato'),
    ('KDSIDI376EGBDKS','Manuel Chavez Gonzalez','3726354789','Paseo de los Naranjos #348 Col. Los Naranjos ','Guanajuato','Guanajuato'),
    ('SDJDWDOW8237WJN','Felipe Camargo Llamas','1726354891','San Vicente #208 Col. La Fundicion','Aguascalientes','Aguascalientes'),
    ('DSAOWEI8926HBDS','Alvaro Gonzalez Lozano','5647382901','Beltran #107 Col. La Troje','Aguascalientes','Aguascalientes'),
    ('AAPQPEKENJU792R','Antonio Jimenez Gomez','1829374658','Revolucion #103 Col. Palo Alto','Aguascalientes','Aguascalientes'),
    ('SAALJDIW920N23E','Maria Hernandez Juarez','6473829364','Efren Rebolledo #8 Col. Canada Chica','Pachuca','Hidalgo'),
    ('JSJDLSD7366HDHS','Jose Camargo Fernandez','0182736344','Pinos #3 Col. Alvarado','Pachuca','Hidalgo'),
    ('JDFKLDJSIQ223YQ','Mario Sanchez Leon','7263748912','Rio Panuco #66 Col. Santa Anna','Pachuca','Hidalgo'),
    ('OWQEQIOWEH827HD','Juan Martinez Martinez','1928374654','La Ley #3060 Col. Monraz','Guadalajara','Jalisco'),
    ('NJSADHJAOPWEU72','Daniel Narancio Araujo','7632784736','Progreso #388 Col. America','Guadalajara','Jalisco'),
    ('DSADH6236WEHLPP','Oscar Carrillo Molina','5425367891','Banderas #15 Col. La Palma','Guadalajara','Jalisco'),
    ('NCJDKSFHKJDF755','Luis Castro Sanchez','7654296345','Rubens #38 Col. La Planta','Tlalpan','Ciudad de Mexico'),
    ('DJSALDAISU72366','Paulina Cisneros Najar','5532617892','Cedros #45 Col. Buenavista','Tlalnepantla','Ciudad de Mexico'),
    ('DSLADJALD8826SD','Luis Alvarez Cruz','3384926534','Plata #355 Col. Palmitas','Tlalnepantla','Ciudad de Mexico'),
    ('CDSDASI3YWTW66E','Carlos Cruz Diaz','2728390164','Peñón de Gribaltar #101 Col. Las Peñas','Queretaro','Queretaro'),
    ('DSALDIW9720JFHD','Omar Fuentes Mena','6672839467','Graciano Sánchez #10 Col. Casa Blanca','Queretaro','Queretaro'),
    ('SADCYTEBHYCOO06','Norma Figueroa Salmoran','8876253647','Emilanio Zapata #33 Col. Temixco','Cuernavaca','Morelos'),
    ('SDPOQEWIQW68129','Jaime Florez Martinez','9927354673','Alicia #149 Col. Vista Hermosa','Cuernavaca','Morelos'),
    ('WEQPWN346LSÑSAI','Carlos Andrade Galindo','8836257182','Pte. # 59 Col. Industrial Vallejo','Toluca','Mexico'),
    ('LSNCMU2789HHSIO','Ricardo Garcia Campos','5537281634','Alamo #8 Col. Tlanepantla','Toluca','Mexico'),
    ('NSMSOPELK782HUN','Pedro Duran Suarez','564732891','Xochimilco #27 Col. Ecatepec de Morelos','Toluca','Mexico'),
    ('GJLGUOP785GSTBC','Gilberto Estrada Torres','9982735462','Cuarta de Octe. #304 Col. Cuauhtemoc','Oaxaca','Oaxaca'),
    ('J2JSHUWGW736JNV','Jose Fernandez Martinez','4429876428','Aquiles Serdan #100 Col. Volcanes','Oaxaca','Oaxaca');


insert into Oferta(Nombre,Descuento) values
    ('Cliente Frecuente',0.35),
    ('Año Nuevo',0.15),
    ('Verano Caliente',0.10);


insert into FormaPago(TipoPago) values
    ('Efectivo'),
    ('Tarjeta De Credito'),
    ('Cheque');


insert into Alquiler(CveAlquiler, CveAgenciaRenta, CveAgenciaDev, Matricula, RFC, TipoPago, FechaRenta,FechaDev,HoraRenta,HoraDev,CostoTotal) values
	(1,1,3,'HRU5937','J2JSHUWGW736JNV','Efectivo','15/01/2017','20/01/2017','9:00','17:00',3500.00),
	(2,1,4,'OFT2496','GJLGUOP785GSTBC','Cheque','18/03/2017','05/04/2017','10:00','18:00',4250.00),
    (3,2,2,'VGY7509','NSMSOPELK782HUN','Tarjeta De Credito','01/04/2017','10/04/2017','9:00','16:00',3150.00), 
    (4,2,3,'AYE7293','LSNCMU2789HHSIO','Tarjeta De Credito','11/04/2017','23/04/2017','11:00','13:00',7320.00),
	(5,3,5,'CNF7437','WEQPWN346LSÑSAI','Efectivo','14/05/2017','19/04/2017','12:00','13:00',2750.00),
    (6,3,6,'GIF9047','SDPOQEWIQW68129','Efectivo','27/05/2017','08/06/2017','10:00','17:00',3850.00),
	(7,4,7,'MCM2848','SADCYTEBHYCOO06','Efectivo','30/05/2017','12/06/2017','9:00','15:00',3540.00),
    (8,4,8,'PTA6394','DSALDIW9720JFHD','Tarjeta De Credito','02/06/2017','08/06/2017','9:00','18:00',5700.00), 
    (9,5,10,'ARR9385','CDSDASI3YWTW66E','Cheque','05/06/2017','24/06/2017','9:00','14:00',4750.00),
	(10,5,2,'FLA4934','DSLADJALD8826SD','Cheque','16/06/2017','02/07/2017','12:00','19:00',5600.00),
    (11,6,1,'WIL3948','DJSALDAISU72366','Cheque','09/07/2017','18/07/2017','13:00','15:00',8550.00),
	(12,6,2,'SUP3482','NCJDKSFHKJDF755','Tarjeta De Credito','21/07/2017','29/07/2017','17:00','14:00',3120.00),
    (13,7,6,'AQU3849','DSADH6236WEHLPP','Tarjeta De Credito','30/08/2017','25/08/2017','15:00','16:00',13750.00), 
    (14,7,7,'ARS3492','NJSADHJAOPWEU72','Efectivo','24/08/2017','28/08/2017','14:00','17:00',1000.00),
	(15,8,8,'WGI3845','OWQEQIOWEH827HD','Efectivo','12/09/2017','29/09/2017','16:00','13:00',9350.00),
    (16,8,8,'LEX8345','JDFKLDJSIQ223YQ','Efectivo','19/09/2017','26/09/2017','13:00','14:00',3850.00),
	(17,8,8,'REV3829','JSJDLSD7366HDHS','Tarjeta De Credito','04/10/2017','20/10/2017','9:00','15:00',9440.00),
    (18,9,9,'UNA8328','SAALJDIW920N23E','Cheque','13/10/2017','17/10/2017','12:00','18:00',3480.00), 
    (19,10,10,'TOL2854','AAPQPEKENJU792R','Cheque','26/10/2017','08/11/2017','9:30','14:00',7320.00),
	(20,10,10,'TIJ2438','DSAOWEI8926HBDS','Tarjeta De Credito','13/11/2017','17/11/2017','11:00','15:00',2440.00),
    (21,10,10,'SAN3483','SDJDWDOW8237WJN','Tarjeta De Credito','19/11/2017','26/11/2017','12:30','13:00',2450.00),
	(22,10,10,'QRO2203','KDSIDI376EGBDKS','Efectivo','24/11/2017','29/11/2017','10:00','17:00',2950.00),
    (23,1,1,'LEO3849','NJDLSO88263DKW8','Efectivo','06/12/2017','12/12/2017','10:00','17:00',3300.00), 
    (24,1,1,'PUE8293','ASDADWDJCHS323R','Cheque','17/12/2017','24/12/2017','11:00','16:00',6090.00),
	(25,1,1,'PAC8392','JGEWODSF73660SS','Tarjeta De Credito','20/12/2017','26/12/2017','9:00','14:00',5700.00),
    (26,1,2,'MON1972','HYRMI8467VGHETY','Efectivo','24/01/2017','27/01/2017','11:30','13:00',1770.00),
	(27,2,3,'ARN2487','FHSURYEH1724TRT','Cheque','17/01/2017','30/01/2017','12:30','19:00',11310.00);   

insert into Promocion(CveAlquiler, Nombre) values
	(1,'Cliente Frecuente'),
    (1,'Verano Caliente'),
    (2,'Cliente Frecuente'),
    (2,'Verano Caliente'),
    (3,'Verano Caliente'),
    (4,'Cliente Frecuente'),
    (4,'Verano Caliente'),
    (5,'Cliente Frecuente'),
    (10,'Verano Caliente'),
    (12,'Verano Caliente'),
    (15,'Cliente Frecuente'),
    (20,'Cliente Frecuente'),
    (24,'Cliente Frecuente');
    
   grant all privileges on alquilerdeautos.* to topicos@localhost identified by 'topicosprogramacion';
   
   select * from alquiler;
    