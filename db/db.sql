drop table if exists monthlyRecord;
drop table if exists record;

create table record(
    id int primary key auto_increment,
    daily_revenue float,
    bill_expense float,
    expense float,
    tax float,
    entry_date date unique
);

create table monthlyRecord(
    id_record int,
    month_number int,
    year int
);

alter table monthlyRecord add foreign key (id_record) references record(id);

insert into record(daily_revenue,bill_expense,expense,tax,entry_date) values(5.5,5.6,5.7,5.8,to_date('2018-08-18','yyyy-mm-dd'));

select * from record;