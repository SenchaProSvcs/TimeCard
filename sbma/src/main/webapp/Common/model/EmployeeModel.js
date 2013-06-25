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
                name: 'firstName',
                type: 'string'
            },
            {
                name: 'lastName',
                type: 'string'
            },
            {
                name: 'isManager',
                type: 'boolean'
            },            
        ],
        
        belongsTo: 'Common.model.Employee'
    }
});