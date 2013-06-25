connect 'jdbc:derby://localhost:1527/SbmaDb';

insert into employee
(
	userName,
	password,
	courtesyTitle,
	firstName,
	lastName,
	addressLine1,
	city,
	stateOrRegion,
	isoCountryCode,
	postCode,
	dateOfBirth,
	gender,
	positionCode,
	wageAmount,
	wageInterval,
	isExempt,
	isManager
)
values
(
	'hyle',
	'hyle',
	'Mr.',
	'Hyle',
	'Campbell',
	'123 Main St',
	'Any City',
	'CA',
	'US',
	'99500',
	'1981-03-14 08:00:00.000',
	'M',
	1001,
	10000,
	'Y',
	false,
	true
);