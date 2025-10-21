

create table if not exists address (
	address_id serial primary key,
	full_address varchar(999) not null
);

create table if not exists employee (
	emp_id serial primary key,
	first_name varchar(200) not null,
	last_name varchar,
	email varchar(200) not null unique,
	contact varchar(50) not null,
	address_id int not null,
	job_title varchar(200) not null,
	dob date not null,
	joining_date date not null default now(),
	is_serving boolean not null default true,
	constraint fk_address_id_address foreign key (address_id) references address(address_id)
); 


create table if not exists job (
	job_id serial primary key,
	job_title varchar(500) not null,
	posted_on date not null default now(),
	compensation int not null default 0,
	posted_by int not null,
	hiring_manager int not null,
	job_description text,
	is_active boolean not null default true,
	constraint fk_posted_by_employee foreign key (posted_by) references employee(emp_id),				
	constraint fk_hiring_manager_employee foreign key (hiring_manager) references employee(emp_id)
);

create index employee_index on employee(emp_id);
create index job_index on job(job_id);
create index address_index on address(address_id);

 create table if not exists users (
	user_id serial primary key,
	username varchar(50) unique not null,
	password varchar(200) not null,
	belongs_to int not null,
	access_rights VARCHAR(50) not null default 'EMPLOYEE',
	constraint fk_belongs_to_employee foreign key (belongs_to) references employee(emp_id)
);

create index users_index on users(user_id);

/*Phase 2*/

create table if not exists departments (
    department_id serial primary key ,
	department_title varchar(255) not null unique,
    description text null,
    is_active boolean not null default true
);

alter table job
add column job_address varchar(999),
add column city varchar(200),
add column state varchar(200),
add column minimum_experience int not null default 0,
add column maximum_experience int,
add column department_id int;

alter table job
add constraint fk_department_id_department
foreign key (department_id) references departments(department_id) on delete set null;

alter table job
add constraint chk_experience_range
check (minimum_experience <= maximum_experience);


/*Phase 3*/

