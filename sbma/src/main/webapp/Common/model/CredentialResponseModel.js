
Ext.define('Common.model.CredentialResponseModel', {
    extend: 'Ext.data.Model',

    config: {
        fields: [
            {
                name: 'authenticationToken',
                type: 'string'
            },
            {
                name: 'isManager',
                type: 'boolean'
            }
            
        ]
    }
});