drop table if exists monthlyRecord;
drop table if exists record;

create table record(
    id int primary key,
    daily_revenue float,
    bill_expense float,
    expense float,
    tax float,
    entry_date date
);

create table montlyRecord(
    id_record int foreign key
);