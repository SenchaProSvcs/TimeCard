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
	'tony',
	'tony',
	'Mr.',
	'Tony',
	'Stuart',
	'1211 E. Cumberland Ave, Unit 803',
	'Tampa',
	'FL',
	'US',
	'33602',
	'1981-09-21 08:00:00.000',
	'M',
	1001,
	10000,
	'Y',
	false,
	false
);