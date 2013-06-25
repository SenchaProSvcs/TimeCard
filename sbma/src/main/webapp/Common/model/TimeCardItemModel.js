Ext.define('Timecard.model.TimeCardItemModel', {
    extend: 'Ext.data.Model',

    config: {
        fields: [
            {
                name: 'id',
                type: 'auto'
            },
            {
                name: 'employeeId',
                type: 'int'
            },            
            {
                name: 'type',
                type: 'int'
            },
            {
                name: 'beginDate',
                type: 'date'
            },
            {
                name: 'endDate',
                type: 'date'
            },
            {
                name: 'latitude',
                type: 'float'
            },
            {
                name: 'longitude',
                type: 'float'
            },
            {
                name: 'notes',
                type: 'string'
            }
            
        ],
        
        belongsTo: 'Common.model.EmployeeModel'
    }
});