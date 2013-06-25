
Ext.define('Timecard.store.TimeCardItemStore', {
    extend: 'Ext.data.Store',

    requires: [
        'Common.model.TimeCardItemModel'
    ],

    config: {
        model: 'Common.model.TimeCardItemModel',
        storeId: 'timeCardItemStore',
        proxy: {
            type: 'ajax',
            reader: {
                type: 'json'
            }
        },
        sorters: {
            property: 'beginDate'
        }
    },

    loadTimeCardItems: function(authenticationToken) {
        var url = '/sbma/rest/timeCardItem?&at=' + authenticationToken;
        this.getProxy().setUrl(url);
        this.load();
    },

    constructor: function(config) {
        this.callParent(arguments);
        Timecard.controller.EventBus.addLoginListener(this.onLogin, this);
    },

    onLogin: function(credentialResponseModel) {
        this.loadTimeCardItems(credentialResponseModel.get('authenticationToken'));
    }

});